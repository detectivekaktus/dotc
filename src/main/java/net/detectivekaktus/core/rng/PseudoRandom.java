package net.detectivekaktus.core.rng;

public class PseudoRandom {
    public static float getProcChance(float baseChance, int scale) {
        return reduceChance(baseChance) * (scale + 1);
    }

    private static float reduceChance(float chance) {
        return chance <= 0.5f ?
                chance
                : 0.5f + chance * chance;
    }
}
