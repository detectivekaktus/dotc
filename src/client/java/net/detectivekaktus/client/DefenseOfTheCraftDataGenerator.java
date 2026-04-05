package net.detectivekaktus.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.detectivekaktus.client.data.providers.*;

public class DefenseOfTheCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(DotcBlockTagProvider::new);
		pack.addProvider(DotcItemTagProvider::new);
		pack.addProvider(DotcRecipeProvider::new);
		pack.addProvider(DotcModelProvider::new);
		pack.addProvider(DotcBlockLootTableProvider::new);
	}
}
