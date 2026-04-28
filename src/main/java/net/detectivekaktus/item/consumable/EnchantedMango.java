package net.detectivekaktus.item.consumable;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.detectivekaktus.attach.PlayerMana;
import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.item.TooltipBuilder;
import net.detectivekaktus.sound.item.DotcItemSounds;

public class EnchantedMango extends DotcItem {
    public EnchantedMango(Properties properties, TooltipBuilder tooltipBuilder) {
        super(properties, tooltipBuilder);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        var superItemStack = super.finishUsingItem(itemStack, level, livingEntity);

        if (level.isClientSide || !(livingEntity instanceof Player))
            return superItemStack;

        // There's a small desync WARNING that gets evoked once mango is
        // eaten because either client or server don't know what entity
        // is. I guess it's harmless so I'll ignore it
        var mana = PlayerMana.get(livingEntity);
        mana.increment(40);
        level.playSound(
                null,
                livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
                DotcItemSounds.ENCHANTED_MANGO,
                SoundSource.PLAYERS
        );

        return superItemStack;
    }
}
