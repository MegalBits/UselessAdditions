package net.megal.item;

import net.minecraft.item.BowItem;
import net.minecraft.item.ToolMaterial;

public class UBowItem extends BowItem {
    public static final float WoodenBowUseTime = 1.5f;

    public final float speed;
    public final float accuracy;
    public final float useTime;

    public UBowItem(ToolMaterial material, float speed, float accuracy, float useTime, Settings settings) {
        super(settings.maxDamage(material.getDurability()));

        this.speed = speed;
        this.accuracy = accuracy;
        this.useTime = useTime;
    }

    public static float getUseTime(int useTicks, float useTime) {
        float f = (float)useTicks / (useTime * 20f);
        f = (f * f + f * 2.0f) / 3.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }

        return f;
    }
}