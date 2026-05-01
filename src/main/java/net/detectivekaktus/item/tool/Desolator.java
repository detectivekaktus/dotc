package net.detectivekaktus.item.tool;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import net.detectivekaktus.core.item.DotcItemRules;
import net.detectivekaktus.effect.DotcEffects;
import net.detectivekaktus.item.DotcHoeItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;

public class Desolator extends DotcHoeItem {
    public Desolator(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties, tooltipBuilder);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity victim, LivingEntity attacker) {
        if (attacker.level().isClientSide || !(attacker instanceof Player))
            return true;

        victim.addEffect(new MobEffectInstance(DotcEffects.ARMOR_REDUCTION, DotcItemRules.DESOLATOR_ARMOR_REDUCTION_DURATION));

        attacker.level().playSound(
                null,
                attacker.getX(), attacker.getY(), attacker.getZ(),
                DotcItemSounds.DESOLATOR,
                SoundSource.PLAYERS,
                1.0f, 1.0f
        );

        return true;
    }
}
