package net.detectivekaktus.item.material;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import net.detectivekaktus.item.ingredient.DotcIngredients;

public enum DotcToolMaterial implements Tier {
    RADIANT_COMPONENT(
            388, 6.5f, 2.5f, 15, Ingredient.of(DotcIngredients.RADIANT_CRYSTAL)
    ),
    DIRE_COMPONENT(
            512, 7.5f, 3.0f, 18, Ingredient.of(DotcIngredients.DIRE_CRYSTAL)
    ),
    RADIANT_ARTEFACT(
            2048, 8.0f, 4.0f, 10, Ingredient.of(DotcIngredients.RADIANT_CRYSTAL)
    ),
    DIRE_ARTEFACT(
            2048, 9.0f, 5.0f, 12, Ingredient.of(DotcIngredients.DIRE_CRYSTAL)
    );

    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairIngredient;

    private DotcToolMaterial(int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient) {
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.INCORRECT_FOR_IRON_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
