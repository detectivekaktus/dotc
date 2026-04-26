package net.detectivekaktus.item.primitive;

import net.detectivekaktus.item.TooltipProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class OgreAxe extends AxeItem implements TooltipProvider {
    public OgreAxe(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getDescriptionComponent("ogre_axe"));
    }
}
