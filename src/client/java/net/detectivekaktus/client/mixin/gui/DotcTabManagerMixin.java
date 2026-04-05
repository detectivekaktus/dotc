package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.detectivekaktus.sound.gui.DotcGuiSounds;

@Mixin(TabManager.class)
public class DotcTabManagerMixin {
    @ModifyArg(method = "setCurrentTab", index = 0, at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"
    ))
    public SoundInstance changeSetCurrentTabUiSound(SoundInstance original) {
        return SimpleSoundInstance.forUI(DotcGuiSounds.UI_CHANGE_TAB, 1.0f);
    }
}
