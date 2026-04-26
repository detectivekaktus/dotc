package net.detectivekaktus.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import net.detectivekaktus.DefenseOfTheCraft;

public class DotcDamageTypes {
    public static final ResourceKey<DamageType> MAGICAL = register("magical");
    public static final ResourceKey<DamageType> PHYSICAL = register("physical");

    public static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(
                Registries.DAMAGE_TYPE,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id)
        );
    }

    public static void initialize() { }
}
