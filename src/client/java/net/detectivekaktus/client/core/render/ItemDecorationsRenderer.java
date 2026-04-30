package net.detectivekaktus.client.core.render;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class ItemDecorationsRenderer {
    // This is basically how vanilla item decoration is rendered. I straight
    // up copied from the `GuiGraphics` class.
    public static void drawStringInItemIconCorner(
            GuiGraphics graphics,
            Font font,
            String str,
            int i, int j, int color
    ) {
        graphics.pose().translate(0.0f, 0.0f, 200.0f);
        graphics.drawString(font, str, i + 17 - font.width(str), j + 9, color, true);
    }
}
