package net.detectivekaktus.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.component.records.ItemStatsComponent;
import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.material.DotcToolMaterial;

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
    public static final Item CRYSTALYS = DotcItems.register(
            new Crystalys(DotcToolMaterial.DIRE_ARTEFACT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.DIRE_ARTEFACT, 1, -2.0f
                    ))
                    .component(
                            DotcComponents.PROCABLE_COMPONENT,
                            new ProcableComponent(Crystalys.BASE_PROC_CHANCE, 0)
                    )),
            "crystalys"
    );
    public static final Item DAEDALUS = DotcItems.register(
            new Daedalus(DotcToolMaterial.DIRE_ARTEFACT, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(
                            DotcToolMaterial.DIRE_ARTEFACT, 3, -2.0f
                    ))
                    .component(
                            DotcComponents.PROCABLE_COMPONENT,
                            new ProcableComponent(Daedalus.BASE_PROC_CHANCE, 0)
                    )),
            "daedalus"
    );
    public static final Item BUTTERFLY = DotcItems.register(
            new Butterfly(DotcToolMaterial.RADIANT_ARTEFACT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.RADIANT_ARTEFACT, 3, -2.0f
                    ))
                    .component(
                            DotcComponents.ITEM_STATS_COMPONENT,
                            new ItemStatsComponent(0, 35, 0)
                    )
                    .component(
                            DotcComponents.EVASION_COMPONENT,
                            Butterfly.BASE_PROC_CHANCE
                    )),
            "butterfly"
    );
    public static final Item MONKEY_KING_BAR = DotcItems.register(
            new MonkeyKingBar(DotcToolMaterial.DIRE_ARTEFACT, new Item.Properties()
                    .attributes(SpearItem.createAttributes(
                            DotcToolMaterial.DIRE_ARTEFACT, 2, -1.5f
                    ))
                    .component(
                            DotcComponents.PROCABLE_COMPONENT,
                            new ProcableComponent(MonkeyKingBar.BASE_PROC_CHANCE, 0)
                    )),
            "monkey_king_bar"
    );
    public static final Item SANGE = DotcItems.register(
            new Sange(DotcToolMaterial.DIRE_ARTEFACT, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            DotcToolMaterial.DIRE_ARTEFACT, 3, -2.5f
                    ))
                    .component(
                            DotcComponents.ITEM_STATS_COMPONENT,
                            new ItemStatsComponent(16, 0, 0)
                    )
                    .component(
                            DotcComponents.HP_REGEN_AMPLIFICATION_COMPONENT,
                            Sange.HP_REGEN_AMPLIFICATION
                    )),
            "sange"
    );

    public static void initialize() { }
}
