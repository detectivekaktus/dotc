package net.detectivekaktus.item.ingredient;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import net.detectivekaktus.item.DotcItem;

import java.util.List;

// TODO: Make this item a wearable armor
public class RobeOfTheMagi extends Item implements DotcItem {
    public RobeOfTheMagi(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getTooltipComponent("robe_of_the_magi"));
    }
}
