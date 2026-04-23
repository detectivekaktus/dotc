package net.detectivekaktus.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.component.records.ItemStatsComponent;
import net.detectivekaktus.component.records.ProcableComponent;

public class DotcComponents {
    public static final Codec<ItemStatsComponent> ITEM_STATS_COMPONENT_CODEC = RecordCodecBuilder.create(
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

    public static final Codec<ProcableComponent> PROCABLE_COMPONENT_CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                    Codec.FLOAT.fieldOf("baseChance").forGetter(ProcableComponent::baseChance),
                    Codec.INT.optionalFieldOf("scale", 0).forGetter(ProcableComponent::scale)
            ).apply(builder, ProcableComponent::new)
    );
    public static final DataComponentType<ProcableComponent> PROCABLE_COMPONENT = register(
            "procable",
            PROCABLE_COMPONENT_CODEC
    );

    public static final DataComponentType<Float> EVASION_COMPONENT = registerFloat("evasion");

    public static final DataComponentType<Float> HP_REGEN_AMPLIFICATION_COMPONENT = registerFloat("hp_regen_amplification");

    public static <T> DataComponentType<T> register(String id, Codec<T> codec) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id),
                DataComponentType.<T>builder().persistent(codec).build()
        );
    }

    public static DataComponentType<Float> registerFloat(String id) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id),
                DataComponentType.<Float>builder().persistent(Codec.FLOAT).build()
        );
    }

    public static void initialize() { }
}
