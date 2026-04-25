package net.detectivekaktus.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.core.player.PlayerManager;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Unique
    private final ContainerListener dotc$playerInvListener = new ContainerListener() {
        @Override
        public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
            var player = (ServerPlayer) (Object) ServerPlayerMixin.this;
            PlayerManager.updateStats(player);
        }

        @Override
        public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int j) { }
    };

    @Inject(at = @At("HEAD"), method = "initMenu")
    public void initMenu(AbstractContainerMenu abstractContainerMenu, CallbackInfo ci) {
        abstractContainerMenu.addSlotListener(dotc$playerInvListener);
    }
}
