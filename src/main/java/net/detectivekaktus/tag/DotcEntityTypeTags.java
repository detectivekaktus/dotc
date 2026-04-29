package net.detectivekaktus.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import net.detectivekaktus.DefenseOfTheCraft;

public class DotcEntityTypeTags {
    public static final TagKey<EntityType<?>> DIFFUSAL_BLADE_INVULNERABLE = register("diffusal_blade_invulnerable");

    public static TagKey<EntityType<?>> register(String id) {
        return TagKey.create(
                Registries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id)
        );
    }

    public static void initialize() { }
}
