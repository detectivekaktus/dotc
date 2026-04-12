package net.detectivekaktus.event.player;

import net.detectivekaktus.attach.PlayerStats;
import net.minecraft.world.entity.player.Player;

public class HpRegenEvent {
    public static void tick(Player player) {
        var stats = PlayerStats.get(player);
        var hpTick = stats.getHpTick();

        if (hpTick >= 20) {
            stats.setHpTick(0);
            var regen = stats.getHpRegen();
            if (regen > 0)
                player.heal(regen);
            return;
        }

        stats.setHpTick(++hpTick);
    }
}
