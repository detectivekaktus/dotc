package net.detectivekaktus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import net.detectivekaktus.DefenseOfTheCraft;

public interface DotcItem {
    default List<Component> generateTooltipTranslationStrings(int count, String itemId) {
        return this.generateTooltipTranslationStrings(count, itemId, ChatFormatting.GRAY);
    }

    default List<Component> generateTooltipTranslationStrings(int count, String itemId, ChatFormatting formatting) {
        var components = new ArrayList<Component>();
        for (int i = 0; i < count; i++)
            components.add(Component.translatable("itemTooltip.l" + (i + 1) + "." + DefenseOfTheCraft.MOD_ID + "." + itemId)
                    .withStyle(formatting));
        return components;
    }
}
