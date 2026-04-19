package net.detectivekaktus.client.data.item.component;

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
import net.detectivekaktus.item.component.DotcItemComponents;

public class DotcComponentsRecipeProvider extends FabricRecipeProvider {
    public DotcComponentsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcIngredients.BLADES_OF_ATTACK)
                .pattern("###")
                .pattern(" @ ")
                .pattern("   ")
                .define('#', Items.IRON_SWORD)
                .define('@', DotcIngredients.GLOVES_OF_HASTE)
                .unlockedBy(
                        "has_iron_sword",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_SWORD)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.MITHRIL_HAMMER)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.BROADSWORD)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" @ ")
                .define('#', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.CLAYMORE)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" @ ")
                .define('#', DotcIngredients.DIRE_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_dire_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.DIRE_CRYSTAL)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.BLADE_OF_ALACRITY)
                .pattern(" # ")
                .pattern(" $ ")
                .pattern(" @ ")
                .define('#', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', Items.STICK)
                .define('$', Items.DIAMOND)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.OGRE_AXE)
                .pattern("## ")
                .pattern("#@ ")
                .pattern(" @ ")
                .define('#', DotcIngredients.DIRE_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_dire_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.DIRE_CRYSTAL)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.STAFF_OF_WIZARDRY)
                .pattern(" # ")
                .pattern(" @ ")
                .pattern(" @ ")
                .define('#', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcItemComponents.DEMON_EDGE)
                .pattern(" # ")
                .pattern("$#$")
                .pattern(" @ ")
                .define('#', DotcIngredients.DIRE_CRYSTAL)
                .define('$', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_dire_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.DIRE_CRYSTAL)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:components_recipes";
    }
}
