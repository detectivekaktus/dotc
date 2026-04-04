package net.detectivekaktus.client.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.sound.DotcSounds;

@Mixin(AbstractWidget.class)
public class DotcAbstractWidgetMixin {
    @Inject(at = @At("HEAD"), method = "playDownSound", cancellable = true)
    public void playDownSound(SoundManager soundManager, CallbackInfo callbackInfo) {
        soundManager.play(SimpleSoundInstance.forUI(DotcSounds.BUTTON_PRESS, 1.0f));
        callbackInfo.cancel();
    }
}
