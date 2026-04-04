package net.detectivekaktus.item.ingredients;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import net.detectivekaktus.item.DotcItem;

public class DireCrystal extends DotcItem {
    public DireCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(2, "dire_crystal");
        tooltip.addAll(components);
    }
}
