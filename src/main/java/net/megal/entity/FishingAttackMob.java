package net.megal.entity;

import net.minecraft.entity.LivingEntity;

public interface FishingAttackMob {
    void throwAt(LivingEntity target, float pullProgress);
}
