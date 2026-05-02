package net.detectivekaktus.core.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.detectivekaktus.effect.DotcEffects;
import net.detectivekaktus.sound.gui.DotcGuiSounds;

public class ItemStackHelper {
    public static InteractionResultHolder<ItemStack> cancelInteractionIfStunned(Player player, Level level, InteractionHand hand) {
        if (!player.hasEffect(DotcEffects.STUN))
            return null;

        if (level.isClientSide)
            player.playSound(DotcGuiSounds.UI_GENERAL_DENY);

        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }

    public static InteractionResult cancelInteractionIfStunned(Player player) {
        if (!player.hasEffect(DotcEffects.STUN))
            return null;

        if (player.level().isClientSide)
            player.playSound(DotcGuiSounds.UI_GENERAL_DENY);

        return InteractionResult.FAIL;
    }
}
