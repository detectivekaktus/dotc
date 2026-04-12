package net.detectivekaktus.event.player;

import net.detectivekaktus.attach.PlayerStats;
import net.minecraft.server.MinecraftServer;

public class HpRegenEvent {
    public static void hpRegenTick(MinecraftServer server) {
        var players = server.getPlayerList().getPlayers();
        for (var player : players) {
            var stats = PlayerStats.get(player);
            var hpTick = stats.getHpTick();

            if (hpTick >= 20) {
                stats.setHpTick(0);
                var regen = stats.getHpRegen();
                player.heal(regen);
                continue;
            }

            stats.setHpTick(++hpTick);
        }
    }
}
