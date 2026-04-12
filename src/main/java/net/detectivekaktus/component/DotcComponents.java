package net.detectivekaktus.component;

import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.component.dummy.DummyComponents;

public class DotcComponents {
    public static <T> DataComponentType<T> register(String id, Codec<T> codec) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, id),
                DataComponentType.<T>builder().persistent(codec).build()
        );
    }

    public static void initialize() {
        DummyComponents.initialize();
    }
}
