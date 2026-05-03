package net.detectivekaktus.item.tool;

import net.detectivekaktus.core.item.Critable;
import net.detectivekaktus.item.TooltipBuilder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Tier;

import java.util.Optional;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcSwordItem;

public class Crystalys extends DotcSwordItem implements Critable {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_20;
    private static final float CRIT_PERCENT = 1.25f;

    public Crystalys(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public float getCritPercent() {
        return CRIT_PERCENT;
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.empty();
    }
}
