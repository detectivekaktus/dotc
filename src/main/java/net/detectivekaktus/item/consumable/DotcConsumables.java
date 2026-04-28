package net.detectivekaktus.item.consumable;

import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.TooltipBuilder;
import net.minecraft.world.item.Item;

public class DotcConsumables {
    public static final Item TANGO = DotcItems.register(
            new Tango(
                    new Item.Properties(),
                    new TooltipBuilder("tango")
                            .description()
                            .active()
            ),
            "tango"
    );

    public static void initialize() { }
}
