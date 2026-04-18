package net.detectivekaktus.item.tool;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public interface HasBonusDamage {
    float getBonusDamage();
    DamageSource getBonusDamageSource(Player player);
    Optional<SoundEvent> getBonusDamageSound();
}
