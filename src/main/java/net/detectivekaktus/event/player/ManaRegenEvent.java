package net.detectivekaktus.event.player;

import net.detectivekaktus.attach.PlayerMana;
import net.minecraft.world.entity.player.Player;

public class ManaRegenEvent {
    public static void tick(Player player) {
        var mana = PlayerMana.get(player);
        var manaTick = mana.getManaTick();

        if (manaTick >= 20) {
            mana.setManaTick(0);
            var regen = mana.getManaRegen();
            mana.increment(regen);
            return;
        }

        mana.setManaTick(++manaTick);
    }
}
