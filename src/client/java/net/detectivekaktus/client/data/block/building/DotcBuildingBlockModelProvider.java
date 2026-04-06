package net.detectivekaktus.client.data.block.building;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

import java.util.stream.Stream;

import net.detectivekaktus.block.building.DotcBuildingBlocks;

public class DotcBuildingBlockModelProvider extends FabricModelProvider {
    public DotcBuildingBlockModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        Stream.of(
                DotcBuildingBlocks.RADIANT_CRYSTAL_BLOCK,
                DotcBuildingBlocks.DIRE_CRYSTAL_BLOCK
        ).forEach(blockStateModelGenerator::createTrivialCube);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    @Override
    public String getName() {
        return "defense-of-the-craft:building_block_models";
    }
}
