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

import net.detectivekaktus.component.dummy.DummyComponents;
import net.detectivekaktus.core.DotcPlayerManager;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Unique
    private final ContainerListener listener = new ContainerListener() {
        @Override
        public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
            var strength = 0;
            var agility = 0;
            var intelligence = 0;

            var player = (ServerPlayer) (Object) ServerPlayerMixin.this;
            var hotbarItems = player.getInventory().items.subList(0, 8);
            for (var item : hotbarItems) {
                if (!item.has(DummyComponents.ITEM_STATS_COMPONENT))
                    continue;

                var stats = item.get(DummyComponents.ITEM_STATS_COMPONENT);
                strength += stats.strength();
                agility += stats.agility();
                intelligence += stats.intelligence();
            }

            var manager = new DotcPlayerManager(player);
            manager.changeStats(strength, agility, intelligence);
        }

        @Override
        public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int j) { }
    };

    @Inject(at = @At("HEAD"), method = "initMenu")
    public void initMenu(AbstractContainerMenu abstractContainerMenu, CallbackInfo ci) {
        abstractContainerMenu.addSlotListener(listener);
    }
}
