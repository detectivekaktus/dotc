package net.detectivekaktus.attach;

import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;

import net.detectivekaktus.DefenseOfTheCraft;

import java.util.function.UnaryOperator;

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
            return target.getAttachedOrCreate(CURRENT_MANA);
        }

        public float consume(float val) {
            var maxMana = getMaxMana();
            var current = getCurrentMana();
            return modifyOrFallback(
                    CURRENT_MANA,
                    currentMana -> Math.clamp(currentMana - val, DotcAttachmentRules.MIN_MANA, maxMana),
                    current
            );
        }

        public float increment(float val) {
            var maxMana = getMaxMana();
            var current = getCurrentMana();
            return modifyOrFallback(
                    CURRENT_MANA,
                    currentMana -> Math.clamp(currentMana + val, DotcAttachmentRules.MIN_MANA, maxMana),
                    current
            );
        }

        public float setCurrentMana(float val) {
            var maxMana = getMaxMana();
            var current = getCurrentMana();
            return setOrFallback(
                    CURRENT_MANA,
                    Math.clamp(val, DotcAttachmentRules.MIN_MANA, maxMana),
                    current
            );
        }

        public float getMaxMana() {
            return target.getAttachedOrCreate(MAX_MANA);
        }

        public float setMaxMana(float val) {
            var previousMax = setOrFallback(
                    MAX_MANA,
                    val,
                    DotcAttachmentRules.DEFAULT_MAX_MANA
            );

            var current = getCurrentMana();
            var manaPercent = Math.clamp(current / previousMax, 0, 1);
            var newMax = getMaxMana();
            modifyOrFallback(
                    CURRENT_MANA,
                    currentMana -> newMax * manaPercent,
                    current
            );

            return previousMax;
        }

        private float modifyOrFallback(AttachmentType<Float> key, UnaryOperator<Float> f, float fallback) {
            var res = target.modifyAttached(key, f);
            return res == null ? fallback : res;
        }

        private float setOrFallback(AttachmentType<Float> key, float value, float fallback) {
            var res = target.setAttached(key, value);
            return res == null ? fallback : res;
        }
    }
}
