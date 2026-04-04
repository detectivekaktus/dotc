package net.detectivekaktus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class DotcItem extends Item {
    public DotcItem(Properties properties) {
        super(properties);
    }

    protected List<Component> generateTooltipTranslationStrings(int count, String itemId) {
        return this.generateTooltipTranslationStrings(count, itemId, ChatFormatting.GRAY);
    }

    protected List<Component> generateTooltipTranslationStrings(int count, String itemId, ChatFormatting formatting) {
        var components = new ArrayList<Component>();
        for (int i = 0; i < count; i++)
            components.add(Component.translatable("itemTooltip.l" + (i + 1) + ".defense-of-the-craft." + itemId)
                    .withStyle(formatting));
        return components;
    }
}
