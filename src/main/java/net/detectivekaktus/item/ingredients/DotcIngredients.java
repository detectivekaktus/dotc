package net.detectivekaktus.item.ingredients;

import net.detectivekaktus.item.DotcItems;
import net.minecraft.world.item.Item;

public class DotcIngredients {
    public static final Item RADIANT_CRYSTAL = DotcItems.register(
            new RadiantCrystal(new Item.Properties()),
            "radiant_crystal"
    );
    public static final Item DIRE_CRYSTAL = DotcItems.register(
            new DireCrystal(new Item.Properties()),
            "dire_crystal"
    );

    public static final Item RADIANT_CRYSTAL_DUST = DotcItems.register(
            new CrystalDust(new Item.Properties()),
            "radiant_crystal_dust"
    );
    public static final Item DIRE_CRYSTAL_DUST = DotcItems.register(
            new CrystalDust(new Item.Properties()),
            "dire_crystal_dust"
    );

    public static void initialize() { }
}
