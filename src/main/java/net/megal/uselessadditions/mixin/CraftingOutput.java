package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(ShapedRecipe.class)
public abstract class CraftingOutput {
    private static final List<Item> naturalMendingItems = new ArrayList<>();
    private static final List<Item> autoSmeltItems = new ArrayList<>();
    static {
        naturalMendingItems.add(UItems.AMETHYST_SWORD);
        naturalMendingItems.add(UItems.AMETHYST_SHOVEL);
        naturalMendingItems.add(UItems.AMETHYST_PICKAXE);
        naturalMendingItems.add(UItems.AMETHYST_AXE);
        naturalMendingItems.add(UItems.AMETHYST_HOE);
        autoSmeltItems.add(UItems.BLAZE_METAL_SWORD);
        autoSmeltItems.add(UItems.BLAZE_METAL_SHOVEL);
        autoSmeltItems.add(UItems.BLAZE_METAL_PICKAXE);
        autoSmeltItems.add(UItems.BLAZE_METAL_AXE);
        autoSmeltItems.add(UItems.BLAZE_METAL_HOE);
    }
    @ModifyReturnValue(at = @At("RETURN"),
            method = "outputFromJson(Lcom/google/gson/JsonObject;)Lnet/minecraft/item/ItemStack;")
    private static ItemStack outputWithEnchantment(ItemStack stack) {

        if (naturalMendingItems.contains(stack.getItem())) {
            stack.addEnchantment(UEnchantments.NATURAL_MENDING, 1);
        }
        if (autoSmeltItems.contains(stack.getItem())) {
            stack.addEnchantment(UEnchantments.AUTO_SMELT, 1);
        }
        return stack;
    }
}