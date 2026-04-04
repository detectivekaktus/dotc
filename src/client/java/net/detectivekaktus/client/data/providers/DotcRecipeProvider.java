package net.detectivekaktus.client.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.item.ingredients.DotcIngredients;

public class DotcRecipeProvider extends FabricRecipeProvider {
    public DotcRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DotcIngredients.RADIANT_CRYSTAL_DUST, 2)
                .requires(DotcIngredients.RADIANT_CRYSTAL)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .group("crystal_dust")
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DotcIngredients.DIRE_CRYSTAL_DUST, 2)
                .requires(DotcIngredients.DIRE_CRYSTAL)
                .unlockedBy(
                        "has_dire_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.DIRE_CRYSTAL)
                )
                .group("crystal_dust")
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DotcIngredients.MITHRIL_INGOT)
                .requires(Ingredient.of(DotcIngredients.RADIANT_CRYSTAL, DotcIngredients.DIRE_CRYSTAL), 3)
                .requires(Items.IRON_INGOT, 2)
                .unlockedBy(
                        "has_crystals_and_iron",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                DotcIngredients.RADIANT_CRYSTAL, DotcIngredients.DIRE_CRYSTAL, Items.IRON_INGOT
                                )
                )
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DotcIngredients.BLIGHT_STONE)
                .requires(Items.ROTTEN_FLESH, 2)
                .requires(Items.POISONOUS_POTATO)
                .requires(Items.SPIDER_EYE)
                .requires(Items.PUFFERFISH)
                .unlockedBy(
                        "has_rotten_flesh",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.ROTTEN_FLESH)
                )
                .save(exporter);
    }
}
