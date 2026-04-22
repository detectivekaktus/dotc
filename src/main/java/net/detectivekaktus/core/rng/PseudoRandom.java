package net.detectivekaktus.core.rng;

public class PseudoRandom {
    public static float getProcChance(float baseChance, int scale) {
        return baseChance * (scale + 1);
    }
}
