package net.megal.uselessadditions.effects;

import net.megal.uselessadditions.UAdd;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.UUID;

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
