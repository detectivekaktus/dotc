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
public class PlayerStats {
    public static final AttachmentType<Integer> STRENGTH = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "strength"),
            integerBuilder ->
                    integerBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_STRENGTH)
                            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
                            .persistent(Codec.INT)
    );
    public static final AttachmentType<Float> HP_REGEN = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "hp_regen"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_HP_REGEN)
                            .persistent(Codec.FLOAT)
    );
    public static final AttachmentType<Float> HP_REGEN_AMPLIFICATION = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "hp_regen_amplification"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_HP_REGEN_AMPLIFICATION)
                            .persistent(Codec.FLOAT)
    );
    public static final AttachmentType<Integer> HP_TICK = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "hp_tick"),
            integerBuilder ->
                    integerBuilder.initializer(() -> 0)
                            .persistent(Codec.INT)
    );

    public static final AttachmentType<Integer> AGILITY = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "agility"),
            integerBuilder ->
                    integerBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_AGILITY)
                            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
                            .persistent(Codec.INT)
    );
    public static final AttachmentType<Float> EVASION = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "evasion"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_EVASION)
                            .persistent(Codec.FLOAT)
    );
    public static final AttachmentType<Integer> EVASION_SCALE = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "evasion_scale"),
            integerBuilder ->
                    integerBuilder.initializer(() -> 0)
                            .persistent(Codec.INT)
    );
    public static final AttachmentType<Float> MOVE_SPEED = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "move_speed"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MOVE_SPEED_BONUS)
                            .persistent(Codec.FLOAT)
    );

    public static final AttachmentType<Integer> INTELLIGENCE = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "intelligence"),
            integerBuilder ->
                    integerBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_INTELLIGENCE)
                            .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
                            .persistent(Codec.INT)
    );
    public static final AttachmentType<Float> MAGIC_RESISTANCE = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(DefenseOfTheCraft.MOD_ID, "magic_resistance"),
            floatBuilder ->
                    floatBuilder.initializer(() -> DotcAttachmentRules.DEFAULT_MAGIC_RESISTANCE)
                            .persistent(Codec.FLOAT)
    );

    public static void initialize() { }

    public static StatsData get(AttachmentTarget target) {
        return new StatsData(target);
    }

    public record StatsData(AttachmentTarget target) {
        public int getStrength() {
            return target.getAttachedOrCreate(STRENGTH);
        }

        public int setStrength(int val) {
            var current = getStrength();
            return setOrFallback(
                    STRENGTH,
                    Math.max(val, DotcAttachmentRules.DEFAULT_STRENGTH),
                    current
            );
        }

        public int getHpTick() {
            return target.getAttachedOrCreate(HP_TICK);
        }

        public int setHpTick(int val) {
            var current = getHpTick();
            return setOrFallback(
                    HP_TICK,
                    Math.clamp(val, 0, 20),
                    current
            );
        }

        public float getHpRegen() {
            return target.getAttachedOrCreate(HP_REGEN);
        }

        public float setHpRegen(float val) {
            var current = getHpRegen();
            return setOrFallback(
                    HP_REGEN,
                    Math.max(val, DotcAttachmentRules.DEFAULT_HP_REGEN),
                    current
            );
        }

        public float getHpRegenAmplification() {
            return target.getAttachedOrCreate(HP_REGEN_AMPLIFICATION);
        }

        public float setHpRegenAmplification(float val) {
            var current = getHpRegenAmplification();
            return setOrFallback(
                    HP_REGEN_AMPLIFICATION,
                    Math.max(val, DotcAttachmentRules.DEFAULT_HP_REGEN_AMPLIFICATION),
                    current
            );
        }

        public int getAgility() {
            return target.getAttachedOrCreate(AGILITY);
        }

        public int setAgility(int val) {
            var current = getAgility();
            return setOrFallback(
                    AGILITY,
                    Math.max(val, DotcAttachmentRules.DEFAULT_AGILITY),
                    current
            );
        }

        public float getEvasion() {
            return target.getAttachedOrCreate(EVASION);
        }

        public float setEvasion(float val) {
            var current = getEvasion();
            return setOrFallback(
                    EVASION,
                    Math.clamp(val, DotcAttachmentRules.DEFAULT_EVASION, 0.99f),
                    current
            );
        }

        public int getEvasionScale() {
            return target.getAttachedOrCreate(EVASION_SCALE);
        }

        public int addEvasionScale(int val) {
            var current = getEvasionScale();
            return modifyOrFallback(
                    EVASION_SCALE,
                    scale -> Math.max(scale + val, 0),
                    current
            );
        }

        public int setEvasionScale(int val) {
            var current = getEvasionScale();
            return setOrFallback(
                    EVASION_SCALE,
                    Math.max(val, 0),
                    current
            );
        }

        public float getMoveSpeed() {
            return target.getAttachedOrCreate(MOVE_SPEED);
        }

        public float setMoveSpeed(float val) {
            var current = getMoveSpeed();
            return setOrFallback(
                    MOVE_SPEED,
                    Math.max(val, 0.0f),
                    current
            );
        }

        public int getIntelligence() {
            return target.getAttachedOrCreate(INTELLIGENCE);
        }

        public int setIntelligence(int val) {
            var current = getIntelligence();
            return setOrFallback(
                    INTELLIGENCE,
                    Math.max(val, DotcAttachmentRules.DEFAULT_INTELLIGENCE),
                    current
            );
        }

        public float getMagicResistance() {
            return target.getAttachedOrCreate(MAGIC_RESISTANCE);
        }

        public float setMagicResistance(float val) {
            var current = getMagicResistance();
            return setOrFallback(
                    MAGIC_RESISTANCE,
                    Math.clamp(val, DotcAttachmentRules.DEFAULT_MAGIC_RESISTANCE, 1.0f),
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

        private float modifyOrFallback(AttachmentType<Float> key, UnaryOperator<Float> value, float fallback) {
            var res = target.modifyAttached(key, value);
            return res == null ? fallback : res;
        }

        private float setOrFallback(AttachmentType<Float> key, float value, float fallback) {
            var res = target.setAttached(key, value);
            return res == null ? fallback : res;
        }
    }
}
