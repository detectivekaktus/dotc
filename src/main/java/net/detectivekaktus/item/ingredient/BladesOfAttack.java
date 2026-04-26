package net.detectivekaktus.item.ingredient;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import net.detectivekaktus.item.TooltipProvider;

public class BladesOfAttack extends SwordItem implements TooltipProvider {
    public BladesOfAttack(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getDescriptionComponent("blades_of_attack"));
    }
}
