package net.megal.uselessadditions.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class UFoodComponents {
    public static final FoodComponent ALLAY_COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).alwaysEdible().build();
    public static final FoodComponent BAT_WING = new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 165, 0), 0.3f).build();
    public static final FoodComponent GOLDEN_HONEY_BOTTLE = new FoodComponent.Builder().hunger(8).saturationModifier(2.1f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 170, 2), 1.0f).alwaysEdible().build();
    public static final FoodComponent GOLDEN_TROPICAL_FISH = new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).statusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 500, 0), 1.0f).alwaysEdible().build();
}
