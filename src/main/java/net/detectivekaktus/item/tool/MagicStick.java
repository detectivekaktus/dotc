package net.detectivekaktus.item.tool;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.core.item.DotcItemRules;
import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;

public class MagicStick extends DotcItem {
    private final int maxCharges;
    private int charges;
    private int chargeTick = 0;

    public MagicStick(Properties properties, TooltipBuilder tooltipBuilder, int maxCharges) {
        super(properties, tooltipBuilder);
        this.maxCharges = maxCharges;
        this.charges = maxCharges;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        var stack = player.getMainHandItem();

        if (level.isClientSide || charges == 0)
            return InteractionResultHolder.pass(stack);

        var hpRegen = DotcItemRules.HEALTH_PER_STICK_CHARGE * charges;
        var manaRegen = DotcItemRules.MANA_PER_STICK_CHARGE * charges;

        var mana = PlayerMana.get(player);
        player.heal(hpRegen);
        mana.increment(manaRegen);

        player.getCooldowns().addCooldown(this, 15 * 20);
        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                DotcItemSounds.MAGIC_STICK,
                SoundSource.PLAYERS
        );
        setCharges(0);

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (chargeTick >= 30 * 20) {
            chargeTick = 0;
            addCharge();
        }
        chargeTick++;
    }

    public int getCharges() {
        return charges;
    }

    public void addCharge() {
        this.addCharges(1);
    }

    public void addCharges(int charges) {
        if (charges < 0)
            return;
        setCharges(getCharges() + charges);
    }

    public void setCharges(int charges) {
        if (charges < 0)
            return;
        this.charges = Math.min(charges, maxCharges);
    }
}
