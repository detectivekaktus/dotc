package net.detectivekaktus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DotcPickaxeItem extends PickaxeItem {
    private final List<Component> components;

    public DotcPickaxeItem(Tier tier, Properties properties, TooltipBuilder tooltipBuilder) {
        super(tier, properties);
        this.components = tooltipBuilder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.addAll(components);
    }
}
