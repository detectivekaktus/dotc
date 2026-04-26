package net.detectivekaktus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import net.detectivekaktus.DefenseOfTheCraft;

public interface TooltipProvider {
    default Component emitComponent(String prefix, String id, ChatFormatting formatting) {
        return Component.translatable(prefix + "." + DefenseOfTheCraft.MOD_ID + "." + id).withStyle(formatting);
    }

    default Component emitComponent(String prefix, ChatFormatting formatting, Object... args) {
        return Component.translatable(prefix + "." + DefenseOfTheCraft.MOD_ID, args).withStyle(formatting);
    }
}
