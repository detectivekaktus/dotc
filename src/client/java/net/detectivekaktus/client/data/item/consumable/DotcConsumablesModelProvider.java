package net.detectivekaktus.client.data.item.consumable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import java.util.stream.Stream;

import net.detectivekaktus.item.consumable.DotcConsumables;

public class DotcConsumablesModelProvider extends FabricModelProvider {
    public DotcConsumablesModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        Stream.of(
                DotcConsumables.TANGO
        ).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:consumables_models";
    }
}
