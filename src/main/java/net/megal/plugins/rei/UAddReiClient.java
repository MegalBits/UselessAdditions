package net.megal.plugins.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.client.categories.DefaultSmithingCategory;
import net.megal.UAdd;
import net.megal.item.modifier.Modifiers;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class UAddReiClient implements REIClientPlugin {
    public static final CategoryIdentifier<EnchantmentIncompatibilityDisplay> ENCHANTMENT_INCOMPATIBILITY = CategoryIdentifier.of(UAdd.ID, "enchantment_incompatibility");
    public static final CategoryIdentifier<EnchantingDisplay> ENCHANTING = CategoryIdentifier.of(UAdd.ID, "enchanting");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new EnchantmentIncompatibilityCategory());
        registry.add(new EnchantingCategory());
        registry.addWorkstations(ENCHANTING, EntryStacks.of(Blocks.ENCHANTING_TABLE), EntryStacks.of(Blocks.ANVIL));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        for (Enchantment enchantment : Registries.ENCHANTMENT) {
            List<Enchantment> incompatibleEnchantments = new ArrayList<>();
            for (Enchantment e : Registries.ENCHANTMENT) {
                if (e.equals(enchantment)) continue;
                if (!enchantment.canCombine(e)) incompatibleEnchantments.add(e);
            }

            if (!incompatibleEnchantments.isEmpty()) registry.add(new EnchantmentIncompatibilityDisplay(enchantment, incompatibleEnchantments));
        }

        for (Item item : Registries.ITEM) {
            ItemStack stack = item.getDefaultStack();

            List<Enchantment> compatibleEnchantments = new ArrayList<>();
            for (Enchantment enchantment : Registries.ENCHANTMENT) {
                boolean isIncompatible = !enchantment.isAcceptableItem(stack);
                for (String m : Modifiers.getModifiersFromStack(stack)) {
                    isIncompatible = isIncompatible || !Modifiers.getModifier(m).isCompatibleWith(enchantment);
                    if (isIncompatible) break;
                }

                if (!isIncompatible) compatibleEnchantments.add(enchantment);
            }

            if (!compatibleEnchantments.isEmpty()) registry.add(new EnchantingDisplay(item, compatibleEnchantments));
        }
    }
}
