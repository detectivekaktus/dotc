package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.detectivekaktus.sound.DotcSounds;

@Mixin(WorldSelectionList.class)
public class DotcWorldSelectionListMixin {
    @Redirect(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
    ), method = "keyPressed")
    private void changeKeyPressedUiSound(SoundManager soundManager, SoundInstance soundInstance) {
        soundManager.play(SimpleSoundInstance.forUI(DotcSounds.BUTTON_PRESS, 1.0f));
    }
}
