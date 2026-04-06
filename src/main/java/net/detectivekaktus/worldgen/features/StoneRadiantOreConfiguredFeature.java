package net.detectivekaktus.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.block.DotcBlocks;

import java.util.List;

public class StoneRadiantOreConfiguredFeature {
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_RADIANT_ORE_VEIN_CONFIGURED_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "stone_radiant_ore_vein")
    );

    public static void configure(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var stoneReplaceableRule = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        var radiantOreConfig = List.of(
                OreConfiguration.target(stoneReplaceableRule, DotcBlocks.RADIANT_ORE.defaultBlockState())
        );

        context.register(
                STONE_RADIANT_ORE_VEIN_CONFIGURED_KEY, new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(radiantOreConfig, 3)
                )
        );
    }
}
