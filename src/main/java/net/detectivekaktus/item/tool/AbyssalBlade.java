package net.detectivekaktus.item.tool;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.core.item.DotcItemCooldowns;
import net.detectivekaktus.core.item.DotcItemRules;
import net.detectivekaktus.core.item.Procable;
import net.detectivekaktus.core.item.SharesProcCooldown;
import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.effect.DotcEffects;
import net.detectivekaktus.item.DotcAbilitySwordItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;
import net.detectivekaktus.tag.DotcEntityTypeTags;

import java.util.List;
import java.util.Optional;

public class AbyssalBlade extends DotcAbilitySwordItem implements Procable, SharesProcCooldown {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_25;

    public AbyssalBlade(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        return interactWithItem(player, livingEntity, itemStack).getResult();
    }

    @Override
    protected TagKey<EntityType<?>> getInvulnerableTag() {
        return DotcEntityTypeTags.ABYSSAL_BLADE_INVULNERABLE;
    }

    @Override
    protected void invokeInteractionAbility(Player player, LivingEntity target) {
        target.addEffect(new MobEffectInstance(DotcEffects.STUN, DotcItemRules.BASH_DURATION));
        player.teleportTo(target.getX(), target.getY(), target.getZ());
    }

    @Override
    protected SoundEvent getAbilitySound() {
        return DotcItemSounds.ABYSSAL_BLADE;
    }

    @Override
    public float getManaCost() {
        return 40.0f;
    }

    @Override
    public int getCooldownInTicks() {
        return DotcItemCooldowns.ABYSSAL_BLADE_COOLDOWN;
    }

    @Override
    public float getProcDamage() {
        return 4.0f;
    }

    @Override
    public DamageSource getProcDamageSource(Player player) {
        return player.damageSources().source(DotcDamageTypes.PHYSICAL);
    }

    @Override
    public Optional<Holder<MobEffect>> getProcEffect() {
        return Optional.of(DotcEffects.STUN);
    }

    @Override
    public int getProcCooldownInTicks() {
        return DotcItemCooldowns.SKULL_BASHER_COOLDOWN;
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.of(DotcItemSounds.SKULL_BASHER);
    }

    @Override
    public List<Item> getSharesProcCooldownWith() {
        return List.of(DotcTools.SKULL_BASHER);
    }
}
