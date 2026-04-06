package net.detectivekaktus.client;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.detectivekaktus.client.data.providers.*;
import net.detectivekaktus.worldgen.features.RadiantOreConfiguredFeature;
import net.detectivekaktus.worldgen.features.RadiantOrePlacedFeature;
import net.detectivekaktus.worldgen.features.StoneRadiantOreConfiguredFeature;
import net.detectivekaktus.worldgen.features.StoneRadiantOrePlacedFeature;

public class DefenseOfTheCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(DotcBlockTagProvider::new);
		pack.addProvider(DotcItemTagProvider::new);
		pack.addProvider(DotcRecipeProvider::new);
		pack.addProvider(DotcModelProvider::new);
		pack.addProvider(DotcBlockLootTableProvider::new);
		pack.addProvider(DotcDynamicRegistryProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, StoneRadiantOreConfiguredFeature::configure);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, RadiantOreConfiguredFeature::configure);

		registryBuilder.add(Registries.PLACED_FEATURE, StoneRadiantOrePlacedFeature::configure);
		registryBuilder.add(Registries.PLACED_FEATURE, RadiantOrePlacedFeature::configure);
	}
}
