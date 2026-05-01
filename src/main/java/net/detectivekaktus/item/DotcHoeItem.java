package net.detectivekaktus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

public class DotcHoeItem extends HoeItem {
    private final List<Component> components;

    public DotcHoeItem(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties);
        this.components = tooltipBuilder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.addAll(components);
    }
}
