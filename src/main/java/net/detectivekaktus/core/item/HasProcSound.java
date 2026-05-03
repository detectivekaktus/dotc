package net.detectivekaktus.core.item;

import net.minecraft.sounds.SoundEvent;

import java.util.Optional;

public interface HasProcSound {
    Optional<SoundEvent> getProcSound();
}
