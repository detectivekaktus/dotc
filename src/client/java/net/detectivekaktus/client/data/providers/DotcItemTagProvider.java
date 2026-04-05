package net.detectivekaktus.client.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.item.ingredients.DotcIngredients;

public class DotcItemTagProvider extends FabricTagProvider<Item> {
    public static final TagKey<Item> CRYSTALS = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "crystals")
    );

    public DotcItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(CRYSTALS)
                .add(DotcIngredients.RADIANT_CRYSTAL)
                .add(DotcIngredients.DIRE_CRYSTAL);
    }
}
