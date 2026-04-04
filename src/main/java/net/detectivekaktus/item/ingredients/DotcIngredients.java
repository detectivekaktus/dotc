package net.detectivekaktus.item.ingredients;

import net.detectivekaktus.item.DotcItems;
import net.minecraft.world.item.Item;

public class DotcIngredients {
    public static final Item RADIANT_CRYSTAL = DotcItems.register(
            new RadiantCrystal(new Item.Properties()),
            "radiant_crystal"
    );

    public static void initialize() { }
}
