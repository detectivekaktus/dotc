package net.detectivekaktus.component.records;

import net.minecraft.world.level.Level;

public record ChargeableComponent(int charges, int maxCharges, long lastTickSync) {
    public static ChargeableComponent addCharge(ChargeableComponent component, Level level) {
        if (component.charges() >= component.maxCharges())
            return component;
        return new ChargeableComponent(
                component.charges() + 1,
                component.maxCharges(),
                level.getGameTime()
        );
    }

    public static ChargeableComponent resetCharges(ChargeableComponent component) {
        return new ChargeableComponent(
                0,
                component.maxCharges(),
                component.lastTickSync()
        );
    }
}
