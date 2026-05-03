package net.detectivekaktus.item.tool;

import net.detectivekaktus.core.item.Procable;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;

import java.util.Optional;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.item.DotcSpearItem;
import net.detectivekaktus.item.TooltipBuilder;

public class Javelin extends DotcSpearItem implements Procable {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_25;
    private static final float BONUS_DAMAGE = 2.0f;

    public Javelin(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public float getProcDamage() {
        return BONUS_DAMAGE;
    }

    @Override
    public DamageSource getProcDamageSource(Player player) {
        return player.level().damageSources().source(
                DotcDamageTypes.MAGICAL,
                player,
                player
        );
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.empty();
    }

    @Override
    public Optional<Holder<MobEffect>> getProcEffect() {
        return Optional.empty();
    }

    @Override
    public int getProcCooldownInTicks() {
        return 0;
    }
}
