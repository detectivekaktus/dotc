package net.detectivekaktus.item.ingredient;

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ItemStatsComponent;
import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
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
    public static final Item BELT_OF_STRENGTH = DotcItems.register(
            new BeltOfStrength(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(6, 0, 0)
                            )
            ),
            "belt_of_strength"
    );
    public static final Item BAND_OF_ELVENSKIN = DotcItems.register(
            new BandOfElvenskin(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 6, 0)
                            )
            ),
            "band_of_elvenskin"
    );
    public static final Item ROBE_OF_THE_MAGI = DotcItems.register(
            new RobeOfTheMagi(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 0, 6)
                            )
            ),
            "robe_of_the_magi"
    );
    public static final Item GLOVES_OF_HASTE = DotcItems.register(
            new GlovesOfHaste(new Item.Properties()),
            "gloves_of_haste"
    );
    public static final Item TALISMAN_OF_EVASION = DotcItems.register(
            new TalismanOfEvasion(
                    new Item.Properties()
                            .component(
                                    DotcComponents.EVASION_COMPONENT,
                                    PseudoRandomBaseChances.AVG_10
                            )
            ),
            "talisman_of_evasion"
    );
    public static final Item EAGLESONG = DotcItems.register(
            new Eaglesong(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 25, 0)
                            )
            ),
            "eaglesong"
    );
    public static final Item SACRED_RELIC = DotcItems.register(
            new SacredRelic(new Item.Properties()),
            "sacred_relic"
    );


    public static void initialize() { }
}
