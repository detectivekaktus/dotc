package net.detectivekaktus.item.consumable;

import net.detectivekaktus.sound.item.DotcItemSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.item.TooltipBuilder;

public class Tango extends DotcItem {
    public Tango(Properties properties, TooltipBuilder tooltipBuilder) {
        super(properties, tooltipBuilder);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        var level = useOnContext.getLevel();
        var blockPos = useOnContext.getClickedPos();
        if (!isUsedOnLeaves(level, blockPos))
            return InteractionResult.PASS;

        var player = useOnContext.getPlayer();
        var stack = useOnContext.getItemInHand();

        level.removeBlock(blockPos, false);
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 1));
        stack.consume(1, player);
        level.playSound(player, blockPos, DotcItemSounds.TANGO, SoundSource.BLOCKS, 1.0f, 1.0f);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private boolean isUsedOnLeaves(Level level, BlockPos blockPos) {
        return level.getBlockState(blockPos).is(BlockTags.LEAVES);
    }
}
