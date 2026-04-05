package net.detectivekaktus.client.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.block.DotcBlocks;

public class DotcBlockTagProvider extends FabricTagProvider<Block> {
    public DotcBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DotcBlocks.RADIANT_ORE)
                .add(DotcBlocks.DEEPSLATE_RADIANT_ORE)
                .add(DotcBlocks.DIRE_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(DotcBlocks.RADIANT_ORE)
                .add(DotcBlocks.DEEPSLATE_RADIANT_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(DotcBlocks.DIRE_ORE);
    }
}
