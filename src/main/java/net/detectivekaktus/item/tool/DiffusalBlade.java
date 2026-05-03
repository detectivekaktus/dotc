package net.detectivekaktus.item.tool;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.core.item.DotcItemCooldowns;
import net.detectivekaktus.core.item.DotcItemRules;
import net.detectivekaktus.item.DotcAbilitySwordItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;
import net.detectivekaktus.tag.DotcEntityTypeTags;

public class DiffusalBlade extends DotcAbilitySwordItem {
    public DiffusalBlade(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        return interactWithItem(player, livingEntity, itemStack).getResult();
    }

    @Override
    protected TagKey<EntityType<?>> getInvulnerableTag() {
        return DotcEntityTypeTags.DIFFUSAL_BLADE_INVULNERABLE;
    }

    @Override
    protected void invokeInteractionAbility(Player player, LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, DotcItemRules.DIFFUSAL_SLOW_DURATION, 1));
    }

    @Override
    protected SoundEvent getAbilitySound() {
        return DotcItemSounds.DIFFUSAL_BLADE;
    }

    @Override
    public float getManaCost() {
        return 40.0f;
    }

    @Override
    public int getCooldownInTicks() {
        return DotcItemCooldowns.DIFFUSAL_BLADE_COOLDOWN;
    }
}
