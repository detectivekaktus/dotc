package net.detectivekaktus.item.ingredient;

import net.detectivekaktus.item.DotcItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BandOfElvenskin extends Item implements DotcItem {
    public BandOfElvenskin(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(2, "band_of_elvenskin");
        tooltip.addAll(components);
    }
}
