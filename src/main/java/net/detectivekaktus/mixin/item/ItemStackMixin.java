package net.detectivekaktus.mixin.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.detectivekaktus.core.item.ItemStackHelper;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
            method = "use",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> callbackInfo) {
        var val = ItemStackHelper.cancelInteractionIfStunned(player, level, hand);
        if (val != null)
            callbackInfo.setReturnValue(val);
    }

    @Inject(
            method = "useOn",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelUseOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> callbackInfo) {
        var player = useOnContext.getPlayer();
        if (player == null)
            return;

        var val = ItemStackHelper.cancelInteractionIfStunned(player);
        if (val != null)
            callbackInfo.setReturnValue(val);
    }

    @Inject(
            method = "interactLivingEntity",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelInteractLivingEntity(Player player, LivingEntity livingEntity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> callbackInfo) {
        var val = ItemStackHelper.cancelInteractionIfStunned(player);
        if (val != null)
            callbackInfo.setReturnValue(val);
    }
}
