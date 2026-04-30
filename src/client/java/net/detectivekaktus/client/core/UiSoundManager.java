package net.detectivekaktus.client.core;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.sound.gui.DotcGuiSounds;

public class UiSoundManager {
    public static boolean playCooldownSound(boolean isOnCooldown, Player player) {
        if (!isOnCooldown)
            return false;

        var level = player.level();
        if (level.isClientSide)
            level.playLocalSound(
                    player,
                    DotcGuiSounds.UI_COOLDOWN,
                    SoundSource.PLAYERS,
                    1.0f, 1.0f
            );
        return true;
    }
}
