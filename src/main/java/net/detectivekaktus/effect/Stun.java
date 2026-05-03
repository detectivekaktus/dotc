package net.detectivekaktus.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class Stun extends MobEffect {
    protected Stun() {
        super(MobEffectCategory.HARMFUL, 0xFF5E2A8A);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }
}
