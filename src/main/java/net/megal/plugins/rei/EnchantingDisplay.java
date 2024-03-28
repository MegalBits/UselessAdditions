package net.megal.plugins.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EnchantingDisplay implements Display {
    private static final ItemStack enchantedBook = Items.ENCHANTED_BOOK.getDefaultStack();
    private final Item item;
    private final List<Enchantment> compatibleEnchantments;

    public EnchantingDisplay(Item item, List<Enchantment> compatibleEnchantments) {
        this.item = item;
        this.compatibleEnchantments = compatibleEnchantments;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        List<EntryIngredient> ingredients = new ArrayList<>();
        for (Enchantment e : compatibleEnchantments) {
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
        return List.of(EntryIngredient.of(EntryStacks.of(item)));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return UAddReiClient.ENCHANTING;
    }
}
