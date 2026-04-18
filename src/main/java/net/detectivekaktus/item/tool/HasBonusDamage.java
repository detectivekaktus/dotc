package net.detectivekaktus.item.tool;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public interface HasBonusDamage extends HasProcSound {
    float getBonusDamage();
    DamageSource getBonusDamageSource(Player player);
}
