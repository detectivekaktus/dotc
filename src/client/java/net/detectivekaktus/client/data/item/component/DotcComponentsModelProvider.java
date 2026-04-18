package net.detectivekaktus.client.data.item.component;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import java.util.stream.Stream;

import net.detectivekaktus.item.component.DotcItemComponents;

public class DotcComponentsModelProvider extends FabricModelProvider {
    public DotcComponentsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        Stream.of(
                DotcItemComponents.BLADES_OF_ATTACK,
                DotcItemComponents.MITHRIL_HAMMER,
                DotcItemComponents.BROADSWORD,
                DotcItemComponents.CLAYMORE,
                DotcItemComponents.BLADE_OF_ALACRITY,
                DotcItemComponents.OGRE_AXE,
                DotcItemComponents.STAFF_OF_WIZARDRY,
                DotcItemComponents.DEMON_EDGE
        ).forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM));
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:components_models";
    }
}
