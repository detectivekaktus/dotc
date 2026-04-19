package net.detectivekaktus.client.data.item.tool;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import java.util.stream.Stream;

import net.detectivekaktus.item.tool.DotcTools;

public class DotcToolsModelProvider extends FabricModelProvider {
    public DotcToolsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        Stream.of(
                DotcTools.JAVELIN,
                DotcTools.CRYSTALYS,
                DotcTools.DAEDALUS,
                DotcTools.MONKEY_KING_BAR
        ).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM));
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:tools_models";
    }
}
