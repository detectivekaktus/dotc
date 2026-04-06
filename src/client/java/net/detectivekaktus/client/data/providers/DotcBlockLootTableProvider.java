package net.detectivekaktus.client.data.providers;

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

import net.detectivekaktus.item.ingredients.DotcIngredients;
import net.detectivekaktus.block.natural.DotcNaturalBlocks;

public class DotcBlockLootTableProvider extends FabricBlockLootTableProvider {
    public DotcBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(DotcNaturalBlocks.RADIANT_ORE, block ->
                createSilkTouchDispatchTable(block,
                        applyExplosionDecay(block,
                                LootItem.lootTableItem(DotcIngredients.RADIANT_CRYSTAL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        ))
        );
        add(DotcNaturalBlocks.DEEPSLATE_RADIANT_ORE, block ->
                createSilkTouchDispatchTable(block,
                        applyExplosionDecay(block,
                                LootItem.lootTableItem(DotcIngredients.RADIANT_CRYSTAL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        ))
        );
        add(DotcNaturalBlocks.DIRE_ORE, block ->
                createSilkTouchDispatchTable(block,
                        applyExplosionDecay(block,
                                LootItem.lootTableItem(DotcIngredients.DIRE_CRYSTAL)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        ))
        );
    }
}
