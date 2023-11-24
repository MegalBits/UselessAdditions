package net.megal.uselessadditions;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class UWorldgen {
    public static final RegistryKey<PlacedFeature> ALLAY_ORE = register(new Identifier(UAdd.MOD_ID,"allay_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID,"axolotl_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> BAT_ORE = register(new Identifier(UAdd.MOD_ID,"bat_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> BEE_ORE = register(new Identifier(UAdd.MOD_ID,"bee_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> BLAZE_ORE = register(new Identifier(UAdd.MOD_ID,"blaze_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> CAT_ORE = register(new Identifier(UAdd.MOD_ID,"cat_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID,"cave_spider_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID,"chicken_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> COD_ORE = register(new Identifier(UAdd.MOD_ID,"cod_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> COW_ORE = register(new Identifier(UAdd.MOD_ID,"cow_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> CREEPER_ORE = register(new Identifier(UAdd.MOD_ID,"creeper_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID,"dolphin_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> DONKEY_ORE = register(new Identifier(UAdd.MOD_ID,"donkey_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID,"enderman_ore"), BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID,"endermite_ore"), BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> EVOKER_ORE = register(new Identifier(UAdd.MOD_ID,"evoker_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> FOX_ORE = register(new Identifier(UAdd.MOD_ID,"fox_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> FROG_ORE = register(new Identifier(UAdd.MOD_ID,"frog_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> GHAST_ORE = register(new Identifier(UAdd.MOD_ID,"ghast_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID,"glow_squid_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> GOAT_ORE = register(new Identifier(UAdd.MOD_ID,"goat_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID,"guardian_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID,"hoglin_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> HORSE_ORE = register(new Identifier(UAdd.MOD_ID,"horse_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID,"iron_golem_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> LLAMA_ORE = register(new Identifier(UAdd.MOD_ID,"llama_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID,"magma_cube_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> OCELOT_ORE = register(new Identifier(UAdd.MOD_ID,"ocelot_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PANDA_ORE = register(new Identifier(UAdd.MOD_ID,"panda_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PARROT_ORE = register(new Identifier(UAdd.MOD_ID,"parrot_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID,"phantom_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PIG_ORE = register(new Identifier(UAdd.MOD_ID,"pig_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID,"piglin_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PILLAGER_ORE = register(new Identifier(UAdd.MOD_ID,"pillager_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID,"polar_bear_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID,"pufferfish_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> RABBIT_ORE = register(new Identifier(UAdd.MOD_ID,"rabbit_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID,"ravager_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SALMON_ORE = register(new Identifier(UAdd.MOD_ID,"salmon_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SHEEP_ORE = register(new Identifier(UAdd.MOD_ID,"sheep_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SHULKER_ORE = register(new Identifier(UAdd.MOD_ID,"shulker_ore"), BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID,"silverfish_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SKELETON_ORE = register(new Identifier(UAdd.MOD_ID,"skeleton_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SLIME_ORE = register(new Identifier(UAdd.MOD_ID,"slime_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID,"snow_golem_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SPIDER_ORE = register(new Identifier(UAdd.MOD_ID,"spider_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> SQUID_ORE = register(new Identifier(UAdd.MOD_ID,"squid_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> STRIDER_ORE = register(new Identifier(UAdd.MOD_ID,"strider_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID,"tropical_fish_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> TURTLE_ORE = register(new Identifier(UAdd.MOD_ID,"turtle_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> VEX_ORE = register(new Identifier(UAdd.MOD_ID,"vex_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID,"villager_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> VINDICATOR_ORE = register(new Identifier(UAdd.MOD_ID,"vindicator_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> WARDEN_ORE = register(new Identifier(UAdd.MOD_ID,"warden_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID,"wither_skeleton_ore"), BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> WOLF_ORE = register(new Identifier(UAdd.MOD_ID,"wolf_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
    public static final RegistryKey<PlacedFeature> ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID,"zombie_ore"), BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);

    private static RegistryKey<PlacedFeature> register(Identifier id, Predicate<BiomeSelectionContext> selector, GenerationStep.Feature generationStep) {
        RegistryKey<PlacedFeature> feature = RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
        BiomeModifications.addFeature(selector, generationStep, feature);
        return feature;
    }

    public static void wgenLoad() {}
}
