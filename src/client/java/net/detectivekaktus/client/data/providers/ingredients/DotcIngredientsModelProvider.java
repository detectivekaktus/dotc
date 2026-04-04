package net.detectivekaktus.client.data.providers.ingredients;

import net.detectivekaktus.item.ingredients.DotcIngredients;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class DotcIngredientsModelProvider extends FabricModelProvider {
    public DotcIngredientsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(DotcIngredients.RADIANT_CRYSTAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DotcIngredients.RADIANT_CRYSTAL_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DotcIngredients.DIRE_CRYSTAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DotcIngredients.DIRE_CRYSTAL_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DotcIngredients.MITHRIL_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DotcIngredients.BLIGHT_STONE, ModelTemplates.FLAT_ITEM);
    }
}
