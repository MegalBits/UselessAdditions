package net.megal.uselessadditions.item;

import net.megal.uselessadditions.mixin.ItemRemainder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DamageableItem extends Item {

    public DamageableItem(Settings settings) {
        super(settings);
        ((ItemRemainder)this).setRecipeRemainder(this);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        stack.setDamage(stack.getDamage()+1);
        return super.getRecipeRemainder(stack);
    }
}
