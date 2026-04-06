package net.detectivekaktus.block.natural;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.detectivekaktus.block.DotcBlocks;

public class DotcNaturalBlocks {
    public static final Block RADIANT_ORE = DotcBlocks.register(
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)),
            "radiant_ore"
    );
    public static final Block DEEPSLATE_RADIANT_ORE = DotcBlocks.register(
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)),
            "deepslate_radiant_ore"
    );
    public static final Block DIRE_ORE = DotcBlocks.register(
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE)),
            "dire_ore"
    );

    public static void initialize() { }
}
