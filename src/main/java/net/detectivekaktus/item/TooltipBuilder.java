package net.detectivekaktus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import net.detectivekaktus.DefenseOfTheCraft;

public class TooltipBuilder {
    private final List<Component> components = new ArrayList<>();
    private final String itemId;

    public TooltipBuilder(String itemId) {
        this.itemId = itemId;
    }

    public TooltipBuilder description() {
        components.add(emitComponent("description", ChatFormatting.GRAY));
        return this;
    }

    public TooltipBuilder stats(int strength, int agility, int intelligence) {
        if (strength != 0) components.add(emitComponent("strength", ChatFormatting.BLUE, strength));
        if (agility != 0) components.add(emitComponent("agility", ChatFormatting.BLUE, agility));
        if (intelligence != 0) components.add(emitComponent("intelligence", ChatFormatting.BLUE, intelligence));
        return this;
    }

    public TooltipBuilder active() {
        components.add(emitComponent("active", ChatFormatting.GOLD));
        return this;
    }

    public TooltipBuilder passive() {
        components.add(emitComponent("passive", ChatFormatting.DARK_AQUA));
        return this;
    }

    public List<Component> build() {
        return List.copyOf(components);
    }

    private Component emitComponent(String prefix, ChatFormatting formatting) {
        return Component.translatable(prefix + "." + DefenseOfTheCraft.MOD_ID + "." + itemId).withStyle(formatting);
    }

    private Component emitComponent(String prefix, ChatFormatting formatting, Object... args) {
        return Component.translatable(prefix + "." + DefenseOfTheCraft.MOD_ID, args).withStyle(formatting);
    }
}
