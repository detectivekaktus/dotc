package net.detectivekaktus.item.ingredient;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import net.detectivekaktus.item.DotcItem;

public class TalismanOfEvasion extends Item implements DotcItem {
    public TalismanOfEvasion(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(1, "talisman_of_evasion");
        tooltip.addAll(components);
    }
}
