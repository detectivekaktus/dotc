package net.detectivekaktus.item.tool;

import net.detectivekaktus.core.rng.PseudoRandomBaseChances;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import net.detectivekaktus.item.DotcItem;

public class Crystalys extends SwordItem implements DotcItem, Critable {
    private static final float CRIT_PERCENT = 1.33f;

    public Crystalys(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        var components = this.generateTooltipTranslationStrings(1, "crystalys");
        tooltip.addAll(components);
    }

    @Override
    public float getCritPercent() {
        return CRIT_PERCENT;
    }
}
