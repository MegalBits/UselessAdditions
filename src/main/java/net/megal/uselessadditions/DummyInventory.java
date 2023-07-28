package net.megal.uselessadditions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class DummyInventory implements Inventory {

    private ItemStack stack;
    public DummyInventory() {}
    public DummyInventory(ItemStack stack) {
        this.stack = stack;
    }
    @Override
    public int size() {
        return stack == null ? 0 : 1;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public ItemStack getStack(int slot) {
        return stack;
    }
    @Override
    public ItemStack removeStack(int slot, int amount) {
        return null;
    }
    @Override
    public ItemStack removeStack(int slot) {
        return null;
    }
    @Override
    public void setStack(int slot, ItemStack stack) {
        this.stack = stack;
    }
    @Override
    public void markDirty() {}
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }
    @Override
    public void clear() {}
}