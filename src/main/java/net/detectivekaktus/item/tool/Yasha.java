package net.detectivekaktus.item.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import net.detectivekaktus.item.TooltipProvider;

public class Yasha extends SwordItem implements TooltipProvider {
    public static final float MOVE_SPEED_BONUS = 0.125f;

    public Yasha(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getDescriptionComponent("yasha"));
    }
}
