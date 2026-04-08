package net.detectivekaktus.item.tool;

import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.material.DotcToolMaterial;
import net.minecraft.world.item.Item;

public class DotcTools {
    public static final Item MITHRIL_HAMMER = DotcItems.register(
            new MithrilHammer(DotcToolMaterial.RADIANT_COMPONENT, new Item.Properties()),
            "mithril_hammer"
    );

    public static void initialize() { }
}
