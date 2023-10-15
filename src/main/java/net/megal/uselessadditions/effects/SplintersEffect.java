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
    HashMap<UUID, Vec3d> playerLastPos = new HashMap<>();
    HashMap<UUID, Vec3d> mobLastPos = new HashMap<>();
    Vec3d defaultPos = Vec3d.ZERO;
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
            UUID uuid = entity.getUuid();
            Vec3d pos = entity.getPos();
            float maxSpeed = 3.5f;
            double vel = 0;
            if (entity instanceof PlayerEntity) {
                if (pos != playerLastPos.getOrDefault(uuid, defaultPos)) {
                    vel = pos.distanceTo(playerLastPos.getOrDefault(uuid, defaultPos))*20;
                    playerLastPos.put(uuid, pos);
                }
            } else {
                if (pos != mobLastPos.getOrDefault(uuid, defaultPos)) {
                    vel = pos.distanceTo(mobLastPos.getOrDefault(uuid, defaultPos))*20;
                    mobLastPos.put(uuid, pos);
                }
            }
            if (vel > maxSpeed) {
                entity.damage(entity.getDamageSources().magic(), Math.max(amplifier+1 * ((float)vel-maxSpeed),1));
            }
        }
    }
}
