package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin {
    @Inject(
            method = "performRangedAttack",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelAttack(LivingEntity livingEntity, float f, CallbackInfo callbackInfo) {
        var mob = (AbstractSkeleton) (Object) this;
        if (!mob.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.cancel();
    }
}
