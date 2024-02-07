package net.megal.uselessadditions.item;

import net.megal.uselessadditions.item.base.UItemHelper;
import net.megal.uselessadditions.mixin.ItemRemainder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DamageableItem extends Item {

    public DamageableItem(Settings settings) {
        super(settings);
        ((ItemRemainder)this).setRecipeRemainder(this);
    }
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack alteredStack = new ItemStack(this, stack.getCount());
        alteredStack.setNbt(stack.getNbt());
        alteredStack.setDamage(stack.getDamage()+1);
        return !(alteredStack.getDamage() >= alteredStack.getMaxDamage()) ? alteredStack : ItemStack.EMPTY;
    }
}
