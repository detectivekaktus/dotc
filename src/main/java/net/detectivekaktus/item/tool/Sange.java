package net.detectivekaktus.item.tool;

import net.minecraft.world.item.Tier;

import net.detectivekaktus.item.DotcSwordItem;
import net.detectivekaktus.item.TooltipBuilder;

public class Sange extends DotcSwordItem {
    public static final float HP_REGEN_AMPLIFICATION = 0.12f;

    public Sange(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }
}
