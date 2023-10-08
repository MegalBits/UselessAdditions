package net.megal.uselessadditions.item;

import net.megal.uselessadditions.item.base.UAllItems;
import net.minecraft.item.ItemStack;

public class DamageableGlintItem extends DamageableItem {

    public DamageableGlintItem(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);
        return UAllItems.modifyDefaultStack(stack);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
