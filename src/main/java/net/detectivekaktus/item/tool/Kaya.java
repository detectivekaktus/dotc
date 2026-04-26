package net.detectivekaktus.item.tool;

import net.minecraft.world.item.Tier;

import net.detectivekaktus.item.DotcSwordItem;
import net.detectivekaktus.item.TooltipBuilder;

public class Kaya extends DotcSwordItem {
    public static final float MANA_COST_REDUCTION = 0.1f;

    public Kaya(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }
}
