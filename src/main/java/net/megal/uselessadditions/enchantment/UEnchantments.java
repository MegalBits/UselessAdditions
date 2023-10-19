package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class UEnchantments {
    private static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final DirtEnchantment SOILED = registerEnchantment(new Identifier(UAdd.MOD_ID, "soiled"),
            new DirtEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final IronEnchantment PLATED = registerEnchantment(new Identifier(UAdd.MOD_ID, "plated"),
            new IronEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final StunnedEnchantment ELECTRIFIED = registerEnchantment(new Identifier(UAdd.MOD_ID, "electrified"),
            new StunnedEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final SubterraneanEnchantment SUBTERRANEAN = registerEnchantment(new Identifier(UAdd.MOD_ID, "subterranean"),
            new SubterraneanEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final SplinteredEnchantment SPLINTERED = registerEnchantment(new Identifier(UAdd.MOD_ID, "splintered"),
            new SplinteredEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final RepairingEnchantment REPAIRING = registerEnchantment(new Identifier(UAdd.MOD_ID, "repairing"),
            new RepairingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final GlowingEnchantment LUMINESCENCE = registerEnchantment(new Identifier(UAdd.MOD_ID, "luminescence"),
            new GlowingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final BlindnessEnchantment BLINDING = registerEnchantment(new Identifier(UAdd.MOD_ID, "blinding"),
            new BlindnessEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final SpeedEnchantment QUICKENING = registerEnchantment(new Identifier(UAdd.MOD_ID, "quickening"),
            new SpeedEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final FireEnchantment BURNING = registerEnchantment(new Identifier(UAdd.MOD_ID, "burning"),
            new FireEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final WaterBreathingEnchantment AQUATIC = registerEnchantment(new Identifier(UAdd.MOD_ID, "aquatic"),
            new WaterBreathingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final SlowfallingEnchantment SLOWED_FALLING = registerEnchantment(new Identifier(UAdd.MOD_ID, "slowed_falling"),
            new SlowfallingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final HungerEnchantment HUNGERING = registerEnchantment(new Identifier(UAdd.MOD_ID, "starving"),
            new HungerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final SlownessEnchantment SLOWING = registerEnchantment(new Identifier(UAdd.MOD_ID, "slowing"),
            new SlownessEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final PoisonEnchantment POISONING = registerEnchantment(new Identifier(UAdd.MOD_ID, "poisoning"),
            new PoisonEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final WitherEnchantment WITHERING = registerEnchantment(new Identifier(UAdd.MOD_ID, "withering"),
            new WitherEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final NaturalMendingEnchantment NATURAL_MENDING = registerEnchantment(new Identifier(UAdd.MOD_ID, "natural_mending"),
            new NaturalMendingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static final AutoSmeltEnchantment AUTO_SMELT = registerEnchantment(new Identifier(UAdd.MOD_ID, "auto_smelt"),
            new AutoSmeltEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));


    public static <T extends Enchantment> T registerEnchantment(Identifier id, T enchantment) {
        return  Registry.register(Registries.ENCHANTMENT, id, enchantment);
    }
    public static void enchLoad() {}
}
