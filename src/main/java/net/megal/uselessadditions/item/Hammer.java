package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.base.UAllItems;
import net.megal.uselessadditions.mixin.ItemRemainder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.world.World;

public class Hammer extends MiningToolItem {

    public Hammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, UAdd.PICKAXE_SHOVEL_MINEABLE, settings);
        ((ItemRemainder)this).setRecipeRemainder(this);
    }
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack alteredStack = new ItemStack(this, stack.getCount());
        alteredStack.setNbt(stack.getNbt());
        alteredStack.setDamage(stack.getDamage()+1);
        return !(alteredStack.getDamage() >= alteredStack.getMaxDamage()) ? alteredStack : ItemStack.EMPTY;
    }
    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        UAllItems.unstackItems(stack, world, player);
        super.onCraft(stack, world, player);
    }
}
