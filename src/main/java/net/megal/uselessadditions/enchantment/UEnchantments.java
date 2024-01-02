package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static net.minecraft.enchantment.Enchantment.*;

public class UEnchantments {
    private static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final RepairingEnchantment REPAIRING = registerEnchantment(new Identifier(UAdd.MOD_ID, "repairing"),
            new RepairingEnchantment(Rarity.VERY_RARE, EquipmentSlot.values()));

    public static final AutoSmeltEnchantment AUTO_SMELT = registerEnchantment(new Identifier(UAdd.MOD_ID, "auto_smelt"),
            new AutoSmeltEnchantment(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static final CactusLiningEnchantment CACTUS_LINING = registerEnchantment(new Identifier(UAdd.MOD_ID, "cactus_lining"),
            new CactusLiningEnchantment(Rarity.COMMON, EquipmentSlot.values()));


    public static <T extends Enchantment> T registerEnchantment(Identifier id, T enchantment) {
        return Registry.register(Registries.ENCHANTMENT, id, enchantment);
    }
    public static void enchLoad() {}
}
