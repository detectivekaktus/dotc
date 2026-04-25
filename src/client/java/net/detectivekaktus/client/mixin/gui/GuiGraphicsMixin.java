package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    @ModifyVariable(
            method = "renderTooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;II)V",
            at = @At(value = "HEAD")
    )
    private List<Component> wordWrapTooltip(List<Component> original) {
        var result = new ArrayList<Component>();

        var maxCharsPerLine = 32;
        for (var component : original) {
            var str = component.getString();

            var start = 0;
            var end = maxCharsPerLine;
            while (start < str.length()) {
                if (end >= str.length()) {
                    end = str.length();
                    result.add(
                            Component.literal(str.substring(start, end))
                                    .withStyle(component.getStyle())
                    );
                    break;
                }
                // If it's a German word, aka fuckass long word
                else if (start == end) {
                    result.add(component);
                    break;
                }

                if (str.charAt(end) == ' ') {
                    result.add(
                            Component.literal(str.substring(start, end))
                                    .withStyle(component.getStyle())
                    );
                    start = end + 1;
                    end = start + maxCharsPerLine;
                }
                else {
                    end--;
                }
            }
        }

        return result;
    }
}
