package net.detectivekaktus.client.data.block.building;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.block.building.DotcBuildingBlocks;

public class DotcBuildingBlockLootTableProvider extends FabricBlockLootTableProvider {
    public DotcBuildingBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(DotcBuildingBlocks.RADIANT_CRYSTAL_BLOCK);
        dropSelf(DotcBuildingBlocks.DIRE_CRYSTAL_BLOCK);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:building_block_loottables";
    }
}
