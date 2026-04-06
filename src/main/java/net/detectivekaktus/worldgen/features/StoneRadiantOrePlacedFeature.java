package net.detectivekaktus.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import net.detectivekaktus.DefenseOfTheCraft;

import java.util.List;

public class StoneRadiantOrePlacedFeature {
    public static final ResourceKey<PlacedFeature> STONE_RADIANT_ORE_VEIN_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "stone_radiant_ore_vein_placed")
    );

    public static void configure(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        var modifiers = List.of(
                CountPlacement.of(2),
                BiomeFilter.biome(),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(
                        TrapezoidHeight.of(VerticalAnchor.absolute(-2), VerticalAnchor.absolute(10))
                )
        );
        context.register(
                STONE_RADIANT_ORE_VEIN_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(StoneRadiantOreConfiguredFeature.STONE_RADIANT_ORE_VEIN_CONFIGURED_KEY),
                        modifiers
                )
        );
    }
}
