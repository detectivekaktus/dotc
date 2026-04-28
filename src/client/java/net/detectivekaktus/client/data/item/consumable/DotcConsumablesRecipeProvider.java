package net.detectivekaktus.client.data.item.consumable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.item.consumable.DotcConsumables;
import net.detectivekaktus.item.ingredient.DotcIngredients;

public class DotcConsumablesRecipeProvider extends FabricRecipeProvider {
    public DotcConsumablesRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DotcConsumables.TANGO, 3)
                .pattern(" # ")
                .pattern("#@#")
                .pattern(" # ")
                .define('@', Items.APPLE)
                .define('#', DotcIngredients.RADIANT_CRYSTAL_DUST)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:consumables_recipes";
    }
}
