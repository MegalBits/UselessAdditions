package net.megal.entity;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.List;

public interface RangedMob {
    default void UAdd$setProjectile(Item item) {}
    default Item UAdd$getProjectile() {
        return Items.ARROW;
    }
}
