package net.detectivekaktus.client.mixin.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import net.detectivekaktus.client.core.render.ItemDecorationsRenderer;
import net.detectivekaktus.client.core.render.WordWrapper;
import net.detectivekaktus.client.core.render.DotcColors;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.item.tool.HasManaCost;

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

    @Inject(
            method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"
            )
    )
    private void renderDotcItemDecorations(Font font, ItemStack itemStack, int i, int j, String string, CallbackInfo callbackInfo) {
        var graphics = (GuiGraphics) (Object) this;

        if (itemStack.getItem() instanceof HasManaCost itemWithManaCost) {
            var manaCost = String.valueOf((int) itemWithManaCost.getManaCost());
            ItemDecorationsRenderer.drawStringInItemIconCorner(graphics, font, manaCost, i, j, DotcColors.MANA_COST_COLOR);
        }
        else if (itemStack.has(DotcComponents.CHARGEABLE_COMPONENT)) {
            // Technically this can overlap with the item quantity but chargeable items
            // are intended to be unstackable, so I will just assume they can never overlap
            // with quantity
            var component = itemStack.get(DotcComponents.CHARGEABLE_COMPONENT);
            var charges = String.valueOf(component.charges());
            ItemDecorationsRenderer.drawStringInItemIconCorner(graphics, font, charges, i, j, DotcColors.TEXT_COLOR);
        }
    }
}
