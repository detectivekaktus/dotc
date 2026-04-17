package net.detectivekaktus.client.mixin.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.item.tool.DotcTools;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Unique
    private static final ModelResourceLocation DOTC_MONKEY_KING_BAR = ModelResourceLocation.inventory(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "monkey_king_bar")
    );
    @Unique
    private static final ModelResourceLocation DOTC_MONKEY_KING_BAR_IN_HAND = ModelResourceLocation.inventory(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "monkey_king_bar_in_hand")
    );

    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    @ModifyVariable(
            method = "getModel",
            at = @At(value = "STORE"),
            name = "bakedModel"
    )
    private BakedModel addDotcItemModelsInGetModel(
            BakedModel original, ItemStack itemStack, Level level, LivingEntity livingEntity, int i
    ) {
        if (itemStack.is(DotcTools.MONKEY_KING_BAR))
            return itemModelShaper.getModelManager().getModel(DOTC_MONKEY_KING_BAR_IN_HAND);
        return original;
    }

    @ModifyVariable(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/model/BakedModel;getTransforms()Lnet/minecraft/client/renderer/block/model/ItemTransforms;",
                    shift = At.Shift.BEFORE
            ),
            name = "bakedModel"
    )
    private BakedModel addDotcItemModelsInRender(
            BakedModel original,
            ItemStack itemStack,
            ItemDisplayContext context,
            boolean bl,
            PoseStack poseStack,
            MultiBufferSource multiBufferSource,
            int i,
            int j
    ) {
        if (context == ItemDisplayContext.GUI || context == ItemDisplayContext.FIXED) {
            if (itemStack.is(DotcTools.MONKEY_KING_BAR))
                return itemModelShaper.getModelManager().getModel(DOTC_MONKEY_KING_BAR);
        }
        return original;
    }
}
