package net.megal.uselessadditions.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;

public class TooltipGlintItem extends TooltipItem {
    public TooltipGlintItem(Settings settings, Formatting formatting) {
        super(settings, formatting);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
