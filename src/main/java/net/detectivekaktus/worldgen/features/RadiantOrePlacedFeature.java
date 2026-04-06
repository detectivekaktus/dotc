package net.detectivekaktus.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;

import net.detectivekaktus.DefenseOfTheCraft;

import java.util.List;

public class RadiantOrePlacedFeature {
    public static final ResourceKey<PlacedFeature> RADIANT_ORE_VEIN_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "radiant_ore_vein_placed")
    );

    public static void configure(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        var modifiers = List.of(
                CountPlacement.of(4),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(
                        UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(-15))
                )
        );
        context.register(
                RADIANT_ORE_VEIN_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(RadiantOreConfiguredFeature.RADIANT_ORE_VEIN_CONFIGURED_KEY),
                        modifiers
                )
        );
    }
}
