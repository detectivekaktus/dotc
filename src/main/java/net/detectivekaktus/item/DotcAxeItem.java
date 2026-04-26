package net.detectivekaktus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DotcAxeItem extends AxeItem {
    private final List<Component> components;

    public DotcAxeItem(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties);
        this.components = tooltipBuilder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.addAll(components);
    }
}
