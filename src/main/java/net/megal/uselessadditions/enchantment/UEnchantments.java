package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UEnchantments {
    public static final Enchantment NATURAL_MENDING = Registry.register(Registries.ENCHANTMENT, new Identifier(UAdd.MOD_ID, "natural_mending"),
            new NaturalMendingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));

    public static void enchLoad() {};
}
