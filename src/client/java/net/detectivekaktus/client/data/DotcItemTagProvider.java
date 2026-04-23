package net.detectivekaktus.client.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

import net.detectivekaktus.item.primitive.DotcPrimitives;
import net.detectivekaktus.item.ingredient.DotcIngredients;
import net.detectivekaktus.item.tool.DotcTools;

import java.util.concurrent.CompletableFuture;
public class DotcItemTagProvider extends FabricTagProvider<Item> {
    public DotcItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(DotcPrimitives.MITHRIL_HAMMER)
                .add(DotcTools.DAEDALUS);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(DotcIngredients.BLADES_OF_ATTACK)
                .add(DotcPrimitives.BLADE_OF_ALACRITY)
                .add(DotcPrimitives.BROADSWORD)
                .add(DotcPrimitives.CLAYMORE)
                .add(DotcPrimitives.DEMON_EDGE)
                .add(DotcPrimitives.STAFF_OF_WIZARDRY)
                .add(DotcTools.CRYSTALYS)
                .add(DotcTools.BUTTERFLY)
                .add(DotcTools.SANGE)
                .add(DotcTools.YASHA)
                .add(DotcTools.KAYA);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(DotcPrimitives.OGRE_AXE);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:item_tags";
    }
}
