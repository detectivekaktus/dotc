package net.detectivekaktus.item.primitive;

import net.minecraft.world.item.Item;

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ItemStatsComponent;
import net.detectivekaktus.item.*;
import net.detectivekaktus.item.material.DotcToolMaterial;

public class DotcPrimitives {
    public static final Item MITHRIL_HAMMER = DotcItems.register(
            new DotcPickaxeItem(
                    DotcToolMaterial.RADIANT_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcPickaxeItem.createAttributes(
                                    DotcToolMaterial.RADIANT_COMPONENT, 1, -2.8f
                            )),
                    new TooltipBuilder("mithril_hammer").description()
            ),
            "mithril_hammer"
    );
    public static final Item BROADSWORD = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.RADIANT_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcSwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_COMPONENT, 4, -2.0f
                            )),
                    new TooltipBuilder("broadsword").description()
            ),
            "broadsword"
    );
    public static final Item CLAYMORE = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.DIRE_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcSwordItem.createAttributes(
                                    DotcToolMaterial.DIRE_COMPONENT, 4, -1.5f
                            )),
                    new TooltipBuilder("claymore").description()
            ),
            "claymore"
    );
    public static final Item OGRE_AXE = DotcItems.register(
            new DotcAxeItem(
                    DotcToolMaterial.DIRE_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcAxeItem.createAttributes(
                                    DotcToolMaterial.DIRE_COMPONENT, 5, -3.0f
                            ))
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(10, 0, 0
                                    )),
                    new TooltipBuilder("ogre_axe")
                            .description()
                            .stats(10, 0, 0)
            ),
            "ogre_axe"
    );
    public static final Item BLADE_OF_ALACRITY = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.RADIANT_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcSwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_COMPONENT, 1, -1.0f
                            ))
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 10, 0
                                    )),
                    new TooltipBuilder("blade_of_alacrity")
                            .description()
                            .stats(0, 10, 0)
            ),
            "blade_of_alacrity"
    );
    public static final Item STAFF_OF_WIZARDRY = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.RADIANT_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcSwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_COMPONENT, 1, -1.5f
                            ))
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 0, 10
                                    )),
                    new TooltipBuilder("staff_of_wizardry")
                            .description()
                            .stats(0, 0, 10)
            ),
            "staff_of_wizardry"
    );
    public static final Item DEMON_EDGE = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.DIRE_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcSwordItem.createAttributes(
                                    DotcToolMaterial.DIRE_COMPONENT, 5, -2.0f
                            )),
                    new TooltipBuilder("demon_edge").description()
            ),
            "demon_edge"
    );
    public static final Item DIVINE_RAPIER = DotcItems.register(
            new DotcSwordItem(
                    DotcToolMaterial.RADIANT_ARTEFACT,
                    new Item.Properties()
                            .attributes(DotcSwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_ARTEFACT, 10, -3.0f
                            )),
                    new TooltipBuilder("divine_rapier").description()
            ),
            "divine_rapier"
    );

    public static void initialize() {
    }
}
