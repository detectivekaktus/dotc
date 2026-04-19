package net.detectivekaktus.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.core.rng.PseudoRandom;
import net.detectivekaktus.item.tool.Critable;
import net.detectivekaktus.item.tool.HasBonusDamage;
import net.detectivekaktus.sound.DotcSounds;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique
    private boolean dotc$hitThroughEvasion = false;

    @Unique
    private boolean isNotMixinTarget(Player player) {
        return player.level().isClientSide || !(player instanceof ServerPlayer);
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;",
                    shift = At.Shift.BEFORE,
                    ordinal = 0
            ),
            cancellable = true
    )
    private void applyEvasion(Entity entity, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return;

        if (!(entity instanceof ServerPlayer))
            return;

        dotc$hitThroughEvasion = false;
        var entityStats = PlayerStats.get(entity);
        var evasion = entityStats.getEvasion();
        var evasionChance = PseudoRandom.getProcChance(evasion, entityStats.getEvasionScale());
        if (entity.getRandom().nextFloat() > evasionChance) {
            entityStats.addEvasionScale(1);
            return;
        }

        var stack = player.getMainHandItem();
        if (stack.getItem() instanceof HasBonusDamage && stack.has(DotcComponents.PROCABLE_COMPONENT)) {
            var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
            var bonusDamageChance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
            if (player.getRandom().nextFloat() < bonusDamageChance) {
                dotc$hitThroughEvasion = true;
                return;
            }

            // I doubt if this is fair. You don't land a hit it means you shouldn't get your
            // pseudo-random busted, however I still keep it here. Maybe it'll be deleted
            // one day :)
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
        }

        entityStats.setEvasionScale(0);
        entity.level().playSound(
                null,
                entity.getX(), entity.getY(), entity.getZ(),
                DotcSounds.EVADED,
                entity.getSoundSource(),
                1.0f, 1.0f
        );
        callbackInfo.cancel();
    }

    @ModifyVariable(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            ),
            name = "f"
    )
    private float applyCritProcs(float original) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return original;

        var stack = player.getMainHandItem();
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT)
                || !(stack.getItem() instanceof Critable item))
            return original;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
            return original;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                new ProcableComponent(component.baseChance(), 0)
        );

        var sound = item.getProcSound();
        sound.ifPresent(soundEvent -> player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                soundEvent,
                player.getSoundSource(),
                1.0f, 1.0f
        ));

        return original * item.getCritPercent();
    }

    @Unique
    private void doApplyPostAttackProcs(ItemStack stack, ProcableComponent component, Entity entity) {
        var player = (Player) (Object) this;

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                new ProcableComponent(component.baseChance(), 0)
        );
        var item = (HasBonusDamage) stack.getItem();

        var damageSource = item.getBonusDamageSource(player);
        var bonusDamage = item.getBonusDamage();
        var sound = item.getProcSound();

        sound.ifPresent(soundEvent -> player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                soundEvent,
                player.getSoundSource(),
                1.0f, 1.0f
        ));
        entity.hurt(damageSource, bonusDamage);
    }

    @ModifyExpressionValue(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    private boolean applyPostAttackProcs(boolean hurt, Entity entity) {
        if (!hurt)
            return false;

        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return hurt;

        var stack = player.getMainHandItem();
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT)
                || !(stack.getItem() instanceof HasBonusDamage))
            return hurt;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        if (dotc$hitThroughEvasion) {
            doApplyPostAttackProcs(stack, component, entity);
            return hurt;
        }

        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    //                   weirdly enough you can't do ++component.scale()
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
            return hurt;
        }

        doApplyPostAttackProcs(stack, component, entity);

        return hurt;
    }
}
