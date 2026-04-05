package net.detectivekaktus.client.data.providers;

import net.detectivekaktus.block.DotcBlocks;
import net.detectivekaktus.item.ingredients.DotcIngredients;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import java.util.stream.Stream;

public class DotcModelProvider extends FabricModelProvider {
    public DotcModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(DotcBlocks.RADIANT_ORE);
        blockStateModelGenerator.createTrivialCube(DotcBlocks.DEEPSLATE_RADIANT_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        Stream.of(
                DotcIngredients.RADIANT_CRYSTAL,
                DotcIngredients.RADIANT_CRYSTAL_DUST,
                DotcIngredients.DIRE_CRYSTAL,
                DotcIngredients.DIRE_CRYSTAL_DUST,
                DotcIngredients.MITHRIL_INGOT,
                DotcIngredients.BLIGHT_STONE,
                DotcIngredients.SACRED_RELIC,
                DotcIngredients.GLOVES_OF_HASTE
        ).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }
}
