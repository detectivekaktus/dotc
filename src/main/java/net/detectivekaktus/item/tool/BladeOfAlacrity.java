package net.detectivekaktus.item.tool;

import net.detectivekaktus.item.DotcItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BladeOfAlacrity extends SwordItem implements DotcItem {
    public BladeOfAlacrity(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(1, "blade_of_alacrity");
        tooltip.addAll(components);
    }
}
