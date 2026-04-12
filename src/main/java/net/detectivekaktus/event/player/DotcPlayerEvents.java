package net.detectivekaktus.event.player;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class DotcPlayerEvents {
    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            ManaRegenEvent.manaRegenTick(server);
            HpRegenEvent.hpRegenTick(server);
        });
    }
}
