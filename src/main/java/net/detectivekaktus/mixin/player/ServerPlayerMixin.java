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

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.core.DotcPlayerManager;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Unique
    private final ContainerListener dotc$playerInvListener = new ContainerListener() {
        @Override
        public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
            var strength = 0;
            var agility = 0;
            var intelligence = 0;
            var evasion = 0.0f;

            var player = (ServerPlayer) (Object) ServerPlayerMixin.this;
            var hotbarItems = player.getInventory().items.subList(0, 9);
            for (var item : hotbarItems) {
                if (item.has(DotcComponents.ITEM_STATS_COMPONENT)) {
                    var stats = item.get(DotcComponents.ITEM_STATS_COMPONENT);
                    strength += stats.strength();
                    agility += stats.agility();
                    intelligence += stats.intelligence();
                }

                if (item.has(DotcComponents.EVASION_COMPONENT)) {
                    var itemEvasion = item.get(DotcComponents.EVASION_COMPONENT);
                    evasion = 1.0f - (1.0f - evasion) * (1.0f - itemEvasion);
                }
            }

            DotcPlayerManager.applyStatChanges(player, strength, agility, intelligence, evasion);
        }

        @Override
        public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int j) { }
    };

    @Inject(at = @At("HEAD"), method = "initMenu")
    public void initMenu(AbstractContainerMenu abstractContainerMenu, CallbackInfo ci) {
        abstractContainerMenu.addSlotListener(dotc$playerInvListener);
    }
}
