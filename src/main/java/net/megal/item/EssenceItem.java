package net.megal.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

public class EssenceItem extends Item {
    public final EntityType<?> entity;

    public EssenceItem(Settings settings, EntityType<?> entity) {
        super(settings);
        this.entity = entity;
    }
}
