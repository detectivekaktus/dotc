package net.detectivekaktus.component.records;

public record ProcableComponent(float baseChance, int scale) {
    public static ProcableComponent increaseScale(ProcableComponent component) {
        return new ProcableComponent(
                component.baseChance(),
                component.scale() + 1
        );
    }

    public static ProcableComponent resetScale(ProcableComponent component) {
        return new ProcableComponent(
                component.baseChance(),
                0
        );
    }
}
