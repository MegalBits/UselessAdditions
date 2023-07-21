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
    private static List<Item> naturalMendingItems = new ArrayList<Item>();
    static {
        naturalMendingItems.add(UItems.AMETHYST_SWORD);
        naturalMendingItems.add(UItems.AMETHYST_SHOVEL);
        naturalMendingItems.add(UItems.AMETHYST_PICKAXE);
        naturalMendingItems.add(UItems.AMETHYST_AXE);
        naturalMendingItems.add(UItems.AMETHYST_HOE);
    }
    @ModifyReturnValue(at = {@At("RETURN")},
            method = {"outputFromJson(Lcom/google/gson/JsonObject;)Lnet/minecraft/item/ItemStack;"})
    private static ItemStack outputWithEnchantment(ItemStack stack) {

        if (naturalMendingItems.contains(stack.getItem())) {
            stack.addEnchantment(UEnchantments.NATURAL_MENDING, 1);
            return stack;
        }
        else return stack;
    }
}