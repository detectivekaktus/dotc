package net.detectivekaktus.client.mixin.render;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.client.render.DotcItemModels;

import java.util.List;
import java.util.Map;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {
    @Shadow
    private void loadSpecialItemModelAndDependencies(ModelResourceLocation modelResourceLocation) {}

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V",
                    shift = At.Shift.AFTER,
                    ordinal = 1
            )
    )
    private void loadDotc3dModels(
            BlockColors blockColors,
            ProfilerFiller profilerFiller,
            Map<ResourceLocation, BlockModel> map,
            Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> map2,
            CallbackInfo callbackInfo
    ) {
        loadSpecialItemModelAndDependencies(DotcItemModels.MONKEY_KING_BAR_IN_HAND);
        loadSpecialItemModelAndDependencies(DotcItemModels.CRYSTALYS_IN_HAND);
        loadSpecialItemModelAndDependencies(DotcItemModels.DAEDALUS_IN_HAND);
    }
}
