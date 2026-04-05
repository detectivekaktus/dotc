package net.detectivekaktus.client.mixin;

import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.detectivekaktus.sound.DotcSounds;

@Mixin(WorldSelectionList.WorldListEntry.class)
public class DotcWorldListEntryMixin {
    @Redirect(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
    ), method = "mouseClicked")
    private void changeMouseClickedUiSound(SoundManager soundManager, SoundInstance soundInstance) {
        soundManager.play(SimpleSoundInstance.forUI(DotcSounds.BUTTON_CONFIRM, 1.0f));
    }
}
