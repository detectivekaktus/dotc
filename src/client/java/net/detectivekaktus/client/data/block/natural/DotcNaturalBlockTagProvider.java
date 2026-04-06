package net.detectivekaktus.client.data.block.natural;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.block.natural.DotcNaturalBlocks;

public class DotcNaturalBlockTagProvider extends FabricTagProvider<Block> {
    public DotcNaturalBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DotcNaturalBlocks.RADIANT_ORE)
                .add(DotcNaturalBlocks.DEEPSLATE_RADIANT_ORE)
                .add(DotcNaturalBlocks.DIRE_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(DotcNaturalBlocks.RADIANT_ORE)
                .add(DotcNaturalBlocks.DEEPSLATE_RADIANT_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(DotcNaturalBlocks.DIRE_ORE);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:natural_block_tags";
    }
}
