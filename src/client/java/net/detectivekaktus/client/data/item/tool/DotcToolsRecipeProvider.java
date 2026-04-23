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

import net.detectivekaktus.item.primitive.DotcPrimitives;
import net.detectivekaktus.item.ingredient.DotcIngredients;
import net.detectivekaktus.item.tool.DotcTools;

public class DotcToolsRecipeProvider extends FabricRecipeProvider {
    public DotcToolsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.JAVELIN)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.CRYSTALYS)
                .pattern(" # ")
                .pattern(" | ")
                .pattern(" @ ")
                .define('#', DotcPrimitives.CLAYMORE)
                .define('|', DotcIngredients.DIRE_CRYSTAL)
                .define('@', DotcIngredients.BLADES_OF_ATTACK)
                .unlockedBy(
                        "has_claymore",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.CLAYMORE)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.DAEDALUS)
                .pattern("###")
                .pattern(" @ ")
                .pattern(" @ ")
                .define('#', DotcTools.CRYSTALYS)
                .define('@', DotcPrimitives.DEMON_EDGE)
                .unlockedBy(
                        "has_crystalys",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcTools.CRYSTALYS)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.MONKEY_KING_BAR)
                .pattern(" # ")
                .pattern(" | ")
                .pattern(" @ ")
                .define('#', DotcTools.JAVELIN)
                .define('|', DotcIngredients.DIRE_CRYSTAL)
                .define('@', DotcPrimitives.DEMON_EDGE)
                .unlockedBy(
                        "has_javelin",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcTools.JAVELIN)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:tools_recipes";
    }
}
