package net.detectivekaktus.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Expression("(? * 8.0)")
    @ModifyExpressionValue(method = "turnPlayer", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private double reduceSensitivityWhenStunned(double original) {
        if (minecraft.player != null && minecraft.player.hasEffect(DotcEffects.STUN))
            return original * 0.25;
        return original;
    }
}
