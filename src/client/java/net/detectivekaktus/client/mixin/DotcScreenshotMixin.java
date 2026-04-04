package net.detectivekaktus.client.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.detectivekaktus.sound.DotcSounds;

@Mixin(Screenshot.class)
public class DotcScreenshotMixin {
    @Inject(at = @At("HEAD"), method = "takeScreenshot")
    private static void takeScreenshoot(CallbackInfoReturnable<NativeImage> info) {
        var client = Minecraft.getInstance();
        client.getSoundManager().play(SimpleSoundInstance.forUI(DotcSounds.STEAM_CAMERA, 1.0f));
    }
}
