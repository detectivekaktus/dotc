package net.detectivekaktus.item.primitive;

import net.detectivekaktus.item.DotcItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class StaffOfWizardry extends SwordItem implements DotcItem {
    public StaffOfWizardry(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(getTooltipComponent("staff_of_wizardry"));
    }
}
