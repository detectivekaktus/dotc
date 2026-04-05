package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.detectivekaktus.sound.gui.DotcGuiSounds;

@Mixin(WorldSelectionList.class)
public class DotcWorldSelectionListMixin {
    @ModifyArg(method = "keyPressed", index = 0, at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
    ))
    public SoundInstance changeKeyPressedUiSound(SoundInstance original) {
        return SimpleSoundInstance.forUI(DotcGuiSounds.UI_BUTTON_PRESS, 1.0f);
    }
}
