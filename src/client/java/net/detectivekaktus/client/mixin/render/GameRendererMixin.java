package net.detectivekaktus.client.mixin.render;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/LevelRenderer;doEntityOutline()V",
                    shift = At.Shift.AFTER
            )
    )
    private void renderStunBlur(DeltaTracker deltaTracker, boolean bl, CallbackInfo callbackInfo) {
        var renderer = (GameRenderer) (Object) this;
        var player = renderer.getMinecraft().player;
        if (player != null && player.hasEffect(DotcEffects.STUN))
            renderer.processBlurEffect(0.25f);
    }
}
