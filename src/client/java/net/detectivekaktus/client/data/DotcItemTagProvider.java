package net.detectivekaktus.client.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

import net.detectivekaktus.item.component.DotcItemComponents;
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
                .add(DotcItemComponents.MITHRIL_HAMMER)
                .add(DotcTools.DAEDALUS);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(DotcIngredients.BLADES_OF_ATTACK)
                .add(DotcItemComponents.BLADE_OF_ALACRITY)
                .add(DotcItemComponents.BROADSWORD)
                .add(DotcItemComponents.CLAYMORE)
                .add(DotcItemComponents.DEMON_EDGE)
                .add(DotcItemComponents.STAFF_OF_WIZARDRY)
                .add(DotcTools.CRYSTALYS);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(DotcItemComponents.OGRE_AXE);
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:item_tags";
    }
}
