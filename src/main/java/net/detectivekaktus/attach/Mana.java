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
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MAX_MANA)
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

    public static ManaData get(AttachmentTarget target) {
        return new ManaData(target);
    }

    // I honestly don't know how to ensure all attachment fields.
    // I'm not even sure the attachment exists on player entity before I tested it
    // (cause of my ensuring strategy). I hope during review I'll get rid of this abomination
    // of implementation.
    public record ManaData(AttachmentTarget target) {
        public float getCurrentMana() {
            var maxMana = getMaxMana();
            return target.getAttachedOrSet(CURRENT_MANA, maxMana);
        }

        public float consume(float val) {
            var maxMana = getMaxMana();
            var current = getCurrentMana();
            var res = target.modifyAttached(
                    CURRENT_MANA,
                    currentMana -> Math.clamp(currentMana - val, DotcAttachmentRules.MIN_MANA, maxMana)
            );
            return res == null ? current : res;
        }

        public float increment(float val) {
            var maxMana = getMaxMana();
            var current = getCurrentMana();
            var res = target.modifyAttached(
                    CURRENT_MANA,
                    currentMana -> Math.clamp(currentMana + val, DotcAttachmentRules.MIN_MANA, maxMana)
            );
            return res == null ? current : res;
        }

        public float setCurrentMana(float val) {
            var maxMana = getMaxMana();
            var current = getCurrentMana();
            var res = target.setAttached(
                    CURRENT_MANA,
                    Math.clamp(val, DotcAttachmentRules.MIN_MANA, maxMana)
            );
            return res == null ? current : res;
        }

        public float getMaxMana() {
            return target.getAttachedOrSet(MAX_MANA, DotcAttachmentRules.DEFAULT_MAX_MANA);
        }

        public float setMaxMana(float val) {
            // this call is needed to ensure the max value exists on the target
            var maxMana = getMaxMana();
            var res = target.setAttached(MAX_MANA, val);
            return res == null ? maxMana : res;
        }
    }
}
