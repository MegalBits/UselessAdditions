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
    public static final StatusEffect STUNNED = register(new Identifier(UAdd.MOD_ID, "stunned"), new UStatusEffect(StatusEffectCategory.HARMFUL, 0xffce00)
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "bc78878d-b676-447e-aef8-4c70cb80b92e", -0.95f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final StatusEffect DRAGONS_CURSE = register(new Identifier(UAdd.MOD_ID, "dragons_curse"), new UStatusEffect(StatusEffectCategory.HARMFUL, 0x8228cf)
            .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, "dffcbc0c-29e3-442c-b064-7b8066a7ec9e", -0.5f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "24db6956-a424-48ff-a15a-3fd21b35e9c8", -1.0f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, "66997bd0-efe4-432e-844c-ddfccac62a1b", -1.0f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final StatusEffect FRACTURED = register(new Identifier(UAdd.MOD_ID, "fractured"), new UStatusEffect(StatusEffectCategory.HARMFUL, 0x6b4389)
            .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, "8247f900-6edd-41ea-92ac-52b99e9c8c54", -0.2f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final StatusEffect BLEEDING = register(new Identifier(UAdd.MOD_ID, "bleeding"), new UStatusEffect(StatusEffectCategory.HARMFUL, 0x9c0000));
    public static final StatusEffect DESTABILIZED = register(new Identifier(UAdd.MOD_ID, "destabilized"), new UStatusEffect(StatusEffectCategory.HARMFUL, 0x844a9b));
    public static final StatusEffect SLIMED = register(new Identifier(UAdd.MOD_ID, "slimed"), new UStatusEffect(StatusEffectCategory.NEUTRAL, 0x7fcd70));
    public static final StatusEffect MUTED = register(new Identifier(UAdd.MOD_ID, "muted"), new UStatusEffect(StatusEffectCategory.BENEFICIAL, 0x062e37));

    private static StatusEffect register(Identifier id, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, id, effect);
    }

    public static void effLoad() {}
}
