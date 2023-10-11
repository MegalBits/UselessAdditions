package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class UEnchantments {
    private static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final PoisonEnchantment POISONING = registerEnchantment(new Identifier(UAdd.MOD_ID, "poisoning"),
            new PoisonEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final NaturalMendingEnchantment NATURAL_MENDING = registerEnchantment(new Identifier(UAdd.MOD_ID, "natural_mending"),
            new NaturalMendingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static final AutoSmeltEnchantment AUTO_SMELT = registerEnchantment(new Identifier(UAdd.MOD_ID, "auto_smelt"),
            new AutoSmeltEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));


    public static <T extends Enchantment> T registerEnchantment(Identifier id, T enchantment) {
        return  Registry.register(Registries.ENCHANTMENT, id, enchantment);
    }
    public static void enchLoad() {}
}
