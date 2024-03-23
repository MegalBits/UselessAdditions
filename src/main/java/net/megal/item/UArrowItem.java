package net.megal.item;

import net.minecraft.item.ArrowItem;

import java.util.List;

public class UArrowItem extends ArrowItem implements HasProjectileStats {
    private final float projectileDamage;
    private final float projectileVelocity;

    public UArrowItem(float damage, float velocity, Settings settings) {
        super(settings);
        this.projectileDamage = damage;
        this.projectileVelocity = velocity;
    }

    public UArrowItem(Settings settings) {
        this(0, 1, settings);
    }

    @Override
    public float getDamage() {
        return projectileDamage;
    }

    @Override
    public float getVelocityMultiplier() {
        return projectileVelocity;//projectileVelocity;
    }
}
