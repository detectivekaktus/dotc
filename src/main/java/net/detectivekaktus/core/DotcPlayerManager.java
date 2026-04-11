package net.detectivekaktus.core;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.attach.PlayerStats;

public class DotcPlayerManager {
    private static final float HP_PER_STRENGTH = 0.5f;
    private static final float ARMOR_PER_AGILITY = 0.2f;
    private static final float MANA_PER_INTELLIGENCE = 1.0f;

    public static void applyStatChanges(Player player, int strength, int agility, int intelligence) {
        var stats = PlayerStats.get(player);
        var prevStrength = stats.setStrength(strength);
        applyStrength(player, strength, prevStrength);

        var prevAgility = stats.setAgility(agility);
        applyAgility(player, agility, prevAgility);

        var prevIntelligence = stats.setIntelligence(intelligence);
        applyIntelligence(player, intelligence, prevIntelligence);
    }

    private static void applyStrength(Player player, int current, int previous) {
        var strengthDelta = current - previous;
        var maxHpAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHpAttr != null) {
            var hp = player.getMaxHealth() + (strengthDelta * HP_PER_STRENGTH);
            maxHpAttr.setBaseValue(hp);
        }
    }

    private static void applyAgility(Player player, int current, int previous) {
        var agilityDelta = current - previous;
        var armorAttr = player.getAttribute(Attributes.ARMOR);
        if (armorAttr != null) {
            var armor = armorAttr.getValue() + agilityDelta * ARMOR_PER_AGILITY;
            armorAttr.setBaseValue(armor);
        }
    }

    private static void applyIntelligence(Player player, int current, int previous) {
        var intelligenceDelta = current - previous;
        var mana = PlayerMana.get(player);
        var maxMana = mana.getMaxMana() + (int) (intelligenceDelta * MANA_PER_INTELLIGENCE);
        mana.setMaxMana(maxMana);
    }
}
