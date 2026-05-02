package net.detectivekaktus.mixin.player;

import net.detectivekaktus.effect.DotcEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.detectivekaktus.core.util.CombatManagerHolder;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    private boolean isNotMixinTarget(LivingEntity entity) {
        return entity.level().isClientSide || !(entity instanceof ServerPlayer);
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

        var player = (CombatManagerHolder) entity;
        if (!player.getCombatManager().hasEvaded())
            return;

        player.getCombatManager().setEvaded(false);
        callbackInfo.cancel();
    }

    @Inject(
            method = "travel",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void blockMovement(Vec3 vector, CallbackInfo callbackInfo) {
        var entity = (LivingEntity) (Object) this;
        if (!entity.hasEffect(DotcEffects.STUN))
            return;

        var deltaY = entity.getDeltaMovement().y;
        var y = deltaY < 0 ? deltaY : 0;
        entity.setDeltaMovement(
                0,
                y,
                0
        );
        callbackInfo.cancel();
    }
}
