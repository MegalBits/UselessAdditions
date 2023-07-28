package net.megal.uselessadditions.item.base;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class UAllItems {
    //Unused for now
    public static List<Text> globalTooltips(ItemStack stack) {
        List<Text> tooltip = new ArrayList<Text>();
        //tooltip.add(Text.translatable("tooltip.uselessadditions.placeholder").formatted(Formatting.GRAY));
        return tooltip;
    }
    //Changes the default stack for items based on conditions
    public static ItemStack modifyDefaultStack(ItemStack stack) {
        if (stack.isIn(UAdd.NATURAL_MENDING)) stack.addEnchantment(UEnchantments.NATURAL_MENDING, 1);
        if (stack.isIn(UAdd.AUTO_SMELT)) stack.addEnchantment(UEnchantments.AUTO_SMELT, 1);
        return stack;
    }

    //Unstacks stacks
    public static ItemStack unstackItems(ItemStack stack, World world, PlayerEntity player) {
        if(stack.getCount() > stack.getMaxCount()) {
            for (int i = 0; i < stack.getCount()-1; i++) {
                if (!world.isClient) {
                    world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), stack));
                }
            }
            stack.setCount(1);
        }
        return stack;
    }
}
