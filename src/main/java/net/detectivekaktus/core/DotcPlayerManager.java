package net.detectivekaktus.core;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attach.DotcAttachmentRules;
import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.component.records.ItemStatsComponent;

// Probably all of these values will be changed during future tests by the players
// but for now I think they are reasonably balanced
public class DotcPlayerManager {
    private static final float BASE_ARMOR = 0.0f; // vanilla base armor value
    private static final float BASE_ATTACK_SPEED = 4.0f; // see Attributes class

    private static final float HP_PER_STRENGTH = 0.25f;
    private static final float HP_REGEN_PER_STRENGTH = 0.025f;

    private static final float ARMOR_PER_AGILITY = 0.1f;
    private static final float ATTACK_SPEED_PER_AGILITY = 0.033f;

    private static final float MANA_PER_INTELLIGENCE = 1.0f;
    private static final float MANA_REGEN_PER_INTELLIGENCE = 0.2f;
    private static final float MAGIC_RESISTANCE_PER_INTELLIGENCE = 0.0025f;

    private static boolean hasStatChanges(PlayerStats.StatsData stats, Config config) {
        return stats.getStrength() != config.strength
                || stats.getAgility() != config.agility
                || stats.getIntelligence() != config.intelligence
                || stats.getEvasion() != config.evasion
                || stats.getHpRegenAmplification() != config.hpRegenAmplification;
    }

    public static void applyChanges(Config config) {
        var stats = PlayerStats.get(config.player);
        if (!hasStatChanges(stats, config))
            return;

        stats.setStrength(config.strength);
        applyStrength(config.player, config.strength);
        stats.setHpRegenAmplification(config.hpRegenAmplification);

        stats.setAgility(config.agility);
        applyAgility(config.player, config.agility);

        stats.setIntelligence(config.intelligence);
        applyIntelligence(config.player, config.intelligence);

        stats.setEvasion(config.evasion);
        stats.setEvasionScale(0);
    }

    private static void applyStrength(Player player, int val) {
        var maxHpAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHpAttr != null) {
            var hp = Player.MAX_HEALTH + (val * HP_PER_STRENGTH);
            maxHpAttr.setBaseValue(hp);
        }

        var stats = PlayerStats.get(player);
        var hpRegen = DotcAttachmentRules.DEFAULT_HP_REGEN + (val * HP_REGEN_PER_STRENGTH);
        stats.setHpRegen(hpRegen);
    }

    private static void applyAgility(Player player, int val) {
        var armorAttr = player.getAttribute(Attributes.ARMOR);
        if (armorAttr != null) {
            var armor = BASE_ARMOR + (val * ARMOR_PER_AGILITY);
            armorAttr.setBaseValue(armor);
        }

        var attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr != null) {
            var attackSpeed = BASE_ATTACK_SPEED + (val * ATTACK_SPEED_PER_AGILITY);
            attackSpeedAttr.setBaseValue(attackSpeed);
        }
    }

    private static void applyIntelligence(Player player, int val) {
        var mana = PlayerMana.get(player);
        var maxMana = DotcAttachmentRules.DEFAULT_MAX_MANA + (val * MANA_PER_INTELLIGENCE);
        mana.setMaxMana(maxMana);

        var manaRegen = DotcAttachmentRules.DEFAULT_MANA_REGEN + (val * MANA_REGEN_PER_INTELLIGENCE);
        mana.setManaRegen(manaRegen);

        var stats = PlayerStats.get(player);
        var magicResistance = DotcAttachmentRules.DEFAULT_MAGIC_RESISTANCE + (val * MAGIC_RESISTANCE_PER_INTELLIGENCE);
        stats.setMagicResistance(magicResistance);
    }

    public static class Config {
        public final Player player;

        int strength, agility, intelligence;
        float evasion, hpRegenAmplification;

        public Config(Player player) {
            this.player = player;

            this.strength = 0;
            this.agility = 0;
            this.intelligence = 0;
            this.evasion = 0.0f;
            this.hpRegenAmplification = 0.0f;
        }

        public void addStats(ItemStatsComponent component) {
            this.strength += component.strength();
            this.agility += component.agility();
            this.intelligence += component.intelligence();
        }

        public void addEvasion(float evasion) {
            this.evasion = 1.0f - (1.0f - this.evasion) * (1.0f - evasion);
        }

        public void addHpRegenAmplification(float amplification) {
            this.hpRegenAmplification = amplification;
        }
    }
}
