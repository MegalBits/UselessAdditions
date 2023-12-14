package net.megal.uselessadditions.effect;

import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UStatusEffects {
    public static final StatusEffect SPLINTERS = register(new Identifier(UAdd.MOD_ID, "splinters"), new SplintersEffect(StatusEffectCategory.HARMFUL, 0x67502c));
    public static final StatusEffect STUNNED = register(new Identifier(UAdd.MOD_ID, "stunned"), new AttributeEffect(StatusEffectCategory.HARMFUL, 0xffce00).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "bc78878d-b676-447e-aef8-4c70cb80b92e", -0.95f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

    private static StatusEffect register(Identifier id, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, id, effect);
    }

    public static void effLoad() {}
}
