package net.detectivekaktus.item.tool;

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.material.DotcToolMaterial;
import net.minecraft.world.item.Item;

public class DotcTools {
    public static final Item JAVELIN = DotcItems.register(
            new Javelin(DotcToolMaterial.DIRE_COMPONENT, new Item.Properties()
                    .attributes(SpearItem.createAttributes(
                            DotcToolMaterial.DIRE_COMPONENT, 1, -2.0f
                    ))
                    .component(
                            DotcComponents.PROCABLE_COMPONENT,
                            new ProcableComponent(Javelin.BASE_PROC_CHANCE, 0)
                    )),
            "javelin"
    );

    public static void initialize() { }
}
