package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.base.UAllItems;
import net.megal.uselessadditions.mixin.ItemRemainder;
import net.minecraft.entity.ItemEntity;
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
        UAdd.LOGGER.info(String.valueOf(stack.getDamage()));
        UAdd.LOGGER.info(String.valueOf(stack.isDamageable()));
        ItemStack alteredStack = new ItemStack(this, stack.getCount());
        alteredStack.setNbt(stack.getNbt());
        alteredStack.setDamage(stack.getDamage()+1);
        return !(alteredStack.getDamage() >= alteredStack.getMaxDamage()) ? alteredStack : ItemStack.EMPTY;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        UAllItems.unstackItems(stack, player);
        super.onCraft(stack, world, player);
    }
}
