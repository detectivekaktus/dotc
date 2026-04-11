package net.detectivekaktus.item.tool;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;

import net.detectivekaktus.component.dummy.DummyComponents;
import net.detectivekaktus.component.dummy.ItemStatsComponent;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.material.DotcToolMaterial;

public class DotcTools {
    public static final Item MITHRIL_HAMMER = DotcItems.register(
            new MithrilHammer(DotcToolMaterial.RADIANT_COMPONENT, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(
                                        // this g param default to 4 in vanilla
                            DotcToolMaterial.RADIANT_COMPONENT, 1, -2.8f
                    ))),
            "mithril_hammer"
    );
    public static final Item BROADSWORD = DotcItems.register(
            new Broadsword(DotcToolMaterial.RADIANT_COMPONENT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.RADIANT_COMPONENT, 4, -2.0f
                    ))),
            "broadsword"
    );
    public static final Item CLAYMORE = DotcItems.register(
            new Claymore(DotcToolMaterial.DIRE_COMPONENT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.DIRE_COMPONENT, 4, -1.5f
                    ))),
            "claymore"
    );
    public static final Item BLADE_OF_ALACRITY = DotcItems.register(
            new BladeOfAlacrity(DotcToolMaterial.RADIANT_COMPONENT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.RADIANT_COMPONENT, 0, -1.0f
                    ))
                    .component(
                            DummyComponents.ITEM_STATS_COMPONENT,
                            new ItemStatsComponent(0, 10, 0
                            ))),
            "blade_of_alacrity"
    );
    public static final Item OGRE_AXE = DotcItems.register(
            new OgreAxe(DotcToolMaterial.DIRE_COMPONENT, new Item.Properties()
                    .attributes(AxeItem.createAttributes(
                            DotcToolMaterial.DIRE_COMPONENT, 6, -3.0f
                    ))
                    .component(
                            DummyComponents.ITEM_STATS_COMPONENT,
                            new ItemStatsComponent(10, 0, 0
                            ))),
            "ogre_axe"
    );
    public static final Item STAFF_OF_WIZARDRY = DotcItems.register(
            new StaffOfWizardry(DotcToolMaterial.RADIANT_COMPONENT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.RADIANT_COMPONENT, 1, -1.5f
                    ))
                    .component(
                            DummyComponents.ITEM_STATS_COMPONENT,
                            new ItemStatsComponent(0, 0, 10
                            ))),
            "staff_of_wizardry"
    );
    public static final Item DEMON_EDGE = DotcItems.register(
            new DemonEdge(DotcToolMaterial.DIRE_COMPONENT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.DIRE_COMPONENT, 5, -2.0f
                    ))),
            "demon_edge"
    );

    public static void initialize() { }
}
