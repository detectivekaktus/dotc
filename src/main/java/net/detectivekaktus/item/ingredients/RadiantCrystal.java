package net.detectivekaktus.item.ingredients;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class RadiantCrystal extends Item {
    public RadiantCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.translatable(
                "itemTooltip.l1.defense-of-the-craft.radiant_crystal"
        ).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable(
                "itemTooltip.l2.defense-of-the-craft.radiant_crystal"
        ).withStyle(ChatFormatting.GRAY));
    }
}
