package net.megal.uselessadditions.item;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class UFoodComponents {
    public static final FoodComponent ALLAY_COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).alwaysEdible().build();
    public static final FoodComponent BAT_WING = new FoodComponent.Builder().hunger(1).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 165, 0), 0.5f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 0), 0.1f).build();
    public static final FoodComponent RAW_ALLAY = new FoodComponent.Builder().hunger(1).saturationModifier(0.15f).build();
}
