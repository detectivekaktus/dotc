package net.detectivekaktus.item.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.detectivekaktus.item.DotcItem;

public class Javelin extends SpearItem implements DotcItem {
    public static final float BASE_PROC_CHANCE = PseudoRandomBaseChances.AVG_25;

    public Javelin(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(2, "javelin");
        tooltip.addAll(components);
    }
}
