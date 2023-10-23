package net.megal.uselessadditions.item;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class UFoodComponents {
    public static final FoodComponent ALLAY_COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).alwaysEdible().build();
    public static final FoodComponent BAT_WING = new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 165, 0), 0.5f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 0), 0.1f).build();
    public static final FoodComponent RAW_AXOLOTL = new FoodComponent.Builder().hunger(1).saturationModifier(0.15f).build();
    public static final FoodComponent COOKED_AXOLOTL = new FoodComponent.Builder().hunger(3).saturationModifier(0.5f).build();
    public static final FoodComponent DRAGON_FLESH = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0f).alwaysEdible().build();
    public static final FoodComponent LESSER_GOLDEN_CARROT = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent ENCHANTED_GOLDEN_CARROT = new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000, 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 6000, 0), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 1), 1.0f).alwaysEdible().build();
    public static final FoodComponent MAGIC_CARROT = new FoodComponent.Builder().hunger(10).saturationModifier(1.8f).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 9000, 2), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 9000, 0), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 800, 2), 1.0f).alwaysEdible().build();
    public static final FoodComponent LESSER_GOLDEN_APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(0.8f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 0), 1.0f).alwaysEdible().build();
    public static final FoodComponent MAGIC_APPLE = new FoodComponent.Builder().hunger(8).saturationModifier(1.8f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 800, 2), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 9000, 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 9000, 0), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 3600, 3), 1.0f).alwaysEdible().build();
}
