package net.detectivekaktus.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.attach.PlayerStats;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.mixin.interfaces.Evadable;

@Mixin(LivingEntity.class)
@SuppressWarnings({"ApiStatus.Experimental", "UnstableApiUsage"})
public class LivingEntityMixin {
    @Unique
    private boolean isNotMixinTarget(LivingEntity entity) {
        return entity.level().isClientSide || !(entity instanceof ServerPlayer);
    }

    @ModifyVariable(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/entity/LivingEntity;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
            ),
            ordinal = 0
    )
    private float applyDotcDamageReduction(float f, DamageSource damageSource) {
        var entity = (LivingEntity) (Object) this;
        var shouldSkip = isNotMixinTarget(entity)
                || !damageSource.is(DotcDamageTypes.MAGICAL)
                || !entity.hasAttached(PlayerStats.MAGIC_RESISTANCE);
        if (shouldSkip)
            return f;

        var stats = PlayerStats.get(entity);
        return f * (1.0f - stats.getMagicResistance());
    }


    @Inject(
            method = "knockback",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelKnockbackIfEvaded(double d, double e, double f, CallbackInfo callbackInfo) {
        var entity = (LivingEntity) (Object) this;
        if (isNotMixinTarget(entity))
            return;

        var player = (Evadable) entity;
        if (!player.getEvaded())
            return;

        player.setEvaded(false);
        callbackInfo.cancel();
    }
}
