package net.detectivekaktus.component.dummy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.component.DataComponentType;

import net.detectivekaktus.component.DotcComponents;

public class DummyComponents {
    private static final Codec<ItemStatsComponent> ITEM_STATS_COMPONENT_CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                    Codec.INT.optionalFieldOf("strength", 0).forGetter(ItemStatsComponent::strength),
                    Codec.INT.optionalFieldOf("agility", 0).forGetter(ItemStatsComponent::agility),
                    Codec.INT.optionalFieldOf("intelligence", 0).forGetter(ItemStatsComponent::intelligence)
            ).apply(builder, ItemStatsComponent::new)
    );
    public static final DataComponentType<ItemStatsComponent> ITEM_STATS_COMPONENT = DotcComponents.register(
            "item_stats",
            ITEM_STATS_COMPONENT_CODEC
    );

    public static void initialize() { }
}
