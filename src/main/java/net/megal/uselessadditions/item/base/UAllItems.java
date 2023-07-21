package net.megal.uselessadditions.item.base;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class UAllItems {
    //Unused for now
    public static List<Text> globalTooltips(ItemStack defaultItemStack) {
        List<Text> tooltip = new ArrayList<Text>();
        tooltip.add(Text.translatable("tooltip.uselessadditions.placeholder").formatted(Formatting.GRAY));
        return tooltip;
    }
    //Changes the default stack for items based on conditions
    public static ItemStack modifyDefaultStack(ItemStack stack) {
        if (stack.isIn(UAdd.NATURAL_MENDING)) stack.addEnchantment(UEnchantments.NATURAL_MENDING, 1);
        return stack;
    }

    //Unstacks stacks
    public static ItemStack unstackItems(ItemStack stack, PlayerEntity player) {
        if(stack.getCount() > stack.getMaxCount()) {
            ItemStack extraStacks = stack.copy();
            extraStacks.setCount(extraStacks.getCount()-1);
            player.giveItemStack(extraStacks);
            stack.setCount(1);
        }
        return stack;
    }
}
