package net.detectivekaktus.item.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcItem;

import java.util.List;

public class Butterfly extends SwordItem implements DotcItem {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_20;

    public Butterfly(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getTooltipComponent("butterfly"));
    }
}
