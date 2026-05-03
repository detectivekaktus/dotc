package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.monster.Creeper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(Creeper.class)
public class CreeperMixin {
    @Inject(
            method = "explodeCreeper",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void explodeIfNotStunned(CallbackInfo callbackInfo) {
        var creeper = (Creeper) (Object) this;
        if (!creeper.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.cancel();
    }
}
