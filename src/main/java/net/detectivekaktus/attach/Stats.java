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

@SuppressWarnings({"ApiStatus.Experimental", "UnstableApiUsage"})
public class Stats {
    private static final AttachmentType<Integer> STRENGTH = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "strength"),
            integerBuilder ->
                    integerBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_STRENGTH)
                            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
                            .persistent(Codec.INT)
    );
    private static final AttachmentType<Integer> AGILITY = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "agility"),
            integerBuilder ->
                    integerBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_AGILITY)
                            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
                            .persistent(Codec.INT)
    );
    private static final AttachmentType<Integer> INTELLIGENCE = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "intelligence"),
            integerBuilder ->
                    integerBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_INTELLIGENCE)
                            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
                            .persistent(Codec.INT)
    );

    public static StatsData get(AttachmentTarget target) {
        return new StatsData(target);
    }

    public record StatsData(AttachmentTarget target) {
        public int getStrength() {
            return target.getAttachedOrCreate(STRENGTH);
        }

        public int addStrength(int val) {
            var current = getStrength();
            return modifyOrFallback(
                    STRENGTH,
                    strength -> Math.max(strength + val, DotcAttachmentRules.DEFAULT_STRENGTH),
                    current
            );
        }

        public int removeStrength(int val) {
            var current = getStrength();
            return modifyOrFallback(
                    STRENGTH,
                    strength -> Math.max(strength - val, DotcAttachmentRules.DEFAULT_STRENGTH),
                    current
            );
        }

        public int setStrength(int val) {
            var current = getStrength();
            return setOrFallback(
                    STRENGTH,
                    Math.max(val, DotcAttachmentRules.DEFAULT_STRENGTH),
                    current
            );
        }

        public int getAgility() {
            return target.getAttachedOrCreate(AGILITY);
        }

        public int addAgility(int val) {
            var current = getAgility();
            return modifyOrFallback(
                    AGILITY,
                    agility -> Math.max(agility + val, DotcAttachmentRules.DEFAULT_AGILITY),
                    current
            );
        }

        public int removeAgility(int val) {
            var current = getAgility();
            return modifyOrFallback(
                    AGILITY,
                    agility -> Math.max(agility - val, DotcAttachmentRules.DEFAULT_AGILITY),
                    current
            );
        }

        public int setAgility(int val) {
            var current = getAgility();
            return setOrFallback(
                    AGILITY,
                    Math.max(val, DotcAttachmentRules.DEFAULT_AGILITY),
                    current
            );
        }

        public int getIntelligence() {
            return target.getAttachedOrCreate(INTELLIGENCE);
        }

        public int addIntelligence(int val) {
            var current = getIntelligence();
            return modifyOrFallback(
                    INTELLIGENCE,
                    intelligence -> Math.max(intelligence + val, DotcAttachmentRules.DEFAULT_INTELLIGENCE),
                    current
            );
        }

        public int removeIntelligence(int val) {
            var current = getIntelligence();
            return modifyOrFallback(
                    INTELLIGENCE,
                    intelligence -> Math.max(intelligence - val, DotcAttachmentRules.DEFAULT_INTELLIGENCE),
                    current
            );
        }

        public int setIntelligence(int val) {
            var current = getIntelligence();
            return setOrFallback(
                    INTELLIGENCE,
                    Math.max(val, DotcAttachmentRules.DEFAULT_INTELLIGENCE),
                    current
            );
        }

        private int modifyOrFallback(AttachmentType<Integer> key, UnaryOperator<Integer> value, int fallback) {
            var res = target.modifyAttached(key, value);
            return res == null ? fallback : res;
        }

        private int setOrFallback(AttachmentType<Integer> key, int value, int fallback) {
            var res = target.setAttached(key, value);
            return res == null ? fallback : res;
        }
    }
}
