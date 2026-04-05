package net.detectivekaktus.sound;

import net.detectivekaktus.DefenseOfTheCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class DotcSounds {
    public static final SoundEvent STEAM_CAMERA = register("steam_camera");
    public static final SoundEvent BUTTON_PRESS = register("button_press");
    public static final SoundEvent BUTTON_CONFIRM = register("button_confirm");
    public static final SoundEvent UI_CHANGE_TAB = register("ui_change_tab");

    private static SoundEvent register(String soundId) {
        var id = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, soundId);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void initialize() { }
}
