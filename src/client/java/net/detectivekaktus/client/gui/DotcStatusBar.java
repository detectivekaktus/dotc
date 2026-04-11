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

    private static final int MANA_BAR_HEIGHT = 9;
    private static final int CURRENT_MANA_COLOR = 0xFF257DAE;
    private static final int MANA_BAR_COLOR = 0xFF134D6E;

    private static final int ICON_TO_TEXT_MARGIN = 13;
    private static final int STAT_TO_STAT_MARGIN = 25;
    private static final int STATS_TO_MANA_MARGIN = 10;

    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private static int getStatusBarYPos(int height) {
        return height - (int) (HOTBAR_HEIGHT * 3.25f);
    }

    private static void drawMana(GuiGraphics context, int statusBarStartX, int x1, int y1) {
        var client = Minecraft.getInstance();
        var mana = Mana.get(client.player);
        var current = mana.getCurrentMana();
        var max = mana.getMaxMana();
        var manaPercent = current / max;

        var x2 = statusBarStartX + HOTBAR_WIDTH;
        var manaBarWidth = x2 - x1;
        var y2 = y1 + MANA_BAR_HEIGHT;
        context.fill(x1, y1, x2, y2, MANA_BAR_COLOR);

        var currentManaX = x1 + (int) (manaBarWidth * manaPercent);
        context.fill(x1, y1, currentManaX, y2, CURRENT_MANA_COLOR);

        var strX = (x1 + x2) / 2;
        var str = String.valueOf((int) current);
        context.drawCenteredString(client.font, str, strX, y1, TEXT_COLOR);
    }

    private static int drawIconAndValue(GuiGraphics context, ResourceLocation icon, int value, int x, int y) {
        var client = Minecraft.getInstance();
        context.blit(icon, x, y, 0, 0, 8, 8, 8, 8);

        var strX = x + ICON_TO_TEXT_MARGIN;
        var strValue = String.valueOf(value);
        context.drawString(client.font, strValue, strX, y, TEXT_COLOR);

        return strX + STAT_TO_STAT_MARGIN;
    }

    private static int drawStats(GuiGraphics context, int x, int y) {
        var client = Minecraft.getInstance();
        var stats = Stats.get(client.player);
        x = drawIconAndValue(context, STRENGTH_ICON, stats.getStrength(), x, y);
        x = drawIconAndValue(context, AGILITY_ICON, stats.getAgility(), x, y);
        x = drawIconAndValue(context, INTELLIGENCE_ICON, stats.getIntelligence(), x, y);
        return x;
    }

    public static void drawStatusBar(GuiGraphics context, DeltaTracker tickCounter) {
        var client = Minecraft.getInstance();
        if (client.options.hideGui) {
            return;
        }

        var width = client.getWindow().getGuiScaledWidth();
        var height = client.getWindow().getGuiScaledHeight();
        var statusBarStartX = (width - HOTBAR_WIDTH) / 2;
        var statusBarStartY = getStatusBarYPos(height);

        var statusBarAfterStatsX = drawStats(context, statusBarStartX, statusBarStartY);
        statusBarAfterStatsX += STATS_TO_MANA_MARGIN;
        drawMana(context, statusBarStartX, statusBarAfterStatsX, statusBarStartY);
    }
}
