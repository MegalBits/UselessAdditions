package net.megal.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.network.UNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;

public interface HasSecondaryStack {
    ItemStack getSecondaryStack();

    void setSecondaryStack(ItemStack stack);

    void swapStack();

    static <T extends MobEntity & HasSecondaryStack> void swapStack(T entity) {
        ItemStack primaryStack = entity.getEquippedStack(EquipmentSlot.MAINHAND);
        entity.equipStack(EquipmentSlot.MAINHAND, entity.getSecondaryStack());
        entity.setSecondaryStack(primaryStack);

        UNetworking.syncSecondaryStack(entity);
    }
}
