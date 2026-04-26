package net.detectivekaktus.item.ingredient;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ItemStatsComponent;
import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.DotcSwordItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.item.material.DotcToolMaterial;

public class DotcIngredients {
    public static final Item RADIANT_CRYSTAL = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("radiant_crystal").description()
            ),
            "radiant_crystal"
    );
    public static final Item DIRE_CRYSTAL = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("dire_crystal").description()
            ),
            "dire_crystal"
    );
    public static final Item RADIANT_CRYSTAL_DUST = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("radiant_crystal_dust").description()
            ),
            "radiant_crystal_dust"
    );
    public static final Item DIRE_CRYSTAL_DUST = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("dire_crystal_dust").description()
            ),
            "dire_crystal_dust"
    );
    public static final Item RADIANT_CRYSTAL_SHARDS = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("radiant_crystal_shards").description()
            ),
            "radiant_crystal_shards"
    );
    public static final Item DIRE_CRYSTAL_SHARDS = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("dire_crystal_shards").description()
            ),
            "dire_crystal_shards"
    );
    public static final Item MITHRIL_INGOT = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("mithril_ingot").description()
            ),
            "mithril_ingot"
    );
    public static final Item BLIGHT_STONE = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("blight_stone").description()
            ),
            "blight_stone"
    );
    public static final Item BLADES_OF_ATTACK = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.RADIANT_COMPONENT,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_COMPONENT, 0, -2.5f
                            )),
                    new TooltipBuilder("blades_of_attack").description()
            ),
            "blades_of_attack"
    );
    public static final Item GLOVES_OF_HASTE = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("gloves_of_haste").description()
            ),
            "gloves_of_haste"
    );
    public static final Item BELT_OF_STRENGTH = DotcItems.register(
            new DotcItem(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(6, 0, 0)
                            ),
                    new TooltipBuilder("belt_of_strength")
                            .description()
                            .stats(6, 0, 0)
            ),
            "belt_of_strength"
    );
    public static final Item BAND_OF_ELVENSKIN = DotcItems.register(
            new DotcItem(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 6, 0)
                            ),
                    new TooltipBuilder("band_of_elvenskin")
                            .description()
                            .stats(0, 6, 0)
            ),
            "band_of_elvenskin"
    );
    // TODO: Make this item a wearable armor
    public static final Item ROBE_OF_THE_MAGI = DotcItems.register(
            new DotcItem(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 0, 6)
                            ),
                    new TooltipBuilder("robe_of_the_magi")
                            .description()
                            .stats(0, 0, 6)
            ),
            "robe_of_the_magi"
    );
    public static final Item TALISMAN_OF_EVASION = DotcItems.register(
            new DotcItem(
                    new Item.Properties()
                            .component(
                                    DotcComponents.EVASION_COMPONENT,
                                    PseudoRandomBaseChances.AVG_10
                            ),
                    new TooltipBuilder("talisman_of_evasion")
                            .description()
                            .passive()
            ),
            "talisman_of_evasion"
    );
    public static final Item EAGLESONG = DotcItems.register(
            new DotcItem(
                    new Item.Properties()
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 25, 0)
                            ),
                    new TooltipBuilder("eaglesong")
                            .description()
                            .stats(0, 25, 0)
            ),
            "eaglesong"
    );
    public static final Item SACRED_RELIC = DotcItems.register(
            new DotcItem(
                    new Item.Properties(),
                    new TooltipBuilder("sacred_relic").description()
            ),
            "sacred_relic"
    );


    public static void initialize() {
    }
}
