package net.detectivekaktus.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.damage.DotcDamageTypes;

@Mixin(LivingEntity.class)
@SuppressWarnings({"ApiStatus.Experimental", "UnstableApiUsage"})
public class LivingEntityMixin {
    @ModifyVariable(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
            ),
            ordinal = 0
    )
    private float reduceMagicalDamage(float f, DamageSource damageSource) {
        var entity = (LivingEntity) (Object) this;
        var shouldSkip = entity.level().isClientSide
                || !damageSource.is(DotcDamageTypes.MAGICAL)
                || !(entity instanceof ServerPlayer)
                || !entity.hasAttached(PlayerStats.MAGIC_RESISTANCE);
        if (shouldSkip)
            return f;

        var stats = PlayerStats.get(entity);
        return f * 1 - stats.getMagicResistance();
    }
}
