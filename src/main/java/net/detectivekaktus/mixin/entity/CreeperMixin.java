package net.detectivekaktus.mixin.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

import net.detectivekaktus.effect.DotcEffects;

@Mixin(Creeper.class)
public class CreeperMixin {
    @WrapWithCondition(
            method = "explodeCreeper",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"
            )
    )
    private boolean explodeIfNotStunned(Level level, Entity entity, double x, double y, double z, float radius, Level.ExplosionInteraction interaction) {
        return !((Creeper) entity).hasEffect(DotcEffects.STUN);
    }

    @Inject(
            method = "spawnLingeringCloud",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cancelCloud(CallbackInfo callbackInfo) {
        var creeper = (Creeper) (Object) this;
        if (!creeper.hasEffect(DotcEffects.STUN))
            return;

        callbackInfo.cancel();
    }
}
