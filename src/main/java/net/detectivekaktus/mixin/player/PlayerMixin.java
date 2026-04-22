package net.detectivekaktus.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

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
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.item.tool.Critable;
import net.detectivekaktus.item.tool.HasBonusDamage;
import net.detectivekaktus.mixin.interfaces.CanHitThroughEvasion;
import net.detectivekaktus.mixin.interfaces.Evadable;
import net.detectivekaktus.sound.DotcSounds;

@Mixin(Player.class)
public class PlayerMixin implements Evadable, CanHitThroughEvasion {
    @Unique
    private boolean dotc$proccedPostAttackDamage = false;
    @Unique
    private boolean dotc$evaded = false;

    @Unique
    @Override
    public boolean getHitThroughEvasion() {
        return dotc$proccedPostAttackDamage;
    }

    @Unique
    @Override
    public void setHitThroughEvasion(boolean evaded) {
        dotc$proccedPostAttackDamage = evaded;
    }

    @Override
    public boolean getEvaded() {
        return dotc$evaded;
    }

    @Override
    public void setEvaded(boolean evaded) {
        dotc$evaded = evaded;
    }

    @Unique
    private boolean isNotMixinTarget(Player player) {
        return player.level().isClientSide || !(player instanceof ServerPlayer);
    }

    @ModifyVariable(
            method = "attack",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/entity/player/Player;getWeaponItem()Lnet/minecraft/world/item/ItemStack;",
                    shift = At.Shift.BEFORE
            ),
            ordinal = 0
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

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
                    shift = At.Shift.BEFORE
            )
    )
    private void calculatePostAttackProcs(Entity entity, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return;

        dotc$proccedPostAttackDamage = false;
        var stack = player.getMainHandItem();
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT) || !(stack.getItem() instanceof HasBonusDamage))
            return;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
            return;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                new ProcableComponent(component.baseChance(), 0)
        );
        dotc$proccedPostAttackDamage = true;
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
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT) || !(stack.getItem() instanceof HasBonusDamage item))
            return hurt;

        if (!dotc$proccedPostAttackDamage)
            return hurt;

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

        return hurt;
    }

    @Unique
    private void playEvasionSound() {
        var entity = (Player) (Object) this;
        entity.level().playSound(
                null,
                entity.getX(), entity.getY(), entity.getZ(),
                DotcSounds.EVADED,
                entity.getSoundSource(),
                1.0f, 1.0f
        );
    }

    @Inject(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void applyEvasion(DamageSource damageSource, float f, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player) || damageSource.is(DotcDamageTypes.MAGICAL))
            return;

        dotc$evaded = false;
        var stats = PlayerStats.get(player);
        var evasion = stats.getEvasion();
        var evasionChance = PseudoRandom.getProcChance(evasion, stats.getEvasionScale());
        if (player.getRandom().nextFloat() > evasionChance) {
            stats.addEvasionScale(1);
            return;
        }

        stats.setEvasionScale(0);

        var attacker = damageSource.getEntity();
        if (attacker == null)
            return;

        if (!(attacker instanceof ServerPlayer)) {
            dotc$evaded = true;
            playEvasionSound();
            callbackInfo.cancel();
            return;
        }

        var hitThrough = ((CanHitThroughEvasion) attacker).getHitThroughEvasion();
        if (hitThrough)
            return;

        dotc$evaded = true;
        playEvasionSound();
        callbackInfo.cancel();
    }

    @ModifyVariable(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
            ),
            ordinal = 0
    )
    private float applyDamageReduction(float original, DamageSource damageSource) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player) || !damageSource.is(DotcDamageTypes.MAGICAL))
            return original;

        var stats = PlayerStats.get(player);
        return original * (1.0f - stats.getMagicResistance());
    }
}
