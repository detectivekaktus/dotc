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
            var player = (ServerPlayer) (Object) ServerPlayerMixin.this;
            var config = new DotcPlayerManager.Config(player);
            var hotbarItems = player.getInventory().items.subList(0, 9);

            for (var item : hotbarItems) {
                if (item.has(DotcComponents.ITEM_STATS_COMPONENT)) {
                    var stats = item.get(DotcComponents.ITEM_STATS_COMPONENT);
                    config.addStats(stats);
                }

                if (item.has(DotcComponents.EVASION_COMPONENT)) {
                    var evasion = item.get(DotcComponents.EVASION_COMPONENT);
                    config.addEvasion(evasion);
                }

                if (item.has(DotcComponents.HP_REGEN_AMPLIFICATION_COMPONENT)) {
                    var amplification = item.get(DotcComponents.HP_REGEN_AMPLIFICATION_COMPONENT);
                    config.addHpRegenAmplification(amplification);
                }

                if (item.has(DotcComponents.MOVE_SPEED_COMPONENT)) {
                    var moveSpeed = item.get(DotcComponents.MOVE_SPEED_COMPONENT);
                    config.addMoveSpeed(moveSpeed);
                }
            }

            DotcPlayerManager.applyChanges(config);
        }

        @Override
        public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int j) { }
    };

    @Inject(at = @At("HEAD"), method = "initMenu")
    public void initMenu(AbstractContainerMenu abstractContainerMenu, CallbackInfo ci) {
        abstractContainerMenu.addSlotListener(dotc$playerInvListener);
    }
}
