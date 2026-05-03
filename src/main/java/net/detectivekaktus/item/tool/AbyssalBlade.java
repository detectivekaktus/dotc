package net.detectivekaktus.item.tool;

import net.detectivekaktus.core.item.HasManaCost;
import net.detectivekaktus.core.item.SharesCooldown;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.core.item.DotcItemCooldowns;
import net.detectivekaktus.core.item.DotcItemRules;
import net.detectivekaktus.core.player.CombatManager;
import net.detectivekaktus.effect.DotcEffects;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.gui.DotcGuiSounds;
import net.detectivekaktus.sound.item.DotcItemSounds;
import net.detectivekaktus.tag.DotcEntityTypeTags;

import java.util.List;

public class AbyssalBlade extends SkullBasher implements HasManaCost, SharesCooldown {
    public AbyssalBlade(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        var level = player.level();

        var mana = PlayerMana.get(player);
        var notEnoughMana = getManaCost() > mana.getCurrentMana();

        if (level.isClientSide) {
            if (notEnoughMana) {
                level.playLocalSound(
                        player,
                        DotcGuiSounds.UI_NOT_ENOUGH_MANA,
                        SoundSource.PLAYERS,
                        1.0f, 1.0f
                );
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        }

        if (livingEntity.getType().is(DotcEntityTypeTags.ABYSSAL_BLADE_INVULNERABLE))
            return InteractionResult.FAIL;

        if (notEnoughMana)
            return InteractionResult.FAIL;

        if (livingEntity instanceof Player interactedPlayer)
            CombatManager.addStickCharge(interactedPlayer);

        mana.consume(getManaCost());

        livingEntity.addEffect(new MobEffectInstance(DotcEffects.STUN, DotcItemRules.BASH_DURATION));
        player.teleportTo(livingEntity.getX(), livingEntity.getY() + 0.5, livingEntity.getZ());
        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                DotcItemSounds.ABYSSAL_BLADE,
                SoundSource.PLAYERS
        );
        player.getCooldowns().addCooldown(this, DotcItemCooldowns.ABYSSAL_BLADE_COOLDOWN);

        return InteractionResult.SUCCESS;
    }

    @Override
    public float getManaCost() {
        return 40.0f;
    }

    @Override
    public List<Item> getSharesCooldownWith() {
        return List.of(DotcTools.SKULL_BASHER);
    }
}
