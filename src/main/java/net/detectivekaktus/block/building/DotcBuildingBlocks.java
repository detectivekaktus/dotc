package net.detectivekaktus.block.building;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.detectivekaktus.block.DotcBlocks;

public class DotcBuildingBlocks {
    public static final Block RADIANT_CRYSTAL_BLOCK = DotcBlocks.register(
            new Block(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.AMETHYST)),
            "radiant_crystal_block"
    );
    public static final Block DIRE_CRYSTAL_BLOCK = DotcBlocks.register(
            new Block(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.AMETHYST)),
            "dire_crystal_block"
    );

    public static void initialize() { }
}
