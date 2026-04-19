package net.detectivekaktus.client;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.detectivekaktus.client.data.DotcBlockTagProvider;
import net.detectivekaktus.client.data.DotcDynamicRegistryProvider;
import net.detectivekaktus.client.data.block.building.DotcBuildingBlockLootTableProvider;
import net.detectivekaktus.client.data.block.building.DotcBuildingBlockModelProvider;
import net.detectivekaktus.client.data.block.building.DotcBuildingBlockRecipeProvider;
import net.detectivekaktus.client.data.block.natural.DotcNaturalBlockLootTableProvider;
import net.detectivekaktus.client.data.block.natural.DotcNaturalBlockModelProvider;
import net.detectivekaktus.client.data.item.ingredient.DotcIngredientsModelProvider;
import net.detectivekaktus.client.data.item.ingredient.DotcIngredientsRecipeProvider;
import net.detectivekaktus.client.data.DotcItemTagProvider;
import net.detectivekaktus.client.data.item.component.DotcComponentsModelProvider;
import net.detectivekaktus.client.data.item.component.DotcComponentsRecipeProvider;
import net.detectivekaktus.client.data.item.tool.DotcToolsModelProvider;
import net.detectivekaktus.client.data.item.tool.DotcToolsRecipeProvider;
import net.detectivekaktus.worldgen.features.*;

public class DefenseOfTheCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(DotcItemTagProvider::new);
		pack.addProvider(DotcBlockTagProvider::new);

		pack.addProvider(DotcNaturalBlockModelProvider::new);
		pack.addProvider(DotcNaturalBlockLootTableProvider::new);

		pack.addProvider(DotcBuildingBlockModelProvider::new);
		pack.addProvider(DotcBuildingBlockRecipeProvider::new);
		pack.addProvider(DotcBuildingBlockLootTableProvider::new);

		pack.addProvider(DotcIngredientsModelProvider::new);
		pack.addProvider(DotcIngredientsRecipeProvider::new);

		pack.addProvider(DotcComponentsModelProvider::new);
		pack.addProvider(DotcComponentsRecipeProvider::new);

		pack.addProvider(DotcToolsModelProvider::new);
		pack.addProvider(DotcToolsRecipeProvider::new);

		pack.addProvider(DotcDynamicRegistryProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, StoneRadiantOreConfiguredFeature::configure);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, RadiantOreConfiguredFeature::configure);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, DireOreConfiguredFeature::configure);

		registryBuilder.add(Registries.PLACED_FEATURE, StoneRadiantOrePlacedFeature::configure);
		registryBuilder.add(Registries.PLACED_FEATURE, RadiantOrePlacedFeature::configure);
		registryBuilder.add(Registries.PLACED_FEATURE, DireOrePlacedFeature::configure);
	}
}
