package net.detectivekaktus.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.detectivekaktus.worldgen.features.StoneRadiantOrePlacedFeature;

public class DotcWorldgen {
    public static void initialize() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                StoneRadiantOrePlacedFeature.STONE_RADIANT_ORE_VEIN_PLACED_KEY
        );
    }
}
