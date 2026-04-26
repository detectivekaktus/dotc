package net.detectivekaktus.item.tool;

import net.detectivekaktus.item.TooltipBuilder;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.item.DotcSwordItem;
import net.detectivekaktus.core.rng.PseudoRandomBaseChances;

public class Butterfly extends DotcSwordItem {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_20;

    public Butterfly(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }
}
