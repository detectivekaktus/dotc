package net.detectivekaktus.client.data.item.tool;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.item.ingredient.DotcIngredients;
import net.detectivekaktus.item.tool.DotcTools;

public class DotcToolsRecipeProvider extends FabricRecipeProvider {
    public DotcToolsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcTools.JAVELIN)
                .pattern(" # ")
                .pattern("#@#")
                .pattern(" @ ")
                .define('#', DotcIngredients.DIRE_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_dire_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.DIRE_CRYSTAL)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:tools_recipes";
    }
}
