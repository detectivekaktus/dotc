package net.detectivekaktus.client.data.block.natural;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.block.natural.DotcNaturalBlocks;
import net.detectivekaktus.item.ingredient.DotcIngredients;

public class DotcNaturalBlockLootTableProvider extends FabricBlockLootTableProvider {
    public DotcNaturalBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(DotcNaturalBlocks.RADIANT_ORE, block ->
                createSilkTouchDispatchTable(block,
                        applyExplosionDecay(block,
                                LootItem.lootTableItem(DotcIngredients.RADIANT_CRYSTAL_SHARDS)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        ))
        );
        add(DotcNaturalBlocks.DEEPSLATE_RADIANT_ORE, block ->
                createSilkTouchDispatchTable(block,
                        applyExplosionDecay(block,
                                LootItem.lootTableItem(DotcIngredients.RADIANT_CRYSTAL_SHARDS)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        ))
        );
        add(DotcNaturalBlocks.DIRE_ORE, block ->
                createSilkTouchDispatchTable(block,
                        applyExplosionDecay(block,
                                LootItem.lootTableItem(DotcIngredients.DIRE_CRYSTAL_SHARDS)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        ))
        );
    }

    @Override
    public String getName() {
        return "defense-of-the-craft:natural_block_loottables";
    }
}
