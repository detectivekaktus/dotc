package net.detectivekaktus.core.item;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public interface HasBonusAttackEffects extends HasProcSound {
    float getProcDamage();
    DamageSource getProcDamageSource(Player player);
    Optional<Holder<MobEffect>> getProcEffect();
}
