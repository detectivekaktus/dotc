package net.detectivekaktus.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.item.ingredients.DotcIngredients;

public class DotcItems {
    public static final ResourceKey<CreativeModeTab> DOTC_ITEM_GROUP_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "item_group")
    );
    public static final CreativeModeTab DOTC_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DotcIngredients.DIRE_CRYSTAL))
            .title(Component.translatable("itemGroup.defense-of-the-craft"))
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DOTC_ITEM_GROUP_KEY, DOTC_ITEM_GROUP);

        DotcIngredients.initialize();

        ItemGroupEvents.modifyEntriesEvent(DOTC_ITEM_GROUP_KEY).register(group -> {
            group.accept(DotcIngredients.RADIANT_CRYSTAL);
            group.accept(DotcIngredients.RADIANT_CRYSTAL_DUST);
            group.accept(DotcIngredients.DIRE_CRYSTAL);
            group.accept(DotcIngredients.DIRE_CRYSTAL_DUST);
            group.accept(DotcIngredients.MITHRIL_INGOT);
            group.accept(DotcIngredients.BLIGHT_STONE);
            group.accept(DotcIngredients.GLOVES_OF_HASTE);
            group.accept(DotcIngredients.SACRED_RELIC);
        });
    }

    // https://docs.fabricmc.net/1.21.1/develop/items/first-item#preparing-your-items-class
    public static Item register(Item item, String id) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id);
        return Registry.register(BuiltInRegistries.ITEM, itemID, item);
    }
}
