package net.detectivekaktus.attribute;

import net.detectivekaktus.DefenseOfTheCraft;
import net.minecraft.resources.ResourceLocation;

public class DotcAttributeModifiers {
    public static final ResourceLocation MAX_HP_BONUS_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "max_hp_bonus");
    public static final ResourceLocation MOVE_SPEED_BONUS_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "move_speed_bonus");
    public static final ResourceLocation BASE_ARMOR_BONUS_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "base_armor_bonus");
    public static final ResourceLocation ATTACK_SPEED_BONUS_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "attack_speed_bonus");

    public static final ResourceLocation ARMOR_REDUCTION_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "armor_reduction");

    public static void initialize() { }
}
