package net.detectivekaktus.attach;

import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;

import net.detectivekaktus.DefenseOfTheCraft;

// If you ever come back to it, see the details in the docs
// https://docs.fabricmc.net/develop/data-attachments#larger-attachments
@SuppressWarnings({"ApiStatus.Experimental", "UnstableApiUsage"})
public class Mana {
    private static final AttachmentType<Float> CURRENT_MANA = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "current_mana"),
            floatBuilder ->
                    floatBuilder.initializer(() -> 0.0f)
                            .syncWith(ByteBufCodecs.FLOAT, AttachmentSyncPredicate.all())
                            .persistent(Codec.FLOAT)
    );
    private static final AttachmentType<Float> MAX_MANA = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "max_mana"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MAX_MANA)
                            .syncWith(ByteBufCodecs.FLOAT, AttachmentSyncPredicate.all())
                            .persistent(Codec.FLOAT)
    );

    public ManaData get(AttachmentTarget target) {
        return new ManaData(target);
    }

    public record ManaData(AttachmentTarget target) {
        public float getCurrentMana() {
            return target.getAttachedOrElse(CURRENT_MANA, 0.0f);
        }

        public float consume(float val) {
            return target.modifyAttached(CURRENT_MANA, current_mana -> current_mana - val);
        }

        public float increment(float val) {
            return target.modifyAttached(CURRENT_MANA, current_mana -> current_mana + val);
        }

        public float setCurrentMana(float val) {
            return target.setAttached(CURRENT_MANA, val);
        }

        public float getMaxMana() {
            return target.getAttachedOrElse(MAX_MANA, DotcAttachmentRules.DEFAULT_MAX_MANA);
        }

        public float setMaxMana(float val) {
            return target.setAttached(MAX_MANA, val);
        }
    }
}
