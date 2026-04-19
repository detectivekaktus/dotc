package net.detectivekaktus.core.rng;

public class PseudoRandom {
    private static final float MAX_UNSCALED_PERCENT = 0.33f;

    public static float getProcChance(float baseChance, int scale) {
        return reduceChance(baseChance) * (scale + 1);
    }

    private static float reduceChance(float chance) {
        if (chance <= MAX_UNSCALED_PERCENT)
            return chance;
        float diff = chance - MAX_UNSCALED_PERCENT;
        return MAX_UNSCALED_PERCENT + diff * diff;
    }
}
