package net.detectivekaktus.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.component.records.ItemStatsComponent;

public class DotcComponents {
    private static final Codec<ItemStatsComponent> ITEM_STATS_COMPONENT_CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                    Codec.INT.optionalFieldOf("strength", 0).forGetter(ItemStatsComponent::strength),
                    Codec.INT.optionalFieldOf("agility", 0).forGetter(ItemStatsComponent::agility),
                    Codec.INT.optionalFieldOf("intelligence", 0).forGetter(ItemStatsComponent::intelligence)
            ).apply(builder, ItemStatsComponent::new)
    );
    public static final DataComponentType<ItemStatsComponent> ITEM_STATS_COMPONENT = register(
            "item_stats",
            ITEM_STATS_COMPONENT_CODEC
    );

    public static <T> DataComponentType<T> register(String id, Codec<T> codec) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id),
                DataComponentType.<T>builder().persistent(codec).build()
        );
    }

    public static void initialize() { }
}
