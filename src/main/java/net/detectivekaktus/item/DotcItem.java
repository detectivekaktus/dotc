package net.detectivekaktus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import net.detectivekaktus.DefenseOfTheCraft;

public interface DotcItem {
    default Component getTooltipComponent(String id) {
        return this.getTooltipComponent(id, ChatFormatting.GRAY);
    }

    default Component getTooltipComponent(String itemId, ChatFormatting formatting) {
        return Component.translatable(
                "itemTooltip." + DefenseOfTheCraft.MOD_ID + "." + itemId
        ).withStyle(formatting);
    }
}
