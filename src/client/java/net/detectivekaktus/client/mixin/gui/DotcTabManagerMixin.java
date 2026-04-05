package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.detectivekaktus.sound.gui.DotcGuiSounds;

@Mixin(TabManager.class)
public class DotcTabManagerMixin {
    @Redirect(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
    ), method = "setCurrentTab")
    private void changeSetCurrentTabUiSound(SoundManager soundManager, SoundInstance soundInstance) {
        soundManager.play(SimpleSoundInstance.forUI(DotcGuiSounds.UI_CHANGE_TAB, 1.0f));
    }
}
