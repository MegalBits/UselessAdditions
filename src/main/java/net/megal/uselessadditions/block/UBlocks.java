package net.megal.uselessadditions.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.UFoodComponents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import static net.minecraft.block.Blocks.TRIPWIRE_HOOK;

public class UBlocks {
    //No items
    public static final PlantFibreWire PLANT_FIBRE_TRIPWIRE = register(new Identifier(UAdd.MOD_ID, "plant_fibre_tripwire"), new PlantFibreWire(TRIPWIRE_HOOK, FabricBlockSettings.create().noCollision().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)));
    public static final UCakeBlock MUD_CAKE = register(new Identifier(UAdd.MOD_ID, "mud_cake"), createCake(UFoodComponents.MUD_CAKE, BlockSoundGroup.MUD, false));
    public static final UCakeBlock CHOCOLATE_CAKE = register(new Identifier(UAdd.MOD_ID, "chocolate_cake"), createCake(UFoodComponents.CHOCOLATE_CAKE, false));
    public static final UCakeBlock RAINBOW_CAKE = register(new Identifier(UAdd.MOD_ID, "rainbow_cake"), createCake(UFoodComponents.RAINBOW_CAKE, false));
    public static final UCakeBlock EXPLOSIVE_CAKE = register(new Identifier(UAdd.MOD_ID, "explosive_cake"), createCake(false));
    public static final BlockEntityType<ExplosiveCakeEntity> EXPLOSIVE_CAKE_ENTITY = registerEntity(new Identifier(UAdd.MOD_ID, "explosive_cake"), FabricBlockEntityTypeBuilder.create(ExplosiveCakeEntity::new, EXPLOSIVE_CAKE).build());
    public static final SlimeCake SLIME_CAKE = register(new Identifier(UAdd.MOD_ID, "slime_cake"), new SlimeCake(FabricBlockSettings.create().solid().strength(0.5F).sounds(BlockSoundGroup.SLIME).pistonBehavior(PistonBehavior.DESTROY), UFoodComponents.SLIME_CAKE, false));
    public static final UCakeBlock SCULK_CAKE = register(new Identifier(UAdd.MOD_ID, "sculk_cake"), createCake(UFoodComponents.SCULK_CAKE, BlockSoundGroup.SCULK, false));
    //Pipes
    public static final Pipe WOODEN_PIPE = register(new Identifier(UAdd.MOD_ID, "wooden_pipe"), new Pipe(FabricBlockSettings.create().strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque().notSolid(), 0.25f), new FabricItemSettings());
    public static final Pipe STONE_PIPE = register(new Identifier(UAdd.MOD_ID, "stone_pipe"), new Pipe(FabricBlockSettings.create().requiresTool().strength(2.5f, 6.0f).sounds(BlockSoundGroup.STONE).nonOpaque().notSolid(), 0.5f), new FabricItemSettings());
    public static final Pipe IRON_PIPE = register(new Identifier(UAdd.MOD_ID, "iron_pipe"), new Pipe(FabricBlockSettings.create().requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque().notSolid(), 1.0f), new FabricItemSettings());
    public static final Pipe GOLD_PIPE = register(new Identifier(UAdd.MOD_ID, "gold_pipe"), new Pipe(FabricBlockSettings.create().requiresTool().strength(3.5F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque().notSolid(), 1.5f), new FabricItemSettings());
    public static final Pipe DIAMOND_PIPE = register(new Identifier(UAdd.MOD_ID, "diamond_pipe"), new Pipe(FabricBlockSettings.create().requiresTool().strength(5.0F, 6.0F).nonOpaque().notSolid(), 2.25f), new FabricItemSettings());
    public static final Pipe NETHERITE_PIPE = register(new Identifier(UAdd.MOD_ID, "netherite_pipe"), new Pipe(FabricBlockSettings.create().requiresTool().strength(20.0f, 1200.0f).sounds(BlockSoundGroup.NETHERITE).nonOpaque().notSolid(), 3.0f), new FabricItemSettings());
    public static final Pipe DRAGON_PIPE = register(new Identifier(UAdd.MOD_ID, "dragon_pipe"), new Pipe(FabricBlockSettings.create().requiresTool().strength(20.0f, 1200.0f).sounds(BlockSoundGroup.STONE).nonOpaque().notSolid(), 4.0f), new FabricItemSettings());
    public static final PipeEngine PIPE_ENGINE = register(new Identifier(UAdd.MOD_ID, "pipe_engine"), new PipeEngine(FabricBlockSettings.create().requiresTool().strength(4.0f, 8.0f).sounds(BlockSoundGroup.COPPER).nonOpaque().notSolid()), new FabricItemSettings());
    public static final BlockEntityType<PipeEntity> PIPE_ENTITY = registerEntity(new Identifier(UAdd.MOD_ID, "pipe"), FabricBlockEntityTypeBuilder.create(PipeEntity::new, WOODEN_PIPE, STONE_PIPE, IRON_PIPE, GOLD_PIPE, DIAMOND_PIPE, NETHERITE_PIPE, DRAGON_PIPE, PIPE_ENGINE).build());
    //Renewable resource stuff
    public static final Sieve SIEVE = register(new Identifier(UAdd.MOD_ID, "sieve"), new Sieve(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(4.0f).sounds(BlockSoundGroup.WOOD).nonOpaque().notSolid()), new FabricItemSettings());
    public static final BlockEntityType<SieveEntity> SIEVE_ENTITY = registerEntity(new Identifier(UAdd.MOD_ID, "sieve"), FabricBlockEntityTypeBuilder.create(SieveEntity::new, UBlocks.SIEVE).build());
    //Enhancement table
    public static final EnhancementTable ENHANCEMENT_TABLE = register(new Identifier(UAdd.MOD_ID, "enhancement_table"), new EnhancementTable(FabricBlockSettings.create().mapColor(MapColor.RED).instrument(Instrument.BASEDRUM).strength(7.5f, 1200.0f).requiresTool().sounds(BlockSoundGroup.STONE).requiresTool()), new FabricItemSettings());
    //Ores
    public static final Block ALLAY_ORE = register(new Identifier(UAdd.MOD_ID, "allay_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ALLAY_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_allay_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_ALLAY_ORE = register(new Identifier(UAdd.MOD_ID, "nether_allay_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_ALLAY_ORE = register(new Identifier(UAdd.MOD_ID, "end_allay_ore"), createEndOre(), new FabricItemSettings());
    public static final Block AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID, "axolotl_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_axolotl_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID, "nether_axolotl_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_AXOLOTL_ORE = register(new Identifier(UAdd.MOD_ID, "end_axolotl_ore"), createEndOre(), new FabricItemSettings());
    public static final Block BAT_ORE = register(new Identifier(UAdd.MOD_ID, "bat_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_BAT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_bat_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_BAT_ORE = register(new Identifier(UAdd.MOD_ID, "nether_bat_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_BAT_ORE = register(new Identifier(UAdd.MOD_ID, "end_bat_ore"), createEndOre(), new FabricItemSettings());
    public static final Block BEE_ORE = register(new Identifier(UAdd.MOD_ID, "bee_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_BEE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_bee_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_BEE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_bee_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_BEE_ORE = register(new Identifier(UAdd.MOD_ID, "end_bee_ore"), createEndOre(), new FabricItemSettings());
    public static final Block BLAZE_ORE = register(new Identifier(UAdd.MOD_ID, "blaze_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_BLAZE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_blaze_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_BLAZE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_blaze_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_BLAZE_ORE = register(new Identifier(UAdd.MOD_ID, "end_blaze_ore"), createEndOre(), new FabricItemSettings());
    public static final Block CAT_ORE = register(new Identifier(UAdd.MOD_ID, "cat_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CAT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cat_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_CAT_ORE = register(new Identifier(UAdd.MOD_ID, "nether_cat_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_CAT_ORE = register(new Identifier(UAdd.MOD_ID, "end_cat_ore"), createEndOre(), new FabricItemSettings());
    public static final Block CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "cave_spider_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cave_spider_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_cave_spider_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_CAVE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "end_cave_spider_ore"), createEndOre(), new FabricItemSettings());
    public static final Block CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID, "chicken_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_chicken_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_chicken_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_CHICKEN_ORE = register(new Identifier(UAdd.MOD_ID, "end_chicken_ore"), createEndOre(), new FabricItemSettings());
    public static final Block COD_ORE = register(new Identifier(UAdd.MOD_ID, "cod_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_COD_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cod_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_COD_ORE = register(new Identifier(UAdd.MOD_ID, "nether_cod_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_COD_ORE = register(new Identifier(UAdd.MOD_ID, "end_cod_ore"), createEndOre(), new FabricItemSettings());
    public static final Block COW_ORE = register(new Identifier(UAdd.MOD_ID, "cow_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_COW_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_cow_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_COW_ORE = register(new Identifier(UAdd.MOD_ID, "nether_cow_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_COW_ORE = register(new Identifier(UAdd.MOD_ID, "end_cow_ore"), createEndOre(), new FabricItemSettings());
    public static final Block CREEPER_ORE = register(new Identifier(UAdd.MOD_ID, "creeper_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_CREEPER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_creeper_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_CREEPER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_creeper_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_CREEPER_ORE = register(new Identifier(UAdd.MOD_ID, "end_creeper_ore"), createEndOre(), new FabricItemSettings());
    public static final Block DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID, "dolphin_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_dolphin_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_dolphin_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_DOLPHIN_ORE = register(new Identifier(UAdd.MOD_ID, "end_dolphin_ore"), createEndOre(), new FabricItemSettings());
    public static final Block DONKEY_ORE = register(new Identifier(UAdd.MOD_ID, "donkey_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_DONKEY_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_donkey_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_DONKEY_ORE = register(new Identifier(UAdd.MOD_ID, "nether_donkey_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_DONKEY_ORE = register(new Identifier(UAdd.MOD_ID, "end_donkey_ore"), createEndOre(), new FabricItemSettings());
    public static final Block ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID, "enderman_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_enderman_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_enderman_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_ENDERMAN_ORE = register(new Identifier(UAdd.MOD_ID, "end_enderman_ore"), createEndOre(), new FabricItemSettings());
    public static final Block ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID, "endermite_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_endermite_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_endermite_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_ENDERMITE_ORE = register(new Identifier(UAdd.MOD_ID, "end_endermite_ore"), createEndOre(), new FabricItemSettings());
    public static final Block EVOKER_ORE = register(new Identifier(UAdd.MOD_ID, "evoker_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_EVOKER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_evoker_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_EVOKER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_evoker_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_EVOKER_ORE = register(new Identifier(UAdd.MOD_ID, "end_evoker_ore"), createEndOre(), new FabricItemSettings());
    public static final Block FOX_ORE = register(new Identifier(UAdd.MOD_ID, "fox_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_FOX_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_fox_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_FOX_ORE = register(new Identifier(UAdd.MOD_ID, "nether_fox_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_FOX_ORE = register(new Identifier(UAdd.MOD_ID, "end_fox_ore"), createEndOre(), new FabricItemSettings());
    public static final Block FROG_ORE = register(new Identifier(UAdd.MOD_ID, "frog_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_FROG_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_frog_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_FROG_ORE = register(new Identifier(UAdd.MOD_ID, "nether_frog_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_FROG_ORE = register(new Identifier(UAdd.MOD_ID, "end_frog_ore"), createEndOre(), new FabricItemSettings());
    public static final Block GHAST_ORE = register(new Identifier(UAdd.MOD_ID, "ghast_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GHAST_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_ghast_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_GHAST_ORE = register(new Identifier(UAdd.MOD_ID, "nether_ghast_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_GHAST_ORE = register(new Identifier(UAdd.MOD_ID, "end_ghast_ore"), createEndOre(), new FabricItemSettings());
    public static final Block GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "glow_squid_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_glow_squid_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "nether_glow_squid_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_GLOW_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "end_glow_squid_ore"), createEndOre(), new FabricItemSettings());
    public static final Block GOAT_ORE = register(new Identifier(UAdd.MOD_ID, "goat_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GOAT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_goat_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_GOAT_ORE = register(new Identifier(UAdd.MOD_ID, "nether_goat_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_GOAT_ORE = register(new Identifier(UAdd.MOD_ID, "end_goat_ore"), createEndOre(), new FabricItemSettings());
    public static final Block GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID, "guardian_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_guardian_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_guardian_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_GUARDIAN_ORE = register(new Identifier(UAdd.MOD_ID, "end_guardian_ore"), createEndOre(), new FabricItemSettings());
    public static final Block HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "hoglin_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_hoglin_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_hoglin_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_HOGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "end_hoglin_ore"), createEndOre(), new FabricItemSettings());
    public static final Block HORSE_ORE = register(new Identifier(UAdd.MOD_ID, "horse_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_HORSE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_horse_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_HORSE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_horse_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_HORSE_ORE = register(new Identifier(UAdd.MOD_ID, "end_horse_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "pillager_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_pillager_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_pillager_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "end_pillager_ore"), createEndOre(), new FabricItemSettings());
    public static final Block IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "iron_golem_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_iron_golem_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "nether_iron_golem_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_IRON_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "end_iron_golem_ore"), createEndOre(), new FabricItemSettings());
    public static final Block LLAMA_ORE = register(new Identifier(UAdd.MOD_ID, "llama_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_LLAMA_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_llama_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_LLAMA_ORE = register(new Identifier(UAdd.MOD_ID, "nether_llama_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_LLAMA_ORE = register(new Identifier(UAdd.MOD_ID, "end_llama_ore"), createEndOre(), new FabricItemSettings());
    public static final Block MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID, "magma_cube_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_magma_cube_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_magma_cube_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_MAGMA_CUBE_ORE = register(new Identifier(UAdd.MOD_ID, "end_magma_cube_ore"), createEndOre(), new FabricItemSettings());
    public static final Block OCELOT_ORE = register(new Identifier(UAdd.MOD_ID, "ocelot_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_OCELOT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_ocelot_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_OCELOT_ORE = register(new Identifier(UAdd.MOD_ID, "nether_ocelot_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_OCELOT_ORE = register(new Identifier(UAdd.MOD_ID, "end_ocelot_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PANDA_ORE = register(new Identifier(UAdd.MOD_ID, "panda_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PANDA_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_panda_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PANDA_ORE = register(new Identifier(UAdd.MOD_ID, "nether_panda_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PANDA_ORE = register(new Identifier(UAdd.MOD_ID, "end_panda_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PARROT_ORE = register(new Identifier(UAdd.MOD_ID, "parrot_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PARROT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_parrot_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PARROT_ORE = register(new Identifier(UAdd.MOD_ID, "nether_parrot_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PARROT_ORE = register(new Identifier(UAdd.MOD_ID, "end_parrot_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID, "phantom_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_phantom_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID, "nether_phantom_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PHANTOM_ORE = register(new Identifier(UAdd.MOD_ID, "end_phantom_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PIG_ORE = register(new Identifier(UAdd.MOD_ID, "pig_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PIG_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_pig_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PIG_ORE = register(new Identifier(UAdd.MOD_ID, "nether_pig_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PIG_ORE = register(new Identifier(UAdd.MOD_ID, "end_pig_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "piglin_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_piglin_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_piglin_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PIGLIN_ORE = register(new Identifier(UAdd.MOD_ID, "end_piglin_ore"), createEndOre(), new FabricItemSettings());
    public static final Block POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID, "polar_bear_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_polar_bear_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID, "nether_polar_bear_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_POLAR_BEAR_ORE = register(new Identifier(UAdd.MOD_ID, "end_polar_bear_ore"), createEndOre(), new FabricItemSettings());
    public static final Block PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "pufferfish_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_pufferfish_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "nether_pufferfish_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_PUFFERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "end_pufferfish_ore"), createEndOre(), new FabricItemSettings());
    public static final Block RABBIT_ORE = register(new Identifier(UAdd.MOD_ID, "rabbit_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_RABBIT_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_rabbit_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_RABBIT_ORE = register(new Identifier(UAdd.MOD_ID, "nether_rabbit_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_RABBIT_ORE = register(new Identifier(UAdd.MOD_ID, "end_rabbit_ore"), createEndOre(), new FabricItemSettings());
    public static final Block RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID, "ravager_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_ravager_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_ravager_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_RAVAGER_ORE = register(new Identifier(UAdd.MOD_ID, "end_ravager_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SALMON_ORE = register(new Identifier(UAdd.MOD_ID, "salmon_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SALMON_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_salmon_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SALMON_ORE = register(new Identifier(UAdd.MOD_ID, "nether_salmon_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SALMON_ORE = register(new Identifier(UAdd.MOD_ID, "end_salmon_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SHEEP_ORE = register(new Identifier(UAdd.MOD_ID, "sheep_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SHEEP_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_sheep_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SHEEP_ORE = register(new Identifier(UAdd.MOD_ID, "nether_sheep_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SHEEP_ORE = register(new Identifier(UAdd.MOD_ID, "end_sheep_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SHULKER_ORE = register(new Identifier(UAdd.MOD_ID, "shulker_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SHULKER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_shulker_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SHULKER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_shulker_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SHULKER_ORE = register(new Identifier(UAdd.MOD_ID, "end_shulker_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "silverfish_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_silverfish_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "nether_silverfish_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SILVERFISH_ORE = register(new Identifier(UAdd.MOD_ID, "end_silverfish_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "skeleton_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_skeleton_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "nether_skeleton_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "end_skeleton_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SLIME_ORE = register(new Identifier(UAdd.MOD_ID, "slime_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SLIME_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_slime_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SLIME_ORE = register(new Identifier(UAdd.MOD_ID, "nether_slime_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SLIME_ORE = register(new Identifier(UAdd.MOD_ID, "end_slime_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "snow_golem_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_snow_golem_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "nether_snow_golem_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SNOW_GOLEM_ORE = register(new Identifier(UAdd.MOD_ID, "end_snow_golem_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "spider_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_spider_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_spider_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SPIDER_ORE = register(new Identifier(UAdd.MOD_ID, "end_spider_ore"), createEndOre(), new FabricItemSettings());
    public static final Block SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "squid_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_squid_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "nether_squid_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_SQUID_ORE = register(new Identifier(UAdd.MOD_ID, "end_squid_ore"), createEndOre(), new FabricItemSettings());
    public static final Block STRIDER_ORE = register(new Identifier(UAdd.MOD_ID, "strider_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_STRIDER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_strider_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_STRIDER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_strider_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_STRIDER_ORE = register(new Identifier(UAdd.MOD_ID, "end_strider_ore"), createEndOre(), new FabricItemSettings());
    public static final Block TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID, "tropical_fish_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_tropical_fish_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID, "nether_tropical_fish_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_TROPICAL_FISH_ORE = register(new Identifier(UAdd.MOD_ID, "end_tropical_fish_ore"), createEndOre(), new FabricItemSettings());
    public static final Block TURTLE_ORE = register(new Identifier(UAdd.MOD_ID, "turtle_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_TURTLE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_turtle_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_TURTLE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_turtle_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_TURTLE_ORE = register(new Identifier(UAdd.MOD_ID, "end_turtle_ore"), createEndOre(), new FabricItemSettings());
    public static final Block VEX_ORE = register(new Identifier(UAdd.MOD_ID, "vex_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_VEX_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_vex_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_VEX_ORE = register(new Identifier(UAdd.MOD_ID, "nether_vex_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_VEX_ORE = register(new Identifier(UAdd.MOD_ID, "end_vex_ore"), createEndOre(), new FabricItemSettings());
    public static final Block VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "villager_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_villager_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "nether_villager_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_VILLAGER_ORE = register(new Identifier(UAdd.MOD_ID, "end_villager_ore"), createEndOre(), new FabricItemSettings());
    public static final Block VINDICATOR_ORE = register(new Identifier(UAdd.MOD_ID, "vindicator_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_VINDICATOR_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_vindicator_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_VINDICATOR_ORE = register(new Identifier(UAdd.MOD_ID, "nether_vindicator_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_VINDICATOR_ORE = register(new Identifier(UAdd.MOD_ID, "end_vindicator_ore"), createEndOre(), new FabricItemSettings());
    public static final Block WARDEN_ORE = register(new Identifier(UAdd.MOD_ID, "warden_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WARDEN_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_warden_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_WARDEN_ORE = register(new Identifier(UAdd.MOD_ID, "nether_warden_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_WARDEN_ORE = register(new Identifier(UAdd.MOD_ID, "end_warden_ore"), createEndOre(), new FabricItemSettings());
    public static final Block WITCH_ORE = register(new Identifier(UAdd.MOD_ID, "witch_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WITCH_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_witch_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_WITCH_ORE = register(new Identifier(UAdd.MOD_ID, "nether_witch_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_WITCH_ORE = register(new Identifier(UAdd.MOD_ID, "end_witch_ore"), createEndOre(), new FabricItemSettings());
    public static final Block WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "wither_skeleton_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_wither_skeleton_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "nether_wither_skeleton_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_WITHER_SKELETON_ORE = register(new Identifier(UAdd.MOD_ID, "end_wither_skeleton_ore"), createEndOre(), new FabricItemSettings());
    public static final Block WOLF_ORE = register(new Identifier(UAdd.MOD_ID, "wolf_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_WOLF_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_wolf_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_WOLF_ORE = register(new Identifier(UAdd.MOD_ID, "nether_wolf_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_WOLF_ORE = register(new Identifier(UAdd.MOD_ID, "end_wolf_ore"), createEndOre(), new FabricItemSettings());
    public static final Block ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID, "zombie_ore"), createOre(), new FabricItemSettings());
    public static final Block DEEPSLATE_ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID, "deepslate_zombie_ore"), createDeepslateOre(), new FabricItemSettings());
    public static final Block NETHER_ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID, "nether_zombie_ore"), createNetherOre(), new FabricItemSettings());
    public static final Block END_ZOMBIE_ORE = register(new Identifier(UAdd.MOD_ID, "end_zombie_ore"), createDeepslateOre(), new FabricItemSettings());
    //Spawners
    public static final Block EMPTY_SPAWNER = register(new Identifier(UAdd.MOD_ID, "empty_spawner"), new Block(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(5.0f).sounds(BlockSoundGroup.METAL).nonOpaque()), new FabricItemSettings());
    public static final SurvivalSpawner SURVIVAL_SPAWNER = register(new Identifier(UAdd.MOD_ID, "survival_spawner"), new SurvivalSpawner(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(5.0f).sounds(BlockSoundGroup.METAL).nonOpaque()));
    private static final SpawnerBlockItem SPAWNER_ITEM = registerBlockItem(new Identifier(UAdd.MOD_ID, "survival_spawner"), new SpawnerBlockItem(SURVIVAL_SPAWNER, new FabricItemSettings()));
    public static final BlockEntityType<SurvivalSpawnerEntity> SURVIVAL_SPAWNER_ENTITY = registerEntity(new Identifier(UAdd.MOD_ID, "survival_spawner"), FabricBlockEntityTypeBuilder.create(SurvivalSpawnerEntity::new, UBlocks.SURVIVAL_SPAWNER).build());
    //Registers blocks as well as a block item
    private static <T extends Block> T register(Identifier id, T block, FabricItemSettings settings) {
        registerBlockItem(id, block, settings);
        return Registry.register(Registries.BLOCK, id, block);
    }
    private static <T extends Block> T register(Identifier id, T block) {
        return Registry.register(Registries.BLOCK, id, block);
    }
    private static <T extends BlockEntityType<?>> T registerEntity(Identifier id, T blockEntity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, blockEntity);
    }
    public static <T extends BlockItem> T registerBlockItem(Identifier id, T item) {
        return Registry.register(Registries.ITEM, id, item);
    }
    private static void registerBlockItem(Identifier id, Block block, FabricItemSettings settings) {
        Registry.register(Registries.ITEM, id, new BlockItem(block, settings));
    }
    //Some default block types so that I don't have to do as much copy/pasting
    private static UCakeBlock createCake(boolean canHaveCandle) {
        return createCake(UFoodComponents.DEFAULT_CAKE, canHaveCandle);
    }
    private static UCakeBlock createCake(FoodComponent foodComponent, boolean canHaveCandle) {
        return createCake(foodComponent, BlockSoundGroup.WOOL, canHaveCandle);
    }
    private static UCakeBlock createCake(FoodComponent foodComponent, BlockSoundGroup sound, boolean canHaveCandle) {
        return new UCakeBlock(FabricBlockSettings.create().solid().strength(0.5F).sounds(sound).pistonBehavior(PistonBehavior.DESTROY), foodComponent, canHaveCandle);
    }
    private static Block createOre() {
        return new Block(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).strength(3.0F).requiresTool().sounds(BlockSoundGroup.STONE));
    }
    private static Block createDeepslateOre() {
        return new Block(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASEDRUM).strength(4.5F,3.0F).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    }
    private static Block createNetherOre() {
        return new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).instrument(Instrument.BASEDRUM).strength(3.0F).requiresTool().sounds(BlockSoundGroup.NETHER_ORE));
    }
    private static Block createEndOre() {
        return new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).strength(3.0F,9.0f).requiresTool().sounds(BlockSoundGroup.STONE));
    }
    public static void blockLoad() {}
}
