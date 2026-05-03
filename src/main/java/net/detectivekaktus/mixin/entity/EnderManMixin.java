package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(EnderMan.class)
public class EnderManMixin {
    @Inject(
            method = "teleport",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelTeleport(CallbackInfoReturnable<Boolean> callbackInfo) {
        var enderMan = (EnderMan) (Object) this;
        if (enderMan.level().isClientSide)
            return;

        if (!enderMan.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.setReturnValue(false);
    }

    @Inject(
            method = "teleport(DDD)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelTeleportWithCoordinates(double x, double y, double z, CallbackInfoReturnable<Boolean> callbackInfo) {
        var enderMan = (EnderMan) (Object) this;
        if (enderMan.level().isClientSide)
            return;

        if (!enderMan.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.setReturnValue(false);
    }

    @Inject(
            method = "teleportTowards",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelTeleportTowards(Entity entity, CallbackInfoReturnable<Boolean> callbackInfo) {
        var enderMan = (EnderMan) (Object) this;
        if (enderMan.level().isClientSide)
            return;

        if (!enderMan.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.setReturnValue(false);
    }
}
