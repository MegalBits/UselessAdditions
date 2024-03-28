package net.megal.plugins.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.megal.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentIncompatibilityDisplay implements Display {
    private static final ItemStack enchantedBook = Items.ENCHANTED_BOOK.getDefaultStack();
    private final Enchantment enchantment;
    private final List<Enchantment> incompatibleEnchantments;

    public EnchantmentIncompatibilityDisplay(Enchantment enchantment, List<Enchantment> incompatibleEnchantments) {
        this.enchantment = enchantment;
        this.incompatibleEnchantments = incompatibleEnchantments;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public List<Enchantment> getIncompatibilities() {
        return incompatibleEnchantments;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        List<EntryIngredient> ingredients = new ArrayList<>();
        for (Enchantment e : incompatibleEnchantments) {
            List<EntryStack> stacks = new ArrayList<>();
            for (int i = 0; i < e.getMaxLevel(); i++) {
                ItemStack book = enchantedBook.copy();
                EnchantedBookItem.addEnchantment(book, new EnchantmentLevelEntry(e, i + 1));
                stacks.add(EntryStacks.of(book));
            }
            ingredients.add(EntryIngredient.of(stacks.toArray(new EntryStack[0])));
        }
        return ingredients;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        List<EntryStack> stacks = new ArrayList<>();
        for (int i = 0; i < enchantment.getMaxLevel(); i++) {
            ItemStack book = enchantedBook.copy();
            EnchantedBookItem.addEnchantment(book, new EnchantmentLevelEntry(enchantment, i + 1));
            stacks.add(EntryStacks.of(book));
        }

        return List.of(EntryIngredient.of(stacks.toArray(new EntryStack[0])));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return UAddReiClient.ENCHANTMENT_INCOMPATIBILITY;
    }
}
