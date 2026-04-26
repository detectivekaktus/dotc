package net.detectivekaktus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DotcItem extends Item {
    private final TooltipBuilder tooltipBuilder;

    public DotcItem(Properties properties, TooltipBuilder tooltipBuilder) {
        super(properties);
        this.tooltipBuilder = tooltipBuilder;
    }

    public TooltipBuilder tooltipBuilder() {
        return tooltipBuilder;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.addAll(tooltipBuilder.build());
    }
}
