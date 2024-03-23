package net.megal.item;

import net.minecraft.item.BowItem;
import net.minecraft.item.ToolMaterial;

public class UBowItem extends BowItem {
    public final float speed;
    public final float accuracy;
    public final float useTime;

    public UBowItem(ToolMaterial material, float speed, float accuracy, float useTime, Settings settings) {
        super(settings.maxDamage(material.getDurability()));

        this.speed = speed;
        this.accuracy = accuracy;
        this.useTime = useTime;
    }
}
