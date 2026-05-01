package net.detectivekaktus.effect;

import net.detectivekaktus.DefenseOfTheCraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class DotcEffects {
    public static final Holder<MobEffect> ARMOR_REDUCTION = register(
            "armor_reduction",
            new ArmorReduction()
    );

    public static Holder<MobEffect> register(String id, MobEffect effect) {
        return Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id),
                effect
        );
    }

    public static void initialize() { }
}
