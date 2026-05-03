package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(Slime.class)
public class SlimeMixin {
    @Inject(
            method = "dealDamage",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelAttack(LivingEntity livingEntity, CallbackInfo callbackInfo) {
        var slime = (Slime) (Object) this;
        if (!slime.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.cancel();
    }
}
