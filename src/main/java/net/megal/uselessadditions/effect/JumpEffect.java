package net.megal.uselessadditions.effect;

import net.minecraft.entity.effect.StatusEffectCategory;

public class JumpEffect extends UStatusEffect implements JumpStrengthEffect {
    private final float modifier;
    private final float multiplier;

    protected JumpEffect(StatusEffectCategory category, int color, float modifier, float multiplier) {
        super(category, color);
        this.modifier = modifier;
        this.multiplier = multiplier;
    }

    @Override
    public float getJumpModifier() {
        return modifier;
    }

    @Override
    public float getJumpMultiplier() {
        return multiplier;
    }
}
