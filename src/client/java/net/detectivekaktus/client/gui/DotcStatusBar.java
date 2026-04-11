package net.detectivekaktus.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.attach.Mana;
import net.detectivekaktus.attach.Stats;

public class DotcStatusBar {
    private static final ResourceLocation STRENGTH_ICON = ResourceLocation.fromNamespaceAndPath(
            DefenseOfTheCraft.MOD_ID,
            "textures/gui/strength_icon.png"
    );
    private static final ResourceLocation AGILITY_ICON = ResourceLocation.fromNamespaceAndPath(
            DefenseOfTheCraft.MOD_ID,
            "textures/gui/agility_icon.png"
    );
    private static final ResourceLocation INTELLIGENCE_ICON = ResourceLocation.fromNamespaceAndPath(
            DefenseOfTheCraft.MOD_ID,
            "textures/gui/intelligence_icon.png"
    );

    private static final int HOTBAR_WIDTH = 182; // vanilla hotbar width
    private static final int HOTBAR_HEIGHT = 22; // vanilla hotbar height
    private static final int TEXT_COLOR = 0xFFFFFFFF;
    private static final int ICON_TO_TEXT_MARGIN = 13;
    private static final int STAT_TO_STAT_MARGIN = 25;
    private static final int STATS_TO_MANA_MARGIN = 20;

    private static int getStatusBarYPos(int height) {
        return (int) (height - (HOTBAR_HEIGHT * 3.15f));
    }

    private static void drawMana(GuiGraphics context, int x, int y) {
        var client = Minecraft.getInstance();
        var mana = Mana.get(client.player);
        var current = (int) mana.getCurrentMana();
        var max = (int) mana.getMaxMana();
        var str = String.format("%d/%d", current, max);
        context.drawString(client.font, str, x, y, TEXT_COLOR);
    }

    private static int drawIconAndValue(GuiGraphics context, ResourceLocation icon, int value, int x, int y) {
        var client = Minecraft.getInstance();
        context.blit(icon, x, y, 0, 0, 8, 8, 8, 8);

        var strX = x + ICON_TO_TEXT_MARGIN;
        var strValue = String.valueOf(value);
        context.drawString(client.font, strValue, strX, y, TEXT_COLOR);

        return strX + STAT_TO_STAT_MARGIN;
    }

    private static int drawStats(GuiGraphics context, Stats.StatsData stats, int x, int y) {
        x = drawIconAndValue(context, STRENGTH_ICON, stats.getStrength(), x, y);
        x = drawIconAndValue(context, AGILITY_ICON, stats.getAgility(), x, y);
        x = drawIconAndValue(context, INTELLIGENCE_ICON, stats.getIntelligence(), x, y);
        return x;
    }

    public static void drawStatusBar(GuiGraphics context, DeltaTracker tickCounter) {
        var client = Minecraft.getInstance();
        var stats = Stats.get(client.player);
        var width = client.getWindow().getGuiScaledWidth();
        var height = client.getWindow().getGuiScaledHeight();

        var x = (width - HOTBAR_WIDTH) / 2;
        var y = getStatusBarYPos(height);
        x = drawStats(context, stats, x, y);
        x += STATS_TO_MANA_MARGIN;
        drawMana(context, x, y);
    }


}
