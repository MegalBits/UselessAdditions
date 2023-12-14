package net.megal.uselessadditions.effect;

import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SplintersEffect extends StatusEffect {
    protected SplintersEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            if ((double)UAdd.calculateVelocity(entity).get(0) > 2.5d) {
                entity.damage(entity.getDamageSources().magic(), amplifier+1);
            }
        }
    }
}
