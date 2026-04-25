package net.detectivekaktus.item.tool;

import net.detectivekaktus.item.DotcItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class Kaya extends SwordItem implements DotcItem {
    public static final float MANA_COST_REDUCTION = 0.1f;

    public Kaya(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getTooltipComponent("kaya"));
    }
}
