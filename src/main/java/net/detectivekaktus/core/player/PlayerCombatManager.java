package net.detectivekaktus.core.player;

import net.detectivekaktus.mixin.util.CombatManagerHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ChargeableComponent;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.core.item.DotcItemCooldowns;
import net.detectivekaktus.core.rng.PseudoRandom;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.item.tool.Critable;
import net.detectivekaktus.item.tool.DotcTools;
import net.detectivekaktus.item.tool.HasBonusDamage;
import net.detectivekaktus.sound.DotcSounds;

public class PlayerCombatManager {
    private final Player player;
    private boolean hitThroughEvasion = false;
    private boolean evaded = false;

    public PlayerCombatManager(Player player) {
        this.player = player;
    }

    public float crit(float damage) {
        var stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof Critable item) || !stack.has(DotcComponents.PROCABLE_COMPONENT))
            return damage;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    ProcableComponent.increaseScale(component)
            );
            return damage;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                ProcableComponent.resetScale(component)
        );

        var sound = item.getProcSound();
        sound.ifPresent(soundEvent -> player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                soundEvent,
                player.getSoundSource(),
                1.0f, 1.0f
        ));

        return damage * item.getCritPercent();
    }

    public void calculateProcs() {
        setHitThroughEvasion(false);

        var stack = player.getMainHandItem();
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT) || !(stack.getItem() instanceof HasBonusDamage))
            return;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    ProcableComponent.increaseScale(component)
            );
            return;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                ProcableComponent.resetScale(component)
        );

        setHitThroughEvasion(true);
    }

    public boolean proc(Entity entity, boolean hurt) {
        var stack = player.getMainHandItem();
        var item = stack.getItem();

        if (stack.has(DotcComponents.PROCABLE_COMPONENT) && (item instanceof HasBonusDamage itemWithBonusDamage)) {
            if (!hitThroughEvasion())
                return hurt;

            var damageSource = itemWithBonusDamage.getBonusDamageSource(player);
            var damage = itemWithBonusDamage.getBonusDamage();
            var sound = itemWithBonusDamage.getProcSound();

            sound.ifPresent(soundEvent -> player.level().playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    soundEvent,
                    player.getSoundSource(),
                    1.0f, 1.0f
            ));
            entity.hurt(damageSource, damage);
        }
        else if (stack.is(DotcTools.ECHO_SABRE)) {
            if (player.getCooldowns().isOnCooldown(item))
                return hurt;

            var damageSource = new DamageSource(
                    player.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(DotcDamageTypes.PHYSICAL)
            );

            var damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            var scale = player.getAttackStrengthScale(0.5f);
            damage *= 0.2f + scale * scale * 0.8f;
            damage += item.getAttackDamageBonus(entity, damage, damageSource);

            // like in dota the echo sabre attack doesn't crit if the first one did,
            // so there's no f *= 1.5 in case of a crit

            player.getCooldowns().addCooldown(item, DotcItemCooldowns.ECHO_SABRE_COOLDOWN);
            entity.hurt(damageSource, damage);
        }

        return hurt;
    }

    private void playEvasionSound() {
        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                DotcSounds.EVADED,
                player.getSoundSource(),
                1.0f, 1.0f
        );
    }

    public boolean evade(DamageSource damageSource) {
        if (damageSource.is(DotcDamageTypes.MAGICAL))
            return false;

        setEvaded(false);

        var stats = PlayerStats.get(player);
        var evasion = stats.getEvasion();
        var evasionChance = PseudoRandom.getProcChance(evasion, stats.getEvasionScale());
        if (player.getRandom().nextFloat() > evasionChance) {
            stats.addEvasionScale(1);
            return evaded;
        }

        stats.setEvasionScale(0);

        var attacker = damageSource.getEntity();
        if (attacker == null)
            return evaded;

        if (!(attacker instanceof ServerPlayer)) {
            setEvaded(true);
            playEvasionSound();
            return evaded;
        }

        var hitThrough = ((CombatManagerHolder) attacker).getCombatManager().hitThroughEvasion();
        if (hitThrough)
            return false;

        setEvaded(true);
        playEvasionSound();
        return evaded;
    }

    public float reduceDamage(float damage, DamageSource damageSource) {
        if (!damageSource.is(DotcDamageTypes.MAGICAL))
            return damage;

        var stats = PlayerStats.get(player);
        return damage * (1.0f - stats.getMagicResistance());
    }

    public static void addStickCharge(Player player) {
        var hotbarItems = player.getInventory().items.subList(0, 9);
        for (var item : hotbarItems) {
            boolean isTarget = (item.is(DotcTools.MAGIC_STICK) || item.is(DotcTools.MAGIC_WAND))
                    && item.has(DotcComponents.CHARGEABLE_COMPONENT);
            if (isTarget) {
                var component = item.get(DotcComponents.CHARGEABLE_COMPONENT);
                item.set(
                        DotcComponents.CHARGEABLE_COMPONENT,
                        ChargeableComponent.addCharge(component)
                );
                // Charge only one stick item to prevent abusing sticks by having
                // 9 of them in inventory
                return;
            }
        }
    }

    public boolean hitThroughEvasion() {
        return hitThroughEvasion;
    }

    public void setHitThroughEvasion(boolean hitThroughEvasion) {
        this.hitThroughEvasion = hitThroughEvasion;
    }

    public boolean hasEvaded() {
        return evaded;
    }

    public void setEvaded(boolean evaded) {
        this.evaded = evaded;
    }
}
