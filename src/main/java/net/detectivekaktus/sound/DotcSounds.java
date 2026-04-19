package net.detectivekaktus.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.sound.gui.DotcGuiSounds;
import net.detectivekaktus.sound.item.DotcItemSounds;

public class DotcSounds {
    public static final SoundEvent EVADED = register("core_evaded");

    public static SoundEvent register(String soundId) {
        var id = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, soundId);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void initialize() {
        DotcGuiSounds.initialize();
        DotcItemSounds.initialize();
    }
}
