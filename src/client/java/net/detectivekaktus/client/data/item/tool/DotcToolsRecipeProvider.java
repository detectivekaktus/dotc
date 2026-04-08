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
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcTools.MITHRIL_HAMMER)
                .pattern("###")
                .pattern(" @ ")
                .pattern(" @ ")
                .define('#', DotcIngredients.MITHRIL_INGOT)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_mithril_ingot",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.MITHRIL_INGOT)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcTools.BROADSWORD)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" @ ")
                .define('#', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_raidant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:tools_recipes";
    }
}
