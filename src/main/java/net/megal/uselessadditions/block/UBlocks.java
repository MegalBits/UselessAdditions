package net.megal.uselessadditions.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.megal.uselessadditions.UAdd;
import net.minecraft.block.Block;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class UBlocks {
    //Ores
    public static final Block ALLAY_ORE = register(new Identifier(UAdd.MOD_ID, "allay_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ALLAY_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_allay_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID, "axolotl_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_axolotl_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block BAT_ORE = register(new Identifier(UAdd.MOD_ID, "bat_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_BAT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_bat_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block BEE_ORE = register(new Identifier(UAdd.MOD_ID, "bee_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_BEE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_bee_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block BLAZE_ORE = register(new Identifier(UAdd.MOD_ID, "blaze_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_BLAZE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_blaze_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block CAT_ORE = register(new Identifier(UAdd.MOD_ID, "cat_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CAT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cat_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "cave_spider_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cave_spider_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID, "chicken_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_chicken_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block COD_ORE = register(new Identifier(UAdd.MOD_ID, "cod_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_COD_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cod_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block COW_ORE = register(new Identifier(UAdd.MOD_ID, "cow_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_COW_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cow_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block CREEPER_ORE = register(new Identifier(UAdd.MOD_ID, "creeper_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CREEPER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_creeper_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID, "dolphin_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_dolphin_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block DONKEY_ORE = register(new Identifier(UAdd.MOD_ID, "donkey_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_DONKEY_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_donkey_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID, "enderman_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_enderman_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID, "endermite_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_endermite_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block FOX_ORE = register(new Identifier(UAdd.MOD_ID, "fox_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_FOX_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_fox_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block FROG_ORE = register(new Identifier(UAdd.MOD_ID, "frog_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_FROG_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_frog_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block GHAST_ORE = register(new Identifier(UAdd.MOD_ID, "ghast_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GHAST_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_ghast_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "glow_squid_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_glow_squid_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block GOAT_ORE = register(new Identifier(UAdd.MOD_ID, "goat_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GOAT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_goat_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID, "guardian_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_guardian_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "hoglin_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_hoglin_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block HORSE_ORE = register(new Identifier(UAdd.MOD_ID, "horse_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_HORSE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_horse_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block ILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "illager_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_illager_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "iron_golem_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_iron_golem_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block LLAMA_ORE = register(new Identifier(UAdd.MOD_ID, "llama_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_LLAMA_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_llama_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID, "magma_cube_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_magma_cube_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block OCELOT_ORE = register(new Identifier(UAdd.MOD_ID, "ocelot_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_OCELOT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_ocelot_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block PANDA_ORE = register(new Identifier(UAdd.MOD_ID, "panda_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PANDA_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_panda_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block PARROT_ORE = register(new Identifier(UAdd.MOD_ID, "parrot_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PARROT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_parrot_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID, "phantom_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_phantom_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block PIG_ORE = register(new Identifier(UAdd.MOD_ID, "pig_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PIG_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_pig_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "piglin_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_piglin_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID, "polar_bear_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_polar_bear_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "pufferfish_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_pufferfish_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block RABBIT_ORE = register(new Identifier(UAdd.MOD_ID, "rabbit_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_RABBIT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_rabbit_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID, "ravager_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_ravager_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SALMON_ORE = register(new Identifier(UAdd.MOD_ID, "salmon_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SALMON_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_salmon_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SHEEP_ORE = register(new Identifier(UAdd.MOD_ID, "sheep_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SHEEP_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_sheep_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SHULKER_ORE = register(new Identifier(UAdd.MOD_ID, "shulker_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SHULKER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_shulker_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "silverfish_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_silverfish_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "skeleton_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_skeleton_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SLIME_ORE = register(new Identifier(UAdd.MOD_ID, "slime_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SLIME_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_slime_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "snow_golem_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_snow_golem_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "spider_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_spider_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "squid_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_squid_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block STRIDER_ORE = register(new Identifier(UAdd.MOD_ID, "strider_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_STRIDER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_strider_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID, "tropical_fish_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_tropical_fish_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block TURTLE_ORE = register(new Identifier(UAdd.MOD_ID, "turtle_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_TURTLE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_turtle_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block VEX_ORE = register(new Identifier(UAdd.MOD_ID, "vex_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_VEX_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_vex_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "villager_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_villager_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block WARDEN_ORE = register(new Identifier(UAdd.MOD_ID, "warden_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WARDEN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_warden_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "wither_skeleton_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_wither_skeleton_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block WOLF_ORE = register(new Identifier(UAdd.MOD_ID, "wolf_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WOLF_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_wolf_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID, "zombie_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_zombie_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final EnhancementTable ENHANCEMENT_TABLE = register(new Identifier(UAdd.MOD_ID, "enhancement_table"), new EnhancementTable(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).strength(7.5f, 1200.0f).requiresTool().sounds(BlockSoundGroup.STONE).requiresTool()), new FabricItemSettings());
    //Registers blocks as well as a block item
    private static <T extends Block> T register(Identifier id, T block, FabricItemSettings settings) {
        registerBlockItem(id, block, settings);
        return Registry.register(Registries.BLOCK, id, block);
    }
    private static <T extends Block> T register(Identifier id, T block) {
        return Registry.register(Registries.BLOCK, id, block);
    }
    private static <T extends BlockItem> void registerBlockItem(Identifier id, T item) {
        Registry.register(Registries.ITEM, id, item);
    }
    private static void registerBlockItem(Identifier id, Block block, FabricItemSettings settings) {
        Registry.register(Registries.ITEM, id, new BlockItem(block, settings));
    }
    //Some default block types so that I don't have to do as much copy/pasting
    private static Block createOre() {
        return new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).strength(3.0F).requiresTool().sounds(BlockSoundGroup.STONE));
    }
    private static Block createDeepslateOre() {
        return new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).strength(4.5F,3.0F).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    }
    public static void blockLoad() {}
}
