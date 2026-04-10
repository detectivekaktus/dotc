package net.detectivekaktus.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import net.detectivekaktus.attach.Mana;

public class DotcHudEvents {
    public static void drawManaStatus(GuiGraphics context, DeltaTracker tickCounter) {
        var client = Minecraft.getInstance();
        var mana = Mana.get(client.player);

        var current = mana.getCurrentMana();
        var max = mana.getMaxMana();
        var str = String.format("Mana: %d/%d", (int) current, (int) max);

        var width = client.getWindow().getGuiScaledWidth();
        var height = client.getWindow().getGuiScaledHeight();
        var x = width / 2;
        var y = height - 60;
        context.drawCenteredString(client.font, str, x, y, 0xFFFFFFFF);
    }
}
