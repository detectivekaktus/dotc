package net.detectivekaktus.item.tool;

import net.detectivekaktus.core.item.Critable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Tier;

import java.util.Optional;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.sound.item.DotcItemSounds;
import net.detectivekaktus.item.DotcPickaxeItem;
import net.detectivekaktus.item.TooltipBuilder;

public class Daedalus extends DotcPickaxeItem implements Critable {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_20;
    private static final float CRIT_PERCENT = 1.5f;

    public Daedalus(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public float getCritPercent() {
        return CRIT_PERCENT;
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.of(DotcItemSounds.DAEDALUS_CRIT);
    }
}
