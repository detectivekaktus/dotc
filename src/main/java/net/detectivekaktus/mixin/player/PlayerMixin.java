package net.detectivekaktus.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.core.rng.PseudoRandom;
import net.detectivekaktus.item.tool.HasBonusDamage;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
                    shift = At.Shift.AFTER
            )
    )
    private void onAttack(Entity entity, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (player.level().isClientSide || !(player instanceof ServerPlayer))
            return;

        var stack = player.getMainHandItem();
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT))
            return;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    //                   weirdly enough you can't do ++component.scale()
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
            return;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                new ProcableComponent(component.baseChance(), 0)
        );

        if (stack.getItem() instanceof HasBonusDamage item) {
            var damageSource = item.getBonusDamageSource(player);
            var bonusDamage = item.getBonusDamage();
            entity.hurt(damageSource, bonusDamage);
        }
        else
            DefenseOfTheCraft.LOGGER.warn(
                    "{} has procable component but doesn't implement `HasBonusDamage` interface",
                    stack.getItem().toString()
            );
    }
}
