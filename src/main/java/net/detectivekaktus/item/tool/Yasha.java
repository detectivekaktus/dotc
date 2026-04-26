package net.detectivekaktus.item.tool;

import net.minecraft.world.item.Tier;

import net.detectivekaktus.item.DotcSwordItem;
import net.detectivekaktus.item.TooltipBuilder;

public class Yasha extends DotcSwordItem {
    public static final float MOVE_SPEED_BONUS = 0.125f;

    public Yasha(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }
}
