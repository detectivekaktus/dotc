package net.detectivekaktus.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import net.detectivekaktus.attribute.DotcAttributeModifiers;

public class ArmorReduction extends MobEffect {
    protected ArmorReduction() {
        super(MobEffectCategory.HARMFUL, 0xFFDC0F0E);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.level().isClientSide || !(livingEntity instanceof Player player))
            return super.applyEffectTick(livingEntity, amplifier);

        var armorAttr = player.getAttribute(Attributes.ARMOR);
        if (armorAttr != null) {
            addAttributeModifier(
                    Attributes.ARMOR,
                    DotcAttributeModifiers.ARMOR_REDUCTION_MODIFIER_ID,
                    -(2 << amplifier),
                    AttributeModifier.Operation.ADD_VALUE
            );
        }

        return super.applyEffectTick(livingEntity, amplifier);
    }
}
