package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(Mob.class)
public class MobMixin {
    @Inject(
            method = "doHurtTarget",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelAttack(Entity entity, CallbackInfoReturnable<Boolean> callbackInfo) {
        var mob = (Mob) (Object) this;
        if (!mob.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.setReturnValue(false);
    }
}
