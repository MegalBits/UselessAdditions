package net.megal.uselessadditions.item.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class USwordItem extends SwordItem {
    public USwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);
        return UAllItems.modifyDefaultStack(stack);
    }
}
