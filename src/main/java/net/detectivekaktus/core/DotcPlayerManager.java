package net.detectivekaktus.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.attach.DotcAttachmentRules;
import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.component.records.ItemStatsComponent;

// Probably all of these values will be changed during future tests by the players
// but for now I think they are reasonably balanced
public class DotcPlayerManager {
    private static final float BASE_ARMOR = 0.0f;
    private static final float BASE_ATTACK_SPEED = 4.0f;

    private static final float HP_PER_STRENGTH = 0.25f;
    private static final float HP_REGEN_PER_STRENGTH = 0.025f;

    private static final float ARMOR_PER_AGILITY = 0.1f;
    private static final float ATTACK_SPEED_PER_AGILITY = 0.033f;

    private static final float MANA_PER_INTELLIGENCE = 1.0f;
    private static final float MANA_REGEN_PER_INTELLIGENCE = 0.2f;
    private static final float MAGIC_RESISTANCE_PER_INTELLIGENCE = 0.0025f;

    private static final ResourceLocation MOVE_SPEED_BONUS_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "move_speed_bonus");

    private static boolean hasStatChanges(PlayerStats.StatsData stats, PlayerMana.ManaData mana, Config config) {
        return stats.getStrength() != config.strength
                || stats.getAgility() != config.agility
                || stats.getIntelligence() != config.intelligence
                || Math.abs(stats.getEvasion() - config.evasion) > 1e-5f
                || Math.abs(stats.getHpRegenAmplification() - config.hpRegenAmplification) > 1e-5f
                || Math.abs(stats.getMoveSpeed() - config.moveSpeed) > 1e-5f
                || Math.abs(mana.getManaCostReduction() - config.manaCostReduction) > 1e-5f;
    }

    public static void applyChanges(Config config) {
        var stats = PlayerStats.get(config.player);
        var mana = PlayerMana.get(config.player);
        if (!hasStatChanges(stats, mana, config))
            return;

        stats.setStrength(config.strength);
        applyStrength(config.player, config.strength);
        stats.setHpRegenAmplification(config.hpRegenAmplification);

        stats.setAgility(config.agility);
        applyAgility(config.player, config.agility);
        applyMoveSpeed(config.player, config.moveSpeed);
        stats.setEvasion(config.evasion);
        stats.setEvasionScale(0);

        stats.setIntelligence(config.intelligence);
        applyIntelligence(config.player, config.intelligence);
        mana.setManaCostReduction(config.manaCostReduction);
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

    private static void applyMoveSpeed(Player player, float val) {
        var moveSpeedAttr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (moveSpeedAttr != null) {
            if (val == 0.0f)
                moveSpeedAttr.removeModifier(MOVE_SPEED_BONUS_MODIFIER_ID);
            else
                moveSpeedAttr.addOrUpdateTransientModifier(
                        new AttributeModifier(
                                MOVE_SPEED_BONUS_MODIFIER_ID,
                                val,
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                );
        }

        var stats = PlayerStats.get(player);
        stats.setMoveSpeed(val);
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
        float evasion, moveSpeed, hpRegenAmplification, manaCostReduction;

        public Config(Player player) {
            this.player = player;

            this.strength = 0;
            this.agility = 0;
            this.intelligence = 0;
            this.evasion = 0.0f;
            this.moveSpeed = 0.0f;
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

        public void addMoveSpeed(float moveSpeed) {
            this.moveSpeed += moveSpeed;
        }

        public void addHpRegenAmplification(float amplification) {
            this.hpRegenAmplification = 1.0f - (1.0f - this.hpRegenAmplification) * (1.0f - amplification);
        }

        public void addManaCostReduction(float reduction) {
            this.manaCostReduction = 1.0f - (1.0f - this.manaCostReduction) * (1.0f - reduction);
        }
    }
}
