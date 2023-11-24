package net.megal.uselessadditions.enchantment;

import net.megal.uselessadditions.UAdd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class UEnchantments {
    private static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public static final ObsidianSmashingEnchantment OBSIDIAN_SMASHING = registerEnchantment(new Identifier(UAdd.MOD_ID, "obsidian_smashing"),
            new ObsidianSmashingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final InflamedEnchantment INFLAMED = registerEnchantment(new Identifier(UAdd.MOD_ID, "inflamed"),
            new InflamedEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final StarDurabilityEnchantment STAR_DURABILITY = registerEnchantment(new Identifier(UAdd.MOD_ID, "star_durability"),
            new StarDurabilityEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static final StarDamageEnchantment STAR_DAMAGE = registerEnchantment(new Identifier(UAdd.MOD_ID, "star_damage"),
            new StarDamageEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final StarMiningEnchantment STAR_EFFICIENCY = registerEnchantment(new Identifier(UAdd.MOD_ID, "star_mining"),
            new StarMiningEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final StarProtectionEnchantment STAR_PROTECTION = registerEnchantment(new Identifier(UAdd.MOD_ID, "star_protection"),
            new StarProtectionEnchantment(Enchantment.Rarity.RARE, ALL_ARMOR));
    public static final ParachuteEnchantment PARACHUTE = registerEnchantment(new Identifier(UAdd.MOD_ID, "parachute"),
            new ParachuteEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.HEAD));
    public static final FinnedEnchantment FINNED = registerEnchantment(new Identifier(UAdd.MOD_ID, "finned"),
            new FinnedEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.HEAD));
    public static final LeapingEnchantment LEAPING = registerEnchantment(new Identifier(UAdd.MOD_ID, "leaping"),
            new LeapingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.HEAD));
    public static final TurtleEnchantment TURTLE = registerEnchantment(new Identifier(UAdd.MOD_ID, "turtle"),
            new TurtleEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.HEAD));
    public static final ShatteringEnchantment SHATTERING = registerEnchantment(new Identifier(UAdd.MOD_ID, "shattering"),
            new ShatteringEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final ShelledEnchantment SHELLED = registerEnchantment(new Identifier(UAdd.MOD_ID, "shelled"),
            new ShelledEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.CHEST));
    public static final UndeadEnchantment UNDEAD = registerEnchantment(new Identifier(UAdd.MOD_ID, "undead"),
            new UndeadEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.CHEST));
    public static final AntiCactusEnchantment CACTUS_LINING = registerEnchantment(new Identifier(UAdd.MOD_ID, "cactus_lining"),
            new AntiCactusEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static final DraconicEnchantment DRACONIC = registerEnchantment(new Identifier(UAdd.MOD_ID, "draconic"),
            new DraconicEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final NetheriteEnchantment NETHERITE_PLATED = registerEnchantment(new Identifier(UAdd.MOD_ID, "netherite_plated"),
            new NetheriteEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final EmeraldEnchantment ENCRUSTED = registerEnchantment(new Identifier(UAdd.MOD_ID, "encrusted"),
            new EmeraldEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final DiamondEnchantment BEJEWELED = registerEnchantment(new Identifier(UAdd.MOD_ID, "bejeweled"),
            new DiamondEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final GoldEnchantment GILDED = registerEnchantment(new Identifier(UAdd.MOD_ID, "gilded"),
            new GoldEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final IronEnchantment IRON_PLATED = registerEnchantment(new Identifier(UAdd.MOD_ID, "iron_plated"),
            new IronEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final StunnedEnchantment ELECTRIFIED = registerEnchantment(new Identifier(UAdd.MOD_ID, "electrified"),
            new StunnedEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final AmethystEnchantment CRYSTALLINE = registerEnchantment(new Identifier(UAdd.MOD_ID, "crystalline"),
            new AmethystEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static final SubterraneanEnchantment SUBTERRANEAN = registerEnchantment(new Identifier(UAdd.MOD_ID, "subterranean"),
            new SubterraneanEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final SplinteredEnchantment SPLINTERED = registerEnchantment(new Identifier(UAdd.MOD_ID, "splintered"),
            new SplinteredEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final DirtEnchantment SOILED = registerEnchantment(new Identifier(UAdd.MOD_ID, "soiled"),
            new DirtEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static final RepairingEnchantment REPAIRING = registerEnchantment(new Identifier(UAdd.MOD_ID, "repairing"),
            new RepairingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
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
        return Registry.register(Registries.ENCHANTMENT, id, enchantment);
    }
    public static void enchLoad() {}
}
