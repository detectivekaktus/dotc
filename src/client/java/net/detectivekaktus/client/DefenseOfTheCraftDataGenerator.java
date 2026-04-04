package net.detectivekaktus.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.detectivekaktus.client.data.providers.ingredients.DotcIngredientsRecipeProvider;
import net.detectivekaktus.client.data.providers.ingredients.DotcIngredientsTagProvider;
import net.detectivekaktus.client.data.providers.ingredients.DotcIngredientsModelProvider;

public class DefenseOfTheCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(DotcIngredientsTagProvider::new);
		pack.addProvider(DotcIngredientsRecipeProvider::new);
		pack.addProvider(DotcIngredientsModelProvider::new);
	}
}
