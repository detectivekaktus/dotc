package net.detectivekaktus.core.player;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.core.rng.PseudoRandom;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.item.tool.Critable;
import net.detectivekaktus.item.tool.DotcTools;
import net.detectivekaktus.item.tool.HasBonusDamage;
import net.detectivekaktus.sound.DotcSounds;

public class PlayerCombatManager {
    private final Player player;

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
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
            return damage;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                new ProcableComponent(component.baseChance(), 0)
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
        var canHitThrough = (CanHitThroughEvasion) player;
        canHitThrough.setHitThroughEvasion(false);

        var stack = player.getMainHandItem();
        if (!stack.has(DotcComponents.PROCABLE_COMPONENT) || !(stack.getItem() instanceof HasBonusDamage))
            return;

        var component = stack.get(DotcComponents.PROCABLE_COMPONENT);
        var chance = PseudoRandom.getProcChance(component.baseChance(), component.scale());
        if (player.getRandom().nextFloat() > chance) {
            stack.set(
                    DotcComponents.PROCABLE_COMPONENT,
                    new ProcableComponent(component.baseChance(), component.scale() + 1)
            );
            return;
        }

        stack.set(
                DotcComponents.PROCABLE_COMPONENT,
                new ProcableComponent(component.baseChance(), 0)
        );

        canHitThrough.setHitThroughEvasion(true);
    }

    public boolean proc(Entity entity, boolean hurt) {
        var canHitThrough = (CanHitThroughEvasion) player;

        var stack = player.getMainHandItem();
        var item = stack.getItem();

        if (stack.has(DotcComponents.PROCABLE_COMPONENT) && (item instanceof HasBonusDamage itemWithBonusDamage)) {
            if (!canHitThrough.getHitThroughEvasion())
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

            player.getCooldowns().addCooldown(item, 5 * 20);
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

        var evadable = (Evadable) player;
        evadable.setEvaded(false);

        var stats = PlayerStats.get(player);
        var evasion = stats.getEvasion();
        var evasionChance = PseudoRandom.getProcChance(evasion, stats.getEvasionScale());
        if (player.getRandom().nextFloat() > evasionChance) {
            stats.addEvasionScale(1);
            return false;
        }

        stats.setEvasionScale(0);

        var attacker = damageSource.getEntity();
        if (attacker == null)
            return false;

        if (!(attacker instanceof ServerPlayer)) {
            evadable.setEvaded(true);
            playEvasionSound();
            return true;
        }

        var hitThrough = ((CanHitThroughEvasion) attacker).getHitThroughEvasion();
        if (hitThrough)
            return false;

        evadable.setEvaded(true);
        playEvasionSound();
        return true;
    }

    public float reduceDamage(float damage, DamageSource damageSource) {
        if (!damageSource.is(DotcDamageTypes.MAGICAL))
            return damage;

        var stats = PlayerStats.get(player);
        return damage * (1.0f - stats.getMagicResistance());
    }
}
