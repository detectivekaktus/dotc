package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

import net.detectivekaktus.client.core.WordWrapper;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
    @ModifyVariable(
            method = "renderTooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;II)V",
            at = @At(value = "HEAD")
    )
    private List<Component> wordWrapTooltipsInInventory(List<Component> original) {
        return WordWrapper.wrapComponents(original);
    }

    @ModifyVariable(
            method = "renderComponentTooltip(Lnet/minecraft/client/gui/Font;Ljava/util/List;II)V",
            at = @At(value = "HEAD")
    )
    private List<Component> wordWrapTooltipsElsewhere(List<Component> original) {
        return WordWrapper.wrapComponents(original);
    }
}
