package net.detectivekaktus.item.ingredients;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DireCrystal extends Item {
    public DireCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        for (int i = 0; i < 2; i++)
            tooltip.add(Component.translatable(
                    "itemTooltip.l" + (i + 1) + ".defense-of-the-craft.dire_crystal"
            ).withStyle(ChatFormatting.GRAY));
    }
}
