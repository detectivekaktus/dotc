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
public class PlayerMana {
    public static final AttachmentType<Float> CURRENT_MANA = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "current_mana"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MAX_MANA)
                            .syncWith(ByteBufCodecs.FLOAT, AttachmentSyncPredicate.targetOnly())
                            .persistent(Codec.FLOAT)
    );
    public static final AttachmentType<Float> MAX_MANA = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "max_mana"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MAX_MANA)
                            .syncWith(ByteBufCodecs.FLOAT, AttachmentSyncPredicate.targetOnly())
                            .persistent(Codec.FLOAT)
    );
    public static final AttachmentType<Float> MANA_REGEN = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "mana_regen"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MANA_REGEN)
                            .persistent(Codec.FLOAT)
    );
    public static final AttachmentType<Float> BONUS_MANA_REGEN = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "bonus_mana_regen"),
            floatBuilder ->
                    floatBuilder.initializer(() -> 0.0f)
                            .persistent(Codec.FLOAT)
    );

    public static final AttachmentType<Integer> MANA_TICK = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "mana_tick"),
            integerBuilder ->
                    integerBuilder.initializer(() -> 0)
                            .persistent(Codec.INT)
    );

    public static final AttachmentType<Float> MANA_COST_REDUCTION = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "mana_cost_reduction"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MANA_COST_REDUCTION)
                            .persistent(Codec.FLOAT)
    );

    public static void initialize() { }

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

        public float getManaRegen() {
            return target.getAttachedOrCreate(MANA_REGEN);
        }

        public float setManaRegen(float val) {
            var current = getManaRegen();
            return setOrFallback(
                    MANA_REGEN,
                    Math.max(val, DotcAttachmentRules.DEFAULT_MANA_REGEN),
                    current
            );
        }

        public float getBonusManaRegen() {
            return target.getAttachedOrCreate(BONUS_MANA_REGEN);
        }

        public float setBonusManaRegen(float val) {
            var current = getBonusManaRegen();
            return setOrFallback(
                    BONUS_MANA_REGEN,
                    Math.max(val, 0.0f),
                    current
            );
        }

        public int getManaTick() {
            return target.getAttachedOrCreate(MANA_TICK);
        }

        public int setManaTick(int val) {
            var current = getManaTick();
            return setOrFallback(
                    MANA_TICK,
                    Math.clamp(val, 0, 20),
                    current
            );
        }

        public float getManaCostReduction() {
            return target.getAttachedOrCreate(MANA_COST_REDUCTION);
        }

        public float setManaCostReduction(float val) {
            var current = getManaCostReduction();
            return setOrFallback(
                    MANA_COST_REDUCTION,
                    Math.max(val, DotcAttachmentRules.DEFAULT_MANA_COST_REDUCTION),
                    current
            );
        }

        private float modifyOrFallback(AttachmentType<Float> key, UnaryOperator<Float> f, float fallback) {
            var res = target.modifyAttached(key, f);
            return res == null ? fallback : res;
        }

        private float setOrFallback(AttachmentType<Float> key, float value, float fallback) {
            var res = target.setAttached(key, value);
            return res == null ? fallback : res;
        }

        private int modifyOrFallback(AttachmentType<Integer> key, UnaryOperator<Integer> f, int fallback) {
            var res = target.modifyAttached(key, f);
            return res == null ? fallback : res;
        }

        private int setOrFallback(AttachmentType<Integer> key, int value, int fallback) {
            var res = target.setAttached(key, value);
            return res == null ? fallback : res;
        }
    }
}
