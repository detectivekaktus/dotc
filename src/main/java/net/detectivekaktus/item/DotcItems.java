package net.detectivekaktus.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.block.natural.DotcNaturalBlocks;
import net.detectivekaktus.block.building.DotcBuildingBlocks;
import net.detectivekaktus.item.primitive.DotcPrimitives;
import net.detectivekaktus.item.ingredient.DotcIngredients;
import net.detectivekaktus.item.tool.DotcTools;

import java.util.stream.Stream;

public class DotcItems {
    public static final ResourceKey<CreativeModeTab> DOTC_ITEM_GROUP_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "item_group")
    );
    public static final CreativeModeTab DOTC_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DotcIngredients.DIRE_CRYSTAL))
            .title(Component.translatable("itemGroup.defense-of-the-craft"))
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DOTC_ITEM_GROUP_KEY, DOTC_ITEM_GROUP);

        DotcIngredients.initialize();
        DotcPrimitives.initialize();
        DotcTools.initialize();

        ItemGroupEvents.modifyEntriesEvent(DOTC_ITEM_GROUP_KEY).register(group -> Stream.of(
                DotcIngredients.RADIANT_CRYSTAL_SHARDS,
                DotcIngredients.RADIANT_CRYSTAL,
                DotcIngredients.RADIANT_CRYSTAL_DUST,
                DotcIngredients.DIRE_CRYSTAL_SHARDS,
                DotcIngredients.DIRE_CRYSTAL,
                DotcIngredients.DIRE_CRYSTAL_DUST,
                DotcIngredients.MITHRIL_INGOT,

                DotcIngredients.TALISMAN_OF_EVASION,
                DotcIngredients.EAGLESONG,
                DotcIngredients.SACRED_RELIC,
                DotcPrimitives.DEMON_EDGE,

                DotcIngredients.BLIGHT_STONE,
                DotcIngredients.GLOVES_OF_HASTE,
                DotcIngredients.BLADES_OF_ATTACK,
                DotcPrimitives.MITHRIL_HAMMER,
                DotcPrimitives.BROADSWORD,
                DotcPrimitives.CLAYMORE,
                DotcTools.JAVELIN,

                DotcPrimitives.OGRE_AXE,
                DotcPrimitives.BLADE_OF_ALACRITY,
                DotcPrimitives.STAFF_OF_WIZARDRY,
                DotcIngredients.BELT_OF_STRENGTH,
                DotcIngredients.BAND_OF_ELVENSKIN,
                DotcIngredients.ROBE_OF_THE_MAGI,

                DotcTools.CRYSTALYS,
                DotcTools.DAEDALUS,
                DotcTools.BUTTERFLY,
                DotcTools.MONKEY_KING_BAR,

                DotcTools.SANGE,
                DotcTools.YASHA,
                DotcTools.KAYA,

                DotcNaturalBlocks.RADIANT_ORE.asItem(),
                DotcNaturalBlocks.DEEPSLATE_RADIANT_ORE.asItem(),
                DotcNaturalBlocks.DIRE_ORE.asItem(),
                DotcBuildingBlocks.RADIANT_CRYSTAL_BLOCK.asItem(),
                DotcBuildingBlocks.DIRE_CRYSTAL_BLOCK.asItem()
        ).forEach(group::accept));
    }

    public static Item register(Item item, String id) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id),
                item
        );
    }
}
