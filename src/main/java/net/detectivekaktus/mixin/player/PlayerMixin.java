package net.detectivekaktus.mixin.player;

import com.mojang.authlib.GameProfile;

import net.detectivekaktus.item.tool.DotcTools;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.detectivekaktus.core.player.PlayerCombatManager;
import net.detectivekaktus.core.util.CombatManagerHolder;

@Mixin(Player.class)
public class PlayerMixin implements CombatManagerHolder {
    @Unique
    @Final
    public PlayerCombatManager dotc$combatManager;

    @Override
    public PlayerCombatManager getCombatManager() {
        return dotc$combatManager;
    }

    @Unique
    private boolean isNotMixinTarget(Player player) {
        return player.level().isClientSide || !(player instanceof ServerPlayer);
    }

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL")
    )
    private void addCombatManager(Level level, BlockPos blockPos, float f, GameProfile gameProfile, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return;

        this.dotc$combatManager = new PlayerCombatManager(player);
    }

    @ModifyVariable(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getWeaponItem()Lnet/minecraft/world/item/ItemStack;",
                    shift = At.Shift.BEFORE
            ),
            ordinal = 0
    )
    private float preDamageCalculatedHook(float original, Entity entity) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return original;

        var stack = player.getWeaponItem();
        var damage =  dotc$combatManager.crit(original);

        if (stack.is(DotcTools.DIFFUSAL_BLADE) && (entity instanceof Player attacked))
            damage += dotc$combatManager.addManaBurnDamage(attacked);

        return damage;
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
                    shift = At.Shift.BEFORE
            )
    )
    private void preTargetHurtHook(Entity entity, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return;

        dotc$combatManager.calculateProcs();
    }

    @ModifyExpressionValue(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    private boolean postTargetHurtHook(boolean hurt, Entity entity) {
        if (!hurt)
            return false;

        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return hurt;

        hurt = dotc$combatManager.proc(entity, hurt);

        var stack = player.getWeaponItem();
        var hitPlayerThroughEvasion = dotc$combatManager.hitThroughEvasion() && (entity instanceof Player);
        if (hitPlayerThroughEvasion && stack.is(DotcTools.DIFFUSAL_BLADE))
            dotc$combatManager.burnMana((Player) entity);

        return hurt;
    }

    @Inject(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void applyEvasion(DamageSource damageSource, float f, CallbackInfo callbackInfo) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return;

        if (dotc$combatManager.evade(damageSource))
            callbackInfo.cancel();
    }

    @ModifyVariable(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
            ),
            ordinal = 0
    )
    private float applyDamageReduction(float original, DamageSource damageSource) {
        var player = (Player) (Object) this;
        if (isNotMixinTarget(player))
            return original;

        return dotc$combatManager.reduceDamage(original, damageSource);
    }
}
