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
import net.detectivekaktus.block.natural.DotcNaturalBlocks;

import java.util.List;

public class DireOreConfiguredFeature {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIRE_ORE_VEIN_CONFIGURED_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "dire_ore_vein")
    );

    public static void configure(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var netherrackRule = new TagMatchTest(BlockTags.BASE_STONE_NETHER);

        var direOreConfig = List.of(
                OreConfiguration.target(netherrackRule, DotcNaturalBlocks.DIRE_ORE.defaultBlockState())
        );

        context.register(
                DIRE_ORE_VEIN_CONFIGURED_KEY, new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(direOreConfig, 4)
                )
        );
    }
}
