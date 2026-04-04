package net.detectivekaktus.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.item.ingredients.DotcIngredients;

public class DotcItems {
    public static void initialize() {
        DotcIngredients.initialize();
    }

    // https://docs.fabricmc.net/1.21.1/develop/items/first-item#preparing-your-items-class
    public static Item register(Item item, String id) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id);
        return Registry.register(BuiltInRegistries.ITEM, itemID, item);
    }
}
