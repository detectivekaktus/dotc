package net.detectivekaktus.item.ingredient;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.material.DotcToolMaterial;

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
    public static final Item MITHRIL_INGOT = DotcItems.register(
            new MithrilIngot(new Item.Properties()),
            "mithril_ingot"
    );
    public static final Item BLIGHT_STONE = DotcItems.register(
            new BlightStone(new Item.Properties()),
            "blight_stone"
    );
    public static final Item BLADES_OF_ATTACK = DotcItems.register(
            new BladesOfAttack(DotcToolMaterial.RADIANT_COMPONENT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.RADIANT_COMPONENT, 0, -2.5f
                    ))),
            "blades_of_attack"
    );
    public static final Item GLOVES_OF_HASTE = DotcItems.register(
            new GlovesOfHaste(new Item.Properties()),
            "gloves_of_haste"
    );
    public static final Item SACRED_RELIC = DotcItems.register(
            new SacredRelic(new Item.Properties()),
            "sacred_relic"
    );


    public static void initialize() { }
}
