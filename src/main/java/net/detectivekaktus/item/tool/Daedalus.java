package net.detectivekaktus.item.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcItem;
import net.detectivekaktus.sound.item.DotcItemSounds;

import java.util.List;
import java.util.Optional;

public class Daedalus extends PickaxeItem implements DotcItem, Critable {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_20;
    private static final float CRIT_PERCENT = 1.5f;

    public Daedalus(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(2, "daedalus");
        tooltip.addAll(components);
    }

    @Override
    public float getCritPercent() {
        return CRIT_PERCENT;
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.of(DotcItemSounds.DAEDALUS_CRIT);
    }
}
