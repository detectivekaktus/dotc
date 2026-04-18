package net.detectivekaktus.item.tool;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Optional;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.damage.DotcDamageTypes;
import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.sound.item.DotcItemSounds;

public class MonkeyKingBar extends SpearItem implements DotcItem, HasBonusDamage {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_50;
    private static final float BONUS_DAMAGE = 4.0f;

    public MonkeyKingBar(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(1, "monkey_king_bar");
        tooltip.addAll(components);
    }

    @Override
    public float getBonusDamage() {
        return BONUS_DAMAGE;
    }

    @Override
    public DamageSource getBonusDamageSource(Player player) {
        return new DamageSource(
                player.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DotcDamageTypes.MAGICAL)
        );
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.of(DotcItemSounds.MKB_PIERCE);
    }
}
