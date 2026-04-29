package net.detectivekaktus.item.tool;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.detectivekaktus.DefenseOfTheCraft;
import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.component.DotcComponents;
import net.detectivekaktus.component.records.ChargeableComponent;
import net.detectivekaktus.core.item.DotcItemRules;
import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;

public class MagicStick extends DotcItem {
    public MagicStick(Properties properties, TooltipBuilder tooltipBuilder) {
        super(properties, tooltipBuilder);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        var stack = player.getItemInHand(interactionHand);

        if (level.isClientSide || !stack.has(DotcComponents.CHARGEABLE_COMPONENT))
            return InteractionResultHolder.pass(stack);

        var component = stack.get(DotcComponents.CHARGEABLE_COMPONENT);

        if (component.charges() == 0)
            return InteractionResultHolder.pass(stack);

        var hpRegen = DotcItemRules.HEALTH_PER_STICK_CHARGE * component.charges();
        var manaRegen = DotcItemRules.MANA_PER_STICK_CHARGE * component.charges();

        var mana = PlayerMana.get(player);
        player.heal(hpRegen);
        mana.increment(manaRegen);

        player.getCooldowns().addCooldown(DotcTools.MAGIC_STICK, 15 * 20);
        player.getCooldowns().addCooldown(DotcTools.MAGIC_WAND, 15 * 20);
        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                DotcItemSounds.MAGIC_STICK,
                SoundSource.PLAYERS
        );

        stack.set(
                DotcComponents.CHARGEABLE_COMPONENT,
                ChargeableComponent.resetCharges(component)
        );

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (level.isClientSide)
            return;

        if (!itemStack.has(DotcComponents.CHARGEABLE_COMPONENT))
            return;

        var component = itemStack.get(DotcComponents.CHARGEABLE_COMPONENT);

        if (Math.abs(level.getGameTime() - component.lastTickSync()) >= 30 * 20) {
            itemStack.set(
                    DotcComponents.CHARGEABLE_COMPONENT,
                    ChargeableComponent.addCharge(component, level)
            );
        }
    }
}
