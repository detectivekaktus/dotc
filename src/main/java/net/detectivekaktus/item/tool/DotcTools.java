package net.detectivekaktus.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;

import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ProcableComponent;
import net.detectivekaktus.component.records.ItemStatsComponent;
import net.detectivekaktus.item.DotcItems;
import net.detectivekaktus.item.DotcSpearItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.item.material.DotcToolMaterial;

public class DotcTools {
    public static final Item JAVELIN = DotcItems.register(
            new Javelin(
                    DotcToolMaterial.DIRE_COMPONENT,
                    new Item.Properties()
                            .attributes(DotcSpearItem.createAttributes(
                                    DotcToolMaterial.DIRE_COMPONENT, 1, -2.0f
                            ))
                            .component(
                                    DotcComponents.PROCABLE_COMPONENT,
                                    new ProcableComponent(Javelin.BASE_PROC_CHANCE, 0)
                            ),
                    new TooltipBuilder("javelin").description().passive()
            ),
            "javelin"
    );
    public static final Item CRYSTALYS = DotcItems.register(
            new Crystalys(
                    DotcToolMaterial.DIRE_ARTEFACT,
                    new Item.Properties()
                            .attributes(
                                    SwordItem.createAttributes(
                                            DotcToolMaterial.DIRE_ARTEFACT, 1, -2.0f
                                    )
                            )
                            .component(
                                    DotcComponents.PROCABLE_COMPONENT,
                                    new ProcableComponent(Crystalys.BASE_PROC_CHANCE, 0)
                            ),
                    new TooltipBuilder("crystalys").description().passive()
            ),
            "crystalys"
    );
    public static final Item DAEDALUS = DotcItems.register(
            new Daedalus(
                    DotcToolMaterial.DIRE_ARTEFACT,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(
                                    DotcToolMaterial.DIRE_ARTEFACT, 3, -2.0f
                            ))
                            .component(
                                    DotcComponents.PROCABLE_COMPONENT,
                                    new ProcableComponent(Daedalus.BASE_PROC_CHANCE, 0)
                            ),
                    new TooltipBuilder("daedalus").description().passive()
            ),
            "daedalus"
    );
    public static final Item BUTTERFLY = DotcItems.register(
            new Butterfly(
                    DotcToolMaterial.RADIANT_ARTEFACT,
                    new Item.Properties()
                            .attributes(
                                    SwordItem.createAttributes(
                                            DotcToolMaterial.RADIANT_ARTEFACT, 3, -2.0f
                                    )
                            )
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 35, 0)
                            )
                            .component(
                                    DotcComponents.EVASION_COMPONENT,
                                    Butterfly.BASE_PROC_CHANCE),
                    new TooltipBuilder("butterfly")
                            .description()
                            .stats(0, 35, 0)
                            .passive()
            ),
            "butterfly"
    );
    public static final Item MONKEY_KING_BAR = DotcItems.register(
            new MonkeyKingBar(
                    DotcToolMaterial.DIRE_ARTEFACT,
                    new Item.Properties()
                            .attributes(DotcSpearItem.createAttributes(
                                    DotcToolMaterial.DIRE_ARTEFACT, 2, -1.5f
                            ))
                            .component(
                                    DotcComponents.PROCABLE_COMPONENT,
                                    new ProcableComponent(MonkeyKingBar.BASE_PROC_CHANCE, 0)
                            ),
                    new TooltipBuilder("monkey_king_bar").description().passive()
            ),
            "monkey_king_bar"
    );
    public static final Item SANGE = DotcItems.register(
            new Sange(
                    DotcToolMaterial.DIRE_ARTEFACT,
                    new Item.Properties()
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
                            ),
                    new TooltipBuilder("sange")
                            .description()
                            .stats(16, 0, 0)
                            .passive()
            ),
            "sange"
    );
    public static final Item YASHA = DotcItems.register(
            new Yasha(
                    DotcToolMaterial.RADIANT_ARTEFACT,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_ARTEFACT, 2, -2.0f
                            ))
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 16, 0)
                            )
                            .component(
                                    DotcComponents.MOVE_SPEED_COMPONENT,
                                    Yasha.MOVE_SPEED_BONUS
                            ),
                    new TooltipBuilder("yasha")
                            .description()
                            .stats(0, 16, 0)
                            .passive()
            ),
            "yasha"
    );
    public static final Item KAYA = DotcItems.register(
            new Kaya(
                    DotcToolMaterial.RADIANT_ARTEFACT,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(
                                    DotcToolMaterial.RADIANT_ARTEFACT, 2, -2.5f
                            ))
                            .component(
                                    DotcComponents.ITEM_STATS_COMPONENT,
                                    new ItemStatsComponent(0, 0, 16)
                            )
                            .component(
                                    DotcComponents.MANA_COST_REDUCTION_COMPONENT,
                                    Kaya.MANA_COST_REDUCTION
                            ),
                    new TooltipBuilder("kaya")
                            .description()
                            .stats(0, 0, 16)
                            .passive()
            ),
            "kaya"
    );

    public static void initialize() { }
}
