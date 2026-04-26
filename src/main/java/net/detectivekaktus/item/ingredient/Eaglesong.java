package net.detectivekaktus.item.ingredient;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import net.detectivekaktus.item.TooltipProvider;

public class Eaglesong extends Item implements TooltipProvider {
    public Eaglesong(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getDescriptionComponent("eaglesong"));
    }
}
