package net.megal.uselessadditions.effects;

import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UStatusEffects {
    public static final StatusEffect SPLINTERS = register(new Identifier(UAdd.MOD_ID, "splinters"), new SplintersEffect(StatusEffectCategory.HARMFUL, 0x67502c));

    private static StatusEffect register(Identifier id, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, id, effect);
    }

    public static void effLoad() {}
}
