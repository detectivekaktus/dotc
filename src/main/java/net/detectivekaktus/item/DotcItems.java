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
import net.detectivekaktus.item.component.DotcItemComponents;
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
        DotcItemComponents.initialize();
        DotcTools.initialize();

        ItemGroupEvents.modifyEntriesEvent(DOTC_ITEM_GROUP_KEY).register(group -> Stream.of(
                DotcIngredients.RADIANT_CRYSTAL,
                DotcIngredients.RADIANT_CRYSTAL_DUST,
                DotcIngredients.DIRE_CRYSTAL,
                DotcIngredients.DIRE_CRYSTAL_DUST,
                DotcIngredients.MITHRIL_INGOT,

                DotcIngredients.TALISMAN_OF_EVASION,
                DotcIngredients.EAGLESONG,
                DotcIngredients.SACRED_RELIC,
                DotcItemComponents.DEMON_EDGE,

                DotcIngredients.BLIGHT_STONE,
                DotcIngredients.GLOVES_OF_HASTE,
                DotcIngredients.BLADES_OF_ATTACK,
                DotcItemComponents.MITHRIL_HAMMER,
                DotcItemComponents.BROADSWORD,
                DotcItemComponents.CLAYMORE,
                DotcTools.JAVELIN,

                DotcItemComponents.OGRE_AXE,
                DotcItemComponents.BLADE_OF_ALACRITY,
                DotcItemComponents.STAFF_OF_WIZARDRY,

                DotcTools.CRYSTALYS,
                DotcTools.DAEDALUS,
                DotcTools.BUTTERFLY,
                DotcTools.MONKEY_KING_BAR,

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
