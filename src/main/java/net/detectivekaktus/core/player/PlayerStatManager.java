package net.detectivekaktus.core.player;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attach.DotcAttachmentRules;
import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.attribute.DotcAttributeModifiers;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ItemStatsComponent;

public class PlayerStatManager {
    private final Player player;

    public PlayerStatManager(Player player) {
        this.player = player;
    }

    public void updateStats() {
        var config = new PlayerStatManager.Config();
        var hotbarItems = player.getInventory().items.subList(0, 9);

        for (var item : hotbarItems) {
            if (item.has(DotcComponents.ITEM_STATS_COMPONENT)) {
                var stats = item.get(DotcComponents.ITEM_STATS_COMPONENT);
                config.addStats(stats);
            }

            if (item.has(DotcComponents.EVASION_COMPONENT)) {
                var evasion = item.get(DotcComponents.EVASION_COMPONENT);
                config.addEvasion(evasion);
            }

            if (item.has(DotcComponents.BONUS_HP_REGEN_COMPONENT)) {
                var regen = item.get(DotcComponents.BONUS_HP_REGEN_COMPONENT);
                config.addBonusHpRegen(regen * item.getCount());
            }

            if (item.has(DotcComponents.HP_REGEN_AMPLIFICATION_COMPONENT)) {
                var amplification = item.get(DotcComponents.HP_REGEN_AMPLIFICATION_COMPONENT);
                config.addHpRegenAmplification(amplification);
            }

            if (item.has(DotcComponents.MOVE_SPEED_COMPONENT)) {
                var moveSpeed = item.get(DotcComponents.MOVE_SPEED_COMPONENT);
                config.addMoveSpeed(moveSpeed);
            }

            if (item.has(DotcComponents.BONUS_MANA_REGEN_COMPONENT)) {
                var manaRegen = item.get(DotcComponents.BONUS_MANA_REGEN_COMPONENT);
                config.addBonusManaRegen(manaRegen);
            }

            if (item.has(DotcComponents.MANA_COST_REDUCTION_COMPONENT)) {
                var reduction = item.get(DotcComponents.MANA_COST_REDUCTION_COMPONENT);
                config.addManaCostReduction(reduction);
            }
        }

        applyStats(config);
    }

    private boolean hasStatChanges(PlayerStats.StatsData stats, PlayerMana.ManaData mana, Config config) {
        return stats.getStrength() != config.strength
                || stats.getAgility() != config.agility
                || stats.getIntelligence() != config.intelligence
                || Math.abs(stats.getBonusHpRegen() - config.bonusHpRegen) > 1e-5f
                || Math.abs(stats.getHpRegenAmplification() - config.hpRegenAmplification) > 1e-5f
                || Math.abs(stats.getEvasion() - config.evasion) > 1e-5f
                || Math.abs(stats.getMoveSpeed() - config.moveSpeed) > 1e-5f
                || Math.abs(mana.getBonusManaRegen() - config.bonusManaRegen) > 1e-5f
                || Math.abs(mana.getManaCostReduction() - config.manaCostReduction) > 1e-5f;
    }

    private void applyStats(Config config) {
        var stats = PlayerStats.get(player);
        var mana = PlayerMana.get(player);
        if (!hasStatChanges(stats, mana, config))
            return;

        stats.setStrength(config.strength);
        applyStrength(config.strength);
        stats.setBonusHpRegen(config.bonusHpRegen);
        stats.setHpRegenAmplification(config.hpRegenAmplification);

        stats.setAgility(config.agility);
        applyAgility(config.agility);
        applyMoveSpeed(config.moveSpeed);
        stats.setEvasion(config.evasion);
        stats.setEvasionScale(0);

        stats.setIntelligence(config.intelligence);
        mana.setBonusManaRegen(config.bonusManaRegen);
        applyIntelligence(config.intelligence);
        mana.setManaCostReduction(config.manaCostReduction);
    }

    private void applyStrength(int val) {
        var maxHpAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHpAttr != null) {
            var hpPercent = (player.getHealth() / maxHpAttr.getValue());

            if (val == 0) {
                maxHpAttr.removeModifier(DotcAttributeModifiers.MAX_HP_BONUS_MODIFIER_ID);
            }
            else {
                var hp = val * StatConversionRules.HP_PER_STRENGTH;
                maxHpAttr.addOrReplacePermanentModifier(
                        new AttributeModifier(
                                DotcAttributeModifiers.MAX_HP_BONUS_MODIFIER_ID,
                                hp,
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );
            }

            player.setHealth((float) (maxHpAttr.getValue() * hpPercent));
        }

        var stats = PlayerStats.get(player);
        var hpRegen = DotcAttachmentRules.DEFAULT_HP_REGEN + (val * StatConversionRules.HP_REGEN_PER_STRENGTH);
        stats.setHpRegen(hpRegen);
    }

    private void applyAgility(int val) {
        var armorAttr = player.getAttribute(Attributes.ARMOR);
        if (armorAttr != null) {
            if (val == 0) {
                armorAttr.removeModifier(DotcAttributeModifiers.BASE_ARMOR_BONUS_MODIFIER_ID);
            }
            else {
                var armor = val * StatConversionRules.ARMOR_PER_AGILITY;
                armorAttr.addOrReplacePermanentModifier(
                        new AttributeModifier(
                                DotcAttributeModifiers.BASE_ARMOR_BONUS_MODIFIER_ID,
                                armor,
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );
            }
        }

        var attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr != null) {
            if (val == 0) {
                attackSpeedAttr.removeModifier(DotcAttributeModifiers.ATTACK_SPEED_BONUS_MODIFIER_ID);
            }
            else {
                var attackSpeed = val * StatConversionRules.ATTACK_SPEED_PER_AGILITY;
                attackSpeedAttr.addOrReplacePermanentModifier(
                        new AttributeModifier(
                                DotcAttributeModifiers.ATTACK_SPEED_BONUS_MODIFIER_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );
            }
        }
    }

    private void applyMoveSpeed(float val) {
        var moveSpeedAttr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (moveSpeedAttr != null) {
            if (val == 0.0f)
                moveSpeedAttr.removeModifier(DotcAttributeModifiers.MOVE_SPEED_BONUS_MODIFIER_ID);
            else
                moveSpeedAttr.addOrReplacePermanentModifier(
                        new AttributeModifier(
                                DotcAttributeModifiers.MOVE_SPEED_BONUS_MODIFIER_ID,
                                val,
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                );
        }

        var stats = PlayerStats.get(player);
        stats.setMoveSpeed(val);
    }

    private void applyIntelligence(int val) {
        var mana = PlayerMana.get(player);
        var maxMana = DotcAttachmentRules.DEFAULT_MAX_MANA + (val * StatConversionRules.MANA_PER_INTELLIGENCE);
        mana.setMaxMana(maxMana);

        var manaRegen = DotcAttachmentRules.DEFAULT_MANA_REGEN + (val * StatConversionRules.MANA_REGEN_PER_INTELLIGENCE) + mana.getBonusManaRegen();
        mana.setManaRegen(manaRegen);

        var stats = PlayerStats.get(player);
        var magicResistance = DotcAttachmentRules.DEFAULT_MAGIC_RESISTANCE + (val * StatConversionRules.MAGIC_RESISTANCE_PER_INTELLIGENCE);
        stats.setMagicResistance(magicResistance);
    }

    public static class Config {
        int strength, agility, intelligence;
        float bonusHpRegen, hpRegenAmplification;
        float evasion, moveSpeed;
        float bonusManaRegen, manaCostReduction;

        public Config() { }

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

        public void addBonusHpRegen(float hpRegen) {
            this.bonusHpRegen += hpRegen;
        }

        public void addHpRegenAmplification(float amplification) {
            this.hpRegenAmplification = 1.0f - (1.0f - this.hpRegenAmplification) * (1.0f - amplification);
        }

        public void addBonusManaRegen(float manaRegen) {
            this.bonusManaRegen += manaRegen;
        }

        public void addManaCostReduction(float reduction) {
            this.manaCostReduction = 1.0f - (1.0f - this.manaCostReduction) * (1.0f - reduction);
        }
    }
}
