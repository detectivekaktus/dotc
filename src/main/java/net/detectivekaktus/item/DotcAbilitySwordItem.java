package net.detectivekaktus.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.core.item.HasCooldown;
import net.detectivekaktus.core.item.HasManaCost;
import net.detectivekaktus.core.item.SharesCooldown;
import net.detectivekaktus.core.player.CombatManager;
import net.detectivekaktus.sound.gui.DotcGuiSounds;

public abstract class DotcAbilitySwordItem extends DotcSwordItem implements HasManaCost, HasCooldown {
    public DotcAbilitySwordItem(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    protected InteractionResultHolder<ItemStack> interactWithItem(Player player, LivingEntity target, ItemStack stack) {
        var level = player.level();

        var mana = PlayerMana.get(player);
        var notEnoughMana = getManaCost() > mana.getCurrentMana();

        if (notEnoughMana) {
            if (level.isClientSide)
                level.playLocalSound(
                        player,
                        DotcGuiSounds.UI_NOT_ENOUGH_MANA,
                        SoundSource.PLAYERS,
                        1.0f, 1.0f
                );
            return InteractionResultHolder.fail(stack);
        }

        if (level.isClientSide)
            return InteractionResultHolder.pass(stack);

        if (target.getType().is(getInvulnerableTag()))
            return InteractionResultHolder.fail(stack);

        if (target instanceof Player interactedPlayer)
            CombatManager.addStickCharge(interactedPlayer);

        invokeInteractionAbility(player, target);
        playAbilitySound(player);

        var cooldowns = player.getCooldowns();
        if (this instanceof SharesCooldown itemWithSharedCooldown) {
            cooldowns.addCooldown(this, getCooldownInTicks());
            for (var item : itemWithSharedCooldown.getSharesCooldownWith()) {
                if (cooldowns.isOnCooldown(item))
                    continue;

                cooldowns.addCooldown(item, getCooldownInTicks());
            }
        }

        return InteractionResultHolder.success(stack);
    }

    private void playAbilitySound(Player player) {
        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                getAbilitySound(),
                SoundSource.PLAYERS
        );
    }

    protected abstract TagKey<EntityType<?>> getInvulnerableTag();
    protected abstract void invokeInteractionAbility(Player player, LivingEntity target);
    protected abstract SoundEvent getAbilitySound();
}
