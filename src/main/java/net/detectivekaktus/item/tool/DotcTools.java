package net.detectivekaktus.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;

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

    public static void initialize() { }
}
