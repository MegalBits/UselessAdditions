package net.megal.item.modifier;

import net.minecraft.enchantment.Enchantment;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class Modifier {
    protected final List<Enchantment> incompatibleEnchantments;

    public Modifier(@Nullable Enchantment... enchantments) {
        incompatibleEnchantments = Arrays.stream(enchantments).toList();
    }

    public boolean isCompatibleWith(Enchantment enchantment) {
        return !incompatibleEnchantments.contains(enchantment);
    }
}
