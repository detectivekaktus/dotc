package net.detectivekaktus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class TooltipBuilder implements TooltipProvider {
    private final List<Component> components = new ArrayList<>();

    public TooltipBuilder() { }

    public TooltipBuilder getDescriptionComponent(String id) {
        components.add(emitComponent("description", id, ChatFormatting.GRAY));
        return this;
    }

    public TooltipBuilder getStatsComponent(int strength, int agility, int intelligence) {
        components.add(emitComponent("strength", ChatFormatting.BLUE, strength));
        components.add(emitComponent("agility", ChatFormatting.BLUE, agility));
        components.add(emitComponent("intelligence", ChatFormatting.BLUE, intelligence));
        return this;
    }

    public TooltipBuilder getActiveComponent(String id) {
        components.add(emitComponent("active", id, ChatFormatting.GOLD));
        return this;
    }

    public TooltipBuilder getPassiveComponent(String id) {
        components.add(emitComponent("passive", id, ChatFormatting.DARK_AQUA));
        return this;
    }

    public List<Component> build() {
        return components;
    }
}
