package net.detectivekaktus.item.ingredient;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import net.detectivekaktus.item.TooltipProvider;

public class DireCrystal extends Item implements TooltipProvider {
    public DireCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getDescriptionComponent("dire_crystal"));
    }
}
