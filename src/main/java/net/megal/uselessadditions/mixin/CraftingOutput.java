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

import static net.megal.uselessadditions.UAdd.autoSmeltItems;
import static net.megal.uselessadditions.UAdd.naturalMendingItems;

@Mixin(ShapedRecipe.class)
public abstract class CraftingOutput {
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