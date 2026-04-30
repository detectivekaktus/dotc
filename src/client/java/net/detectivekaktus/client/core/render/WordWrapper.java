package net.detectivekaktus.client.core.render;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class WordWrapper {
    public static List<Component> wrapComponents(List<Component> components) {
        var result = new ArrayList<Component>(components.size());

        var maxCharsPerLine = 32;
        for (var component : components) {
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
                // makes hard wrap
                else if (start == end) {
                    end = Math.min(end + maxCharsPerLine, str.length());
                    result.add(
                            Component.literal(str.substring(start, end))
                                    .withStyle(component.getStyle())
                    );
                    start = end + 1;
                    end = start + maxCharsPerLine;
                    continue;
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
