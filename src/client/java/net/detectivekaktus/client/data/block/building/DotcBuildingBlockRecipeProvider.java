package net.detectivekaktus.client.data.block.building;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.block.building.DotcBuildingBlocks;
import net.detectivekaktus.item.ingredients.DotcIngredients;

public class DotcBuildingBlockRecipeProvider extends FabricRecipeProvider {
    public DotcBuildingBlockRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DotcBuildingBlocks.RADIANT_CRYSTAL_BLOCK)
                .requires(DotcIngredients.RADIANT_CRYSTAL, 9)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DotcBuildingBlocks.DIRE_CRYSTAL_BLOCK)
                .requires(DotcIngredients.DIRE_CRYSTAL, 9)
                .unlockedBy(
                        "has_dire_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.DIRE_CRYSTAL)
                )
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DotcIngredients.RADIANT_CRYSTAL, 9)
                .requires(DotcBuildingBlocks.RADIANT_CRYSTAL_BLOCK)
                .unlockedBy(
                        "has_radiant_crystal_block",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcBuildingBlocks.RADIANT_CRYSTAL_BLOCK)
                )
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DotcIngredients.DIRE_CRYSTAL, 9)
                .requires(DotcBuildingBlocks.DIRE_CRYSTAL_BLOCK)
                .unlockedBy(
                        "has_dire_crystal_block",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcBuildingBlocks.DIRE_CRYSTAL_BLOCK)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:building_block_recipes";
    }
}
