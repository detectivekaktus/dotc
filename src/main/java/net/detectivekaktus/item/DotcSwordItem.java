package net.detectivekaktus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DotcSwordItem extends SwordItem {
    private final TooltipBuilder tooltipBuilder = new TooltipBuilder();

    public DotcSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public TooltipBuilder tooltip() {
        return tooltipBuilder;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.addAll(tooltipBuilder.build());
    }
}
