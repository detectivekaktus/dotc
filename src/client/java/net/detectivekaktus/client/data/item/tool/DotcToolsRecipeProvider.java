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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcTools.DAEDALUS)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.SANGE)
                .pattern(" | ")
                .pattern(" @ ")
                .pattern(" # ")
                .define('|', DotcIngredients.DIRE_CRYSTAL)
                .define('@', DotcPrimitives.OGRE_AXE)
                .define('#', DotcIngredients.BELT_OF_STRENGTH)
                .unlockedBy(
                        "has_ogre_axe",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.OGRE_AXE)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.YASHA)
                .pattern(" | ")
                .pattern(" @ ")
                .pattern(" # ")
                .define('|', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', DotcPrimitives.BLADE_OF_ALACRITY)
                .define('#', DotcIngredients.BAND_OF_ELVENSKIN)
                .unlockedBy(
                        "has_blade_of_alacrity",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.BLADE_OF_ALACRITY)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.KAYA)
                .pattern(" | ")
                .pattern(" @ ")
                .pattern(" # ")
                .define('|', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', DotcPrimitives.STAFF_OF_WIZARDRY)
                .define('#', DotcIngredients.ROBE_OF_THE_MAGI)
                .unlockedBy(
                        "has_staff_of_wizardry",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.STAFF_OF_WIZARDRY)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.ECHO_SABRE)
                .pattern(" | ")
                .pattern(" @ ")
                .pattern(" # ")
                .define('|', DotcPrimitives.BROADSWORD)
                .define('@', DotcPrimitives.OGRE_AXE)
                .define('#', DotcIngredients.VOID_STONE)
                .unlockedBy(
                        "has_broadsword",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.BROADSWORD)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcTools.MAGIC_STICK)
                .pattern(" # ")
                .pattern(" @ ")
                .pattern(" @ ")
                .define('#', DotcIngredients.RADIANT_CRYSTAL_DUST)
                .define('@', Items.STICK)
                .unlockedBy(
                        "has_radiant_crystal",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.RADIANT_CRYSTAL)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DotcTools.MAGIC_WAND)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" @ ")
                .define('#', DotcIngredients.RADIANT_CRYSTAL)
                .define('@', DotcTools.MAGIC_STICK)
                .unlockedBy(
                        "has_magic_stick",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcTools.MAGIC_STICK)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.DIFFUSAL_BLADE)
                .pattern("  #")
                .pattern(" @ ")
                .pattern("$$ ")
                .define('#', DotcPrimitives.BLADE_OF_ALACRITY)
                .define('@', DotcIngredients.ROBE_OF_THE_MAGI)
                .define('$', DotcIngredients.RADIANT_CRYSTAL)
                .unlockedBy(
                        "has_blade_of_alacrity",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.BLADE_OF_ALACRITY)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.DESOLATOR)
                .pattern("$$@")
                .pattern("  #")
                .pattern("  #")
                .define('#', DotcPrimitives.MITHRIL_HAMMER)
                .define('@', DotcIngredients.BLIGHT_STONE)
                .define('$', DotcIngredients.DIRE_CRYSTAL)
                .unlockedBy(
                        "has_blight_stone",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcIngredients.BLIGHT_STONE)
                )
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DotcTools.SKULL_BASHER)
                .pattern("$@$")
                .pattern(" # ")
                .pattern(" # ")
                .define('#', DotcPrimitives.MITHRIL_HAMMER)
                .define('@', DotcIngredients.DIRE_CRYSTAL)
                .define('$', DotcIngredients.BELT_OF_STRENGTH)
                .unlockedBy(
                        "has_mithril_hammer",
                        InventoryChangeTrigger.TriggerInstance.hasItems(DotcPrimitives.MITHRIL_HAMMER)
                )
                .save(exporter);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:tools_recipes";
    }
}
