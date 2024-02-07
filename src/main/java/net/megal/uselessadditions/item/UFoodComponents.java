package net.megal.uselessadditions.item;

import net.megal.uselessadditions.effect.UStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class UFoodComponents {
    public static final FoodComponent ALLAY_COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).alwaysEdible().build();
    public static final FoodComponent BONE_STEW = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 0), 0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 0), 0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 0), 0.05f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 300, 0), 0.025f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 400, 0), 0.0125f)
            .alwaysEdible().build();
    public static final FoodComponent BAT_WING = new FoodComponent.Builder().hunger(1).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 165, 0), 0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0), 0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0), 0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0), 0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 0), 0.1f)
            .build();
    public static final FoodComponent BAT_WING_STEW = new FoodComponent.Builder().hunger(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0), 0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0), 0.05f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0), 0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), 0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0), 0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 200, 0), 0.1f)
            .alwaysEdible().build();
    public static final FoodComponent FROG_LEG = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent FROG_LEG_STEW = new FoodComponent.Builder().hunger(8).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 1), 0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 300, 1), 0.2f)
            .alwaysEdible().build();
    public static final FoodComponent DRAGON_FLESH = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent DRACONIC_STEW = new FoodComponent.Builder().hunger(28).saturationModifier(1.85f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400, 4), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 0), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent MAGIC_APPLE = new FoodComponent.Builder().hunger(8).saturationModifier(1.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 800, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 9000, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 9000, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 3600, 3), 1.0f)
            .alwaysEdible().build();
    // Cakes
    public static final FoodComponent DEFAULT_CAKE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent MUD_CAKE = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.2f)
            .build();
    public static final FoodComponent CHOCOLATE_CAKE = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent RAINBOW_CAKE = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent SLIME_CAKE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(UStatusEffects.SLIMED, 300, 0), 1.0f)
            .build();
    public static final FoodComponent SCULK_CAKE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(UStatusEffects.MUTED, 600, 0), 1.0f)
            .build();
}
