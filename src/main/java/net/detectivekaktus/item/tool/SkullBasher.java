package net.detectivekaktus.item.tool;

import net.detectivekaktus.core.item.HasBonusAttackEffects;
import net.detectivekaktus.core.item.HasCooldown;
import net.detectivekaktus.core.item.SharesCooldown;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.effect.DotcEffects;
import net.detectivekaktus.item.DotcPickaxeItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;

import java.util.List;
import java.util.Optional;

public class SkullBasher extends DotcPickaxeItem implements HasBonusAttackEffects, HasCooldown, SharesCooldown {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_25;

    public SkullBasher(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public float getProcDamage() {
        return 2.5f;
    }

    @Override
    public DamageSource getProcDamageSource(Player player) {
        return player.damageSources().source(DotcDamageTypes.PHYSICAL, player, player);
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.of(DotcItemSounds.SKULL_BASHER);
    }

    @Override
    public Optional<Holder<MobEffect>> getProcEffect() {
        return Optional.of(DotcEffects.STUN);
    }

    @Override
    public int getCooldownInTicks() {
        return 3 * 20;
    }

    @Override
    public List<Item> getSharesCooldownWith() {
        return List.of(DotcTools.ABYSSAL_BLADE);
    }
}
