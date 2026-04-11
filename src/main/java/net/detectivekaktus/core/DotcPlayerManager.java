package net.detectivekaktus.core;

import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attach.PlayerStats;

public class DotcPlayerManager {
    private final Player player;

    public DotcPlayerManager(Player player) {
        this.player = player;
    }

    public void changeStats(int strength, int agility, int intelligence) {
        var stats = PlayerStats.get(player);
        stats.setStrength(strength);
        stats.setAgility(agility);
        stats.setIntelligence(intelligence);
    }
}
