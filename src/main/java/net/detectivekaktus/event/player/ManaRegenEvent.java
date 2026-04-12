package net.detectivekaktus.event.player;

import net.detectivekaktus.attach.PlayerMana;
import net.minecraft.server.MinecraftServer;

public class ManaRegenEvent {
    public static void manaRegenTick(MinecraftServer server) {
        var players = server.getPlayerList().getPlayers();
        for (var player : players) {
            var mana = PlayerMana.get(player);
            var manaTick = mana.getManaTick();

            if (manaTick >= 20) {
                mana.setManaTick(0);
                var regen = mana.getManaRegen();
                mana.increment(regen);
                continue;
            }

            mana.setManaTick(++manaTick);
        }
    }
}
