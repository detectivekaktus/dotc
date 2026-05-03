package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.effect.DotcEffects;

@Mixin(targets = "net.minecraft.world.entity.monster.Blaze$BlazeAttackGoal")
public class BlazeMixin {
    @WrapWithCondition(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean cancelFireballs(Level level, Entity entity) {
        var fireball = (Fireball) entity;
        var blaze = (Blaze) fireball.getOwner();
        if (blaze == null) {
            DefenseOfTheCraft.LOGGER.warn("Blaze fireball doesn't have owner. No bash logic will be executed.");
            return true;
        }

        return !blaze.hasEffect(DotcEffects.STUN);
    }
}
