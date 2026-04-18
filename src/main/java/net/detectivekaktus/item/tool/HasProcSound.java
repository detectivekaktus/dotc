package net.detectivekaktus.item.tool;

import net.minecraft.sounds.SoundEvent;

import java.util.Optional;

public interface HasProcSound {
    Optional<SoundEvent> getProcSound();
}
