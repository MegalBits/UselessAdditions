package net.megal.uselessadditions.item;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.base.UAllItems;
import net.megal.uselessadditions.mixin.ItemRemainder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class EnchantedHammer extends Hammer {

    public EnchantedHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
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
