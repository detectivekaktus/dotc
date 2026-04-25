package net.detectivekaktus.item.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Optional;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcItem;

public class Crystalys extends SwordItem implements DotcItem, Critable {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_20;
    private static final float CRIT_PERCENT = 1.25f;

    public Crystalys(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getTooltipComponent("crystalys"));
    }

    @Override
    public float getCritPercent() {
        return CRIT_PERCENT;
    }

    @Override
    public Optional<SoundEvent> getProcSound() {
        return Optional.empty();
    }
}
