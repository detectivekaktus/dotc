package net.detectivekaktus.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.block.building.DotcBuildingBlocks;
import net.detectivekaktus.block.natural.DotcNaturalBlocks;

public class DotcBlocks {
    public static Block register(Block block, String name) {
        return register(block, name, true);
    }

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, id, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {
        DotcNaturalBlocks.initialize();
        DotcBuildingBlocks.initialize();
    }
}
