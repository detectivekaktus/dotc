package net.detectivekaktus.mixin.player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.core.player.StatManager;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Unique
    private final ContainerListener dotc$playerInvListener = new ContainerListener() {
        @Override
        public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
            statManager.updateStats();
        }

        @Override
        public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int j) { }
    };

    @Inject(at = @At("HEAD"), method = "initMenu")
    public void initMenu(AbstractContainerMenu abstractContainerMenu, CallbackInfo ci) {
        abstractContainerMenu.addSlotListener(dotc$playerInvListener);
    }

    @Unique
    @Final
    private StatManager statManager;

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL")
    )
    private void addStatManager(MinecraftServer minecraftServer, ServerLevel serverLevel, GameProfile gameProfile, ClientInformation clientInformation, CallbackInfo callbackInfo) {
        var player = (ServerPlayer) (Object) this;
        this.statManager = new StatManager(player);
    }
}
