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

    public static final DestabilizingEnchantment DESTABILIZING = registerEnchantment(new Identifier(UAdd.MOD_ID, "destabilizing"),
            new DestabilizingEnchantment(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static final FireThornsEnchantment FIRE_THORNS = registerEnchantment(new Identifier(UAdd.MOD_ID, "fire_thorns"),
            new FireThornsEnchantment(Rarity.VERY_RARE, ALL_ARMOR));

    public static final LifeLeechEnchantment LIFE_LEECH = registerEnchantment(new Identifier(UAdd.MOD_ID, "life_leech"),
            new LifeLeechEnchantment(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static final EverlastingEnchantment EVERLASTING = registerEnchantment(new Identifier(UAdd.MOD_ID, "everlasting"),
            new EverlastingEnchantment(Rarity.VERY_RARE, EquipmentSlot.values()));

    public static final SoulBoundEnchantment SOUL_BOUND = registerEnchantment(new Identifier(UAdd.MOD_ID, "soul_bound"),
            new SoulBoundEnchantment(Rarity.VERY_RARE, EquipmentSlot.values()));

    public static final TelekinesisEnchantment TELEKINESIS = registerEnchantment(new Identifier(UAdd.MOD_ID, "telekinesis"),
            new TelekinesisEnchantment(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static final RepairingEnchantment REPAIRING = registerEnchantment(new Identifier(UAdd.MOD_ID, "repairing"),
            new RepairingEnchantment(Rarity.VERY_RARE, EquipmentSlot.values()));

    public static final AutoSmeltEnchantment AUTO_SMELT = registerEnchantment(new Identifier(UAdd.MOD_ID, "auto_smelt"),
            new AutoSmeltEnchantment(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    /*
    public static final CactusLiningEnchantment CACTUS_LINING = registerEnchantment(new Identifier(UAdd.MOD_ID, "cactus_lining"),
            new CactusLiningEnchantment(Rarity.VERY_RARE));


    public static final ObsidianPaddingEnchantment OBSIDIAN_PADDING = registerEnchantment(new Identifier(UAdd.MOD_ID, "obsidian_padding"),
            new ObsidianPaddingEnchantment(Rarity.VERY_RARE));

     */

    public static final HookingEnchantment HOOKING = registerEnchantment(new Identifier(UAdd.MOD_ID, "hooking"),
            new HookingEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND));



    public static <T extends Enchantment> T registerEnchantment(Identifier id, T enchantment) {
        return Registry.register(Registries.ENCHANTMENT, id, enchantment);
    }
    public static void enchLoad() {}
}
