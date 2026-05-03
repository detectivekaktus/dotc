package net.detectivekaktus.client.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

import net.detectivekaktus.tag.DotcEntityTypeTags;

public class DotcEntityTagProvider extends FabricTagProvider<EntityType<?>> {
    public DotcEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENTITY_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(DotcEntityTypeTags.DIFFUSAL_BLADE_INVULNERABLE)
                .add(EntityType.ENDER_DRAGON)
                .add(EntityType.WITHER)
                .add(EntityType.WARDEN);

        getOrCreateTagBuilder(DotcEntityTypeTags.ABYSSAL_BLADE_INVULNERABLE)
                .add(EntityType.ENDER_DRAGON)
                .add(EntityType.WITHER);
    }
}
