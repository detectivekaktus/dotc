package net.detectivekaktus.item.primitive;

import net.detectivekaktus.item.TooltipProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BladeOfAlacrity extends SwordItem implements TooltipProvider {
    public BladeOfAlacrity(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getDescriptionComponent("blade_of_alacrity"));
    }
}
