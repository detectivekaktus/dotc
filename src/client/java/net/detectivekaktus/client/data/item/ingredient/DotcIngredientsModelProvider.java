package net.detectivekaktus.client.data.item.ingredient;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import java.util.stream.Stream;

import net.detectivekaktus.item.ingredient.DotcIngredients;

public class DotcIngredientsModelProvider extends FabricModelProvider {
    public DotcIngredientsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        Stream.of(
                DotcIngredients.RADIANT_CRYSTAL,
                DotcIngredients.RADIANT_CRYSTAL_DUST,
                DotcIngredients.DIRE_CRYSTAL,
                DotcIngredients.DIRE_CRYSTAL_DUST,
                DotcIngredients.MITHRIL_INGOT,
                DotcIngredients.BLIGHT_STONE,
                DotcIngredients.BLADES_OF_ATTACK,
                DotcIngredients.TALISMAN_OF_EVASION,
                DotcIngredients.EAGLESONG,
                DotcIngredients.SACRED_RELIC,
                DotcIngredients.GLOVES_OF_HASTE,
                DotcIngredients.BELT_OF_STRENGTH,
                DotcIngredients.BAND_OF_ELVENSKIN,
                DotcIngredients.ROBE_OF_THE_MAGI
        ).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:ingredient_models";
    }
}
