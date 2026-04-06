package net.detectivekaktus.client.data.block.natural;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

import java.util.stream.Stream;

import net.detectivekaktus.block.natural.DotcNaturalBlocks;

public class DotcNaturalBlockModelProvider extends FabricModelProvider {
    public DotcNaturalBlockModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        Stream.of(
                DotcNaturalBlocks.RADIANT_ORE,
                DotcNaturalBlocks.DEEPSLATE_RADIANT_ORE,
                DotcNaturalBlocks.DIRE_ORE
        ).forEach(blockStateModelGenerator::createTrivialCube);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    @Override
    public String getName() {
        return "defense-of-the-craft:natural_block_models";
    }
}
