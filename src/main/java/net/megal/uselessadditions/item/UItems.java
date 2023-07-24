package net.megal.uselessadditions.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.base.*;
import net.megal.uselessadditions.item.bundles.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.megal.uselessadditions.block.UBlocks.*;

public class UItems {
    //Mob Ore Shards
    public static final Item ALLAY_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "allay_shard"),
                    createItem());
    public static final Item AXOLOTL_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "axolotl_shard"),
                    createItem());
    public static final Item BAT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "bat_shard"),
                    createItem());
    public static final Item BEE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "bee_shard"),
                    createItem());
    public static final Item BLAZE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "blaze_shard"),
                    createItem());
    public static final Item CAT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cat_shard"),
                    createItem());
    public static final Item CAVE_SPIDER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cave_spider_shard"),
                    createItem());
    public static final Item CHICKEN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "chicken_shard"),
                    createItem());
    public static final Item COD_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cod_shard"),
                    createItem());
    public static final Item COW_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cow_shard"),
                    createItem());
    public static final Item CREEPER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "creeper_shard"),
                    createItem());
    public static final Item DOLPHIN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "dolphin_shard"),
                    createItem());
    public static final Item DONKEY_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "donkey_shard"),
                    createItem());
    public static final Item ENDERMAN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "enderman_shard"),
                    createItem());
    public static final Item ENDERMITE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "endermite_shard"),
                    createItem());
    public static final Item FOX_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "fox_shard"),
                    createItem());
    public static final Item FROG_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "frog_shard"),
                    createItem());
    public static final Item GHAST_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ghast_shard"),
                    createItem());
    public static final Item GLOW_SQUID_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "glow_squid_shard"),
                    createItem());
    public static final Item GOAT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "goat_shard"),
                    createItem());
    public static final Item GUARDIAN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "guardian_shard"),
                    createItem());
    public static final Item HOGLIN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "hoglin_shard"),
                    createItem());
    public static final Item HORSE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "horse_shard"),
                    createItem());
    public static final Item ILLAGER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "illager_shard"),
                    createItem());
    public static final Item IRON_GOLEM_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "iron_golem_shard"),
                    createItem());
    public static final Item LLAMA_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "llama_shard"),
                    createItem());
    public static final Item MAGMA_CUBE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "magma_cube_shard"),
                    createItem());
    public static final Item OCELOT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ocelot_shard"),
                    createItem());
    public static final Item PANDA_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "panda_shard"),
                    createItem());
    public static final Item PARROT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "parrot_shard"),
                    createItem());
    public static final Item PHANTOM_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "phantom_shard"),
                    createItem());
    public static final Item PIG_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "pig_shard"),
                    createItem());
    public static final Item PIGLIN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "piglin_shard"),
                    createItem());
    public static final Item POLAR_BEAR_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "polar_bear_shard"),
                    createItem());
    public static final Item PUFFERFISH_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "pufferfish_shard"),
                    createItem());
    public static final Item RABBIT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "rabbit_shard"),
                    createItem());
    public static final Item RAVAGER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ravager_shard"),
                    createItem());
    public static final Item SALMON_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "salmon_shard"),
                    createItem());
    public static final Item SHEEP_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "sheep_shard"),
                    createItem());
    public static final Item SHULKER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "shulker_shard"),
                    createItem());
    public static final Item SILVERFISH_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "silverfish_shard"),
                    createItem());
    public static final Item SKELETON_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "skeleton_shard"),
                    createItem());
    public static final Item SLIME_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "slime_shard"),
                    createItem());
    public static final Item SNOW_GOLEM_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "snow_golem_shard"),
                    createItem());
    public static final Item SPIDER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "spider_shard"),
                    createItem());
    public static final Item SQUID_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "squid_shard"),
                    createItem());
    public static final Item STRIDER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "strider_shard"),
                    createItem());
    public static final Item TROPICAL_FISH_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "tropical_fish_shard"),
                    createItem());
    public static final Item TURTLE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "turtle_shard"),
                    createItem());
    public static final Item VEX_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "vex_shard"),
                    createItem());
    public static final Item VILLAGER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "villager_shard"),
                    createItem());
    public static final Item WARDEN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "warden_shard"),
                    createItem());
    public static final Item WITHER_SKELETON_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "wither_skeleton_shard"),
                    createItem());
    public static final Item WOLF_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "wolf_shard"),
                    createItem());
    public static final Item ZOMBIE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "zombie_shard"),
                    createItem());
    public static final Item SMALL_ALLAY_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_allay_shard"),
                    createItem());
    public static final Item SMALL_AXOLOTL_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_axolotl_shard"),
                    createItem());
    public static final Item SMALL_BAT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_bat_shard"),
                    createItem());
    public static final Item SMALL_BEE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_bee_shard"),
                    createItem());
    public static final Item SMALL_BLAZE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_blaze_shard"),
                    createItem());
    public static final Item SMALL_CAT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_cat_shard"),
                    createItem());
    public static final Item SMALL_CAVE_SPIDER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_cave_spider_shard"),
                    createItem());
    public static final Item SMALL_CHICKEN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_chicken_shard"),
                    createItem());
    public static final Item SMALL_COD_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_cod_shard"),
                    createItem());
    public static final Item SMALL_COW_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_cow_shard"),
                    createItem());
    public static final Item SMALL_CREEPER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_creeper_shard"),
                    createItem());
    public static final Item SMALL_DOLPHIN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_dolphin_shard"),
                    createItem());
    public static final Item SMALL_DONKEY_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_donkey_shard"),
                    createItem());
    public static final Item SMALL_ENDERMAN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_enderman_shard"),
                    createItem());
    public static final Item SMALL_ENDERMITE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_endermite_shard"),
                    createItem());
    public static final Item SMALL_FOX_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_fox_shard"),
                    createItem());
    public static final Item SMALL_FROG_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_frog_shard"),
                    createItem());
    public static final Item SMALL_GHAST_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_ghast_shard"),
                    createItem());
    public static final Item SMALL_GLOW_SQUID_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_glow_squid_shard"),
                    createItem());
    public static final Item SMALL_GOAT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_goat_shard"),
                    createItem());
    public static final Item SMALL_GUARDIAN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_guardian_shard"),
                    createItem());
    public static final Item SMALL_HOGLIN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_hoglin_shard"),
                    createItem());
    public static final Item SMALL_HORSE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_horse_shard"),
                    createItem());
    public static final Item SMALL_ILLAGER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_illager_shard"),
                    createItem());
    public static final Item SMALL_IRON_GOLEM_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_iron_golem_shard"),
                    createItem());
    public static final Item SMALL_LLAMA_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_llama_shard"),
                    createItem());
    public static final Item SMALL_MAGMA_CUBE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_magma_cube_shard"),
                    createItem());
    public static final Item SMALL_OCELOT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_ocelot_shard"),
                    createItem());
    public static final Item SMALL_PANDA_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_panda_shard"),
                    createItem());
    public static final Item SMALL_PARROT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_parrot_shard"),
                    createItem());
    public static final Item SMALL_PHANTOM_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_phantom_shard"),
                    createItem());
    public static final Item SMALL_PIG_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_pig_shard"),
                    createItem());
    public static final Item SMALL_PIGLIN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_piglin_shard"),
                    createItem());
    public static final Item SMALL_POLAR_BEAR_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_polar_bear_shard"),
                    createItem());
    public static final Item SMALL_PUFFERFISH_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_pufferfish_shard"),
                    createItem());
    public static final Item SMALL_RABBIT_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_rabbit_shard"),
                    createItem());
    public static final Item SMALL_RAVAGER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_ravager_shard"),
                    createItem());
    public static final Item SMALL_SALMON_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_salmon_shard"),
                    createItem());
    public static final Item SMALL_SHEEP_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_sheep_shard"),
                    createItem());
    public static final Item SMALL_SHULKER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_shulker_shard"),
                    createItem());
    public static final Item SMALL_SILVERFISH_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_silverfish_shard"),
                    createItem());
    public static final Item SMALL_SKELETON_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_skeleton_shard"),
                    createItem());
    public static final Item SMALL_SLIME_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_slime_shard"),
                    createItem());
    public static final Item SMALL_SNOW_GOLEM_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_snow_golem_shard"),
                    createItem());
    public static final Item SMALL_SPIDER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_spider_shard"),
                    createItem());
    public static final Item SMALL_SQUID_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_squid_shard"),
                    createItem());
    public static final Item SMALL_STRIDER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_strider_shard"),
                    createItem());
    public static final Item SMALL_TROPICAL_FISH_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_tropical_fish_shard"),
                    createItem());
    public static final Item SMALL_TURTLE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_turtle_shard"),
                    createItem());
    public static final Item SMALL_VEX_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_vex_shard"),
                    createItem());
    public static final Item SMALL_VILLAGER_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_villager_shard"),
                    createItem());
    public static final Item SMALL_WARDEN_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_warden_shard"),
                    createItem());
    public static final Item SMALL_WITHER_SKELETON_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_wither_skeleton_shard"),
                    createItem());
    public static final Item SMALL_WOLF_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_wolf_shard"),
                    createItem());
    public static final Item SMALL_ZOMBIE_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "small_zombie_shard"),
                    createItem());
    //Spawn Eggs
    public static final SpawnEgg ALLAY_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "allay_spawn_egg"),
                    createSpawnEgg(EntityType.ALLAY));
    public static final SpawnEgg AXOLOTL_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "axolotl_spawn_egg"),
                    createSpawnEgg(EntityType.AXOLOTL));
    public static final SpawnEgg BAT_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "bat_spawn_egg"),
                    createSpawnEgg(EntityType.BAT));
    public static final SpawnEgg BEE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "bee_spawn_egg"),
                    createSpawnEgg(EntityType.BEE));
    public static final SpawnEgg BLAZE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "blaze_spawn_egg"),
                    createSpawnEgg(EntityType.BLAZE));
    public static final SpawnEgg CAT_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cat_spawn_egg"),
                    createSpawnEgg(EntityType.CAT));
    public static final SpawnEgg CAVE_SPIDER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cave_spider_spawn_egg"),
                    createSpawnEgg(EntityType.CAVE_SPIDER));
    public static final SpawnEgg CHICKEN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "chicken_spawn_egg"),
                    createSpawnEgg(EntityType.CHICKEN));
    public static final SpawnEgg COD_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cod_spawn_egg"),
                    createSpawnEgg(EntityType.COD));
    public static final SpawnEgg COW_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "cow_spawn_egg"),
                    createSpawnEgg(EntityType.COW));
    public static final SpawnEgg CREEPER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "creeper_spawn_egg"),
                    createSpawnEgg(EntityType.CREEPER));
    public static final SpawnEgg DROWNED_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "drowned_spawn_egg"),
                    createSpawnEgg(EntityType.DROWNED));
    public static final SpawnEgg DOLPHIN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "dolphin_spawn_egg"),
                    createSpawnEgg(EntityType.DOLPHIN));
    public static final SpawnEgg DONKEY_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "donkey_spawn_egg"),
                    createSpawnEgg(EntityType.DONKEY));
    public static final SpawnEgg ELDER_GUARDIAN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "elder_guardian_spawn_egg"),
                    createSpawnEgg(EntityType.ELDER_GUARDIAN));
    public static final SpawnEgg ENDERMAN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "enderman_spawn_egg"),
                    createSpawnEgg(EntityType.ENDERMAN));
    public static final SpawnEgg ENDERMITE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "endermite_spawn_egg"),
                    createSpawnEgg(EntityType.ENDERMITE));
    public static final SpawnEgg EVOKER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "evoker_spawn_egg"),
                    createSpawnEgg(EntityType.EVOKER));
    public static final SpawnEgg FOX_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "fox_spawn_egg"),
                    createSpawnEgg(EntityType.FOX));
    public static final SpawnEgg FROG_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "frog_spawn_egg"),
                    createSpawnEgg(EntityType.FROG));
    public static final SpawnEgg GHAST_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ghast_spawn_egg"),
                    createSpawnEgg(EntityType.GHAST));
    public static final SpawnEgg GLOW_SQUID_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "glow_squid_spawn_egg"),
                    createSpawnEgg(EntityType.GLOW_SQUID));
    public static final SpawnEgg GOAT_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "goat_spawn_egg"),
                    createSpawnEgg(EntityType.GOAT));
    public static final SpawnEgg GUARDIAN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "guardian_spawn_egg"),
                    createSpawnEgg(EntityType.GUARDIAN));
    public static final SpawnEgg HOGLIN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "hoglin_spawn_egg"),
                    createSpawnEgg(EntityType.HOGLIN));
    public static final SpawnEgg HORSE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "horse_spawn_egg"),
                    createSpawnEgg(EntityType.HORSE));
    public static final SpawnEgg HUSK_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "husk_spawn_egg"),
                    createSpawnEgg(EntityType.HUSK));
    public static final SpawnEgg IRON_GOLEM_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "iron_golem_spawn_egg"),
                    createSpawnEgg(EntityType.IRON_GOLEM));
    public static final SpawnEgg LLAMA_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "llama_spawn_egg"),
                    createSpawnEgg(EntityType.LLAMA));
    public static final SpawnEgg MAGMA_CUBE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "magma_cube_spawn_egg"),
                    createSpawnEgg(EntityType.MAGMA_CUBE));
    public static final SpawnEgg OCELOT_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ocelot_spawn_egg"),
                    createSpawnEgg(EntityType.OCELOT));
    public static final SpawnEgg PANDA_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "panda_spawn_egg"),
                    createSpawnEgg(EntityType.PANDA));
    public static final SpawnEgg PARROT_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "parrot_spawn_egg"),
                    createSpawnEgg(EntityType.PARROT));
    public static final SpawnEgg PHANTOM_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "phantom_spawn_egg"),
                    createSpawnEgg(EntityType.PHANTOM));
    public static final SpawnEgg PIG_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "pig_spawn_egg"),
                    createSpawnEgg(EntityType.PIG));
    public static final SpawnEgg PIGLIN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "piglin_spawn_egg"),
                    createSpawnEgg(EntityType.PIGLIN));
    public static final SpawnEgg PILLAGER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "pillager_spawn_egg"),
                    createSpawnEgg(EntityType.PILLAGER));
    public static final SpawnEgg POLAR_BEAR_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "polar_bear_spawn_egg"),
                    createSpawnEgg(EntityType.POLAR_BEAR));
    public static final SpawnEgg PUFFERFISH_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "pufferfish_spawn_egg"),
                    createSpawnEgg(EntityType.PUFFERFISH));
    public static final SpawnEgg RABBIT_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "rabbit_spawn_egg"),
                    createSpawnEgg(EntityType.RABBIT));
    public static final SpawnEgg RAVAGER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ravager_spawn_egg"),
                    createSpawnEgg(EntityType.RAVAGER));
    public static final SpawnEgg SALMON_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "salmon_spawn_egg"),
                    createSpawnEgg(EntityType.SALMON));
    public static final SpawnEgg SHEEP_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "sheep_spawn_egg"),
                    createSpawnEgg(EntityType.SHEEP));
    public static final SpawnEgg SHULKER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "shulker_spawn_egg"),
                    createSpawnEgg(EntityType.SHULKER));
    public static final SpawnEgg SILVERFISH_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "silverfish_spawn_egg"),
                    createSpawnEgg(EntityType.SILVERFISH));
    public static final SpawnEgg SKELETON_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "skeleton_spawn_egg"),
                    createSpawnEgg(EntityType.SKELETON));
    public static final SpawnEgg SLIME_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "slime_spawn_egg"),
                    createSpawnEgg(EntityType.SLIME));
    public static final SpawnEgg SNOW_GOLEM_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "snow_golem_spawn_egg"),
                    createSpawnEgg(EntityType.SNOW_GOLEM));
    public static final SpawnEgg SPIDER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "spider_spawn_egg"),
                    createSpawnEgg(EntityType.SPIDER));
    public static final SpawnEgg SQUID_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "squid_spawn_egg"),
                    createSpawnEgg(EntityType.SQUID));
    public static final SpawnEgg STRAY_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "stray_spawn_egg"),
                    createSpawnEgg(EntityType.STRAY));
    public static final SpawnEgg STRIDER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "strider_spawn_egg"),
                    createSpawnEgg(EntityType.STRIDER));
    public static final SpawnEgg TROPICAL_FISH_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "tropical_fish_spawn_egg"),
                    createSpawnEgg(EntityType.TROPICAL_FISH));
    public static final SpawnEgg TURTLE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "turtle_spawn_egg"),
                    createSpawnEgg(EntityType.TURTLE));
    public static final SpawnEgg VEX_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "vex_spawn_egg"),
                    createSpawnEgg(EntityType.VEX));
    public static final SpawnEgg VILLAGER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "villager_spawn_egg"),
                    createSpawnEgg(EntityType.VILLAGER));
    public static final SpawnEgg VINDICATOR_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "vindicator_spawn_egg"),
                    createSpawnEgg(EntityType.VINDICATOR));
    public static final SpawnEgg WARDEN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "warden_spawn_egg"),
                    createSpawnEgg(EntityType.WARDEN));
    public static final SpawnEgg WITHER_SKELETON_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "wither_skeleton_spawn_egg"),
                    createSpawnEgg(EntityType.WITHER_SKELETON));
    public static final SpawnEgg WOLF_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "wolf_spawn_egg"),
                    createSpawnEgg(EntityType.WOLF));
    public static final SpawnEgg ZOGLIN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "zoglin_spawn_egg"),
                    createSpawnEgg(EntityType.ZOGLIN));
    public static final SpawnEgg ZOMBIE_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "zombie_spawn_egg"),
                    createSpawnEgg(EntityType.ZOMBIE));
    public static final SpawnEgg ZOMBIE_VILLAGER_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "zombie_villager_spawn_egg"),
                    createSpawnEgg(EntityType.ZOMBIE_VILLAGER));
    public static final SpawnEgg ZOMBIFIED_PIGLIN_SPAWN_EGG =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "zombified_piglin_spawn_egg"),
                    createSpawnEgg(EntityType.ZOMBIFIED_PIGLIN));
    //Materials
    public static final Item EMPTY_DISC =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "empty_disc"),
                    createItem());
    public static final Item IRON_BUNDLE_UPGRADE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "iron_bundle_upgrade"),
                    createItem());
    public static final Item DIAMOND_BUNDLE_UPGRADE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "diamond_bundle_upgrade"),
                    createItem());
    public static final DamageableItem BUNDLED_FLOWERS =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "bundled_flowers"),
                    new DamageableItem(new FabricItemSettings().maxCount(1).maxDamage(16)));
    public static final TooltipItem FORTRESS_NUGGET =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "fortress_nugget"),
                    new TooltipItem(new FabricItemSettings(), Formatting.GRAY));
    public static final Item ENDER_PEARL_SHARD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "ender_pearl_shard"),
                    createItem());
    public static final Item MAGIC_INGOT =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "magic_ingot"),
                    createItem());
    //Tools
    public static final SwordItem AMETHYST_SWORD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "amethyst_sword"),
                    new USwordItem(UMaterials.AMETHYST, 3, -2.4F, new FabricItemSettings()));
    public static final ShovelItem AMETHYST_SHOVEL =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "amethyst_shovel"),
                    new UShovelItem(UMaterials.AMETHYST, 1.5F, -3.0F, new FabricItemSettings()));
    public static final PickaxeItem AMETHYST_PICKAXE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "amethyst_pickaxe"),
                    new UPickaxeItem(UMaterials.AMETHYST, 1, -2.8F, new FabricItemSettings()));
    public static final AxeItem AMETHYST_AXE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "amethyst_axe"),
                    new UAxeItem(UMaterials.AMETHYST, 5.0F, -3.0F, new FabricItemSettings()));
    public static final HoeItem AMETHYST_HOE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "amethyst_hoe"),
                    new UHoeItem(UMaterials.AMETHYST, -3, 0.0F, new FabricItemSettings()));
    public static final SwordItem EMERALD_SWORD =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "emerald_sword"),
                    new USwordItem(UMaterials.EMERALD, 3, -2.4F, new FabricItemSettings()));
    public static final ShovelItem EMERALD_SHOVEL =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "emerald_shovel"),
                    new UShovelItem(UMaterials.EMERALD, 1.5F, -3.0F, new FabricItemSettings()));
    public static final PickaxeItem EMERALD_PICKAXE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "emerald_pickaxe"),
                    new UPickaxeItem(UMaterials.EMERALD, 1, -2.8F, new FabricItemSettings()));
    public static final AxeItem EMERALD_AXE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "emerald_axe"),
                    new UAxeItem(UMaterials.EMERALD, 5.0F, -3.0F, new FabricItemSettings()));
    public static final HoeItem EMERALD_HOE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "emerald_hoe"),
                    new UHoeItem(UMaterials.EMERALD, -3, 0.0F, new FabricItemSettings()));
    //Utility
    public static final Bundle BUNDLE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "bundle"),
                    new Bundle(new FabricItemSettings().maxCount(1)));
    public static final IronBundle IRON_BUNDLE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "iron_bundle"),
                    new IronBundle(new FabricItemSettings().maxCount(1)));
    public static final DiamondBundle DIAMOND_BUNDLE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "diamond_bundle"),
                    new DiamondBundle(new FabricItemSettings().maxCount(1)));
    public static final NetheriteBundle NETHERITE_BUNDLE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "netherite_bundle"),
                    new NetheriteBundle(new FabricItemSettings().maxCount(1).fireproof()));
    public static final DragonBundle DRAGON_BUNDLE =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "dragon_bundle"),
                    new DragonBundle(new FabricItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC)));
    private static Item createItem() {
        return new Item(new FabricItemSettings());
    }
    private static SpawnEgg createSpawnEgg(EntityType<? extends MobEntity> type) {
        return new SpawnEgg(new FabricItemSettings(), type);
    }
    public static void itemTabs() {
        ItemGroupEvents.modifyEntriesEvent(UGroups.UAddTab).register(entries -> {
            //Ores
            entries.add(ALLAY_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_ALLAY_ORE.asItem().getDefaultStack());
            entries.add(AXOLOTL_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_AXOLOTL_ORE.asItem().getDefaultStack());
            entries.add(BAT_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_BAT_ORE.asItem().getDefaultStack());
            entries.add(BEE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_BEE_ORE.asItem().getDefaultStack());
            entries.add(BLAZE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_BLAZE_ORE.asItem().getDefaultStack());
            entries.add(CAT_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_CAT_ORE.asItem().getDefaultStack());
            entries.add(CAVE_SPIDER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_CAVE_SPIDER_ORE.asItem().getDefaultStack());
            entries.add(CHICKEN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_CHICKEN_ORE.asItem().getDefaultStack());
            entries.add(COD_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_COD_ORE.asItem().getDefaultStack());
            entries.add(COW_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_COW_ORE.asItem().getDefaultStack());
            entries.add(CREEPER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_CREEPER_ORE.asItem().getDefaultStack());
            entries.add(DOLPHIN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_DOLPHIN_ORE.asItem().getDefaultStack());
            entries.add(DONKEY_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_DONKEY_ORE.asItem().getDefaultStack());
            entries.add(ENDERMAN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_ENDERMAN_ORE.asItem().getDefaultStack());
            entries.add(ENDERMITE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_ENDERMITE_ORE.asItem().getDefaultStack());
            entries.add(FOX_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_FOX_ORE.asItem().getDefaultStack());
            entries.add(FROG_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_FROG_ORE.asItem().getDefaultStack());
            entries.add(GHAST_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_GHAST_ORE.asItem().getDefaultStack());
            entries.add(GLOW_SQUID_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_GLOW_SQUID_ORE.asItem().getDefaultStack());
            entries.add(GOAT_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_GOAT_ORE.asItem().getDefaultStack());
            entries.add(GUARDIAN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_GUARDIAN_ORE.asItem().getDefaultStack());
            entries.add(HOGLIN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_HOGLIN_ORE.asItem().getDefaultStack());
            entries.add(HORSE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_HORSE_ORE.asItem().getDefaultStack());
            entries.add(ILLAGER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_ILLAGER_ORE.asItem().getDefaultStack());
            entries.add(IRON_GOLEM_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_IRON_GOLEM_ORE.asItem().getDefaultStack());
            entries.add(LLAMA_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_LLAMA_ORE.asItem().getDefaultStack());
            entries.add(MAGMA_CUBE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_MAGMA_CUBE_ORE.asItem().getDefaultStack());
            entries.add(OCELOT_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_OCELOT_ORE.asItem().getDefaultStack());
            entries.add(PANDA_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_PANDA_ORE.asItem().getDefaultStack());
            entries.add(PARROT_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_PARROT_ORE.asItem().getDefaultStack());
            entries.add(PHANTOM_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_PHANTOM_ORE.asItem().getDefaultStack());
            entries.add(PIG_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_PIG_ORE.asItem().getDefaultStack());
            entries.add(PIGLIN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_PIGLIN_ORE.asItem().getDefaultStack());
            entries.add(POLAR_BEAR_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_POLAR_BEAR_ORE.asItem().getDefaultStack());
            entries.add(PUFFERFISH_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_PUFFERFISH_ORE.asItem().getDefaultStack());
            entries.add(RABBIT_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_RABBIT_ORE.asItem().getDefaultStack());
            entries.add(RAVAGER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_RAVAGER_ORE.asItem().getDefaultStack());
            entries.add(SALMON_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SALMON_ORE.asItem().getDefaultStack());
            entries.add(SHEEP_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SHEEP_ORE.asItem().getDefaultStack());
            entries.add(SHULKER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SHULKER_ORE.asItem().getDefaultStack());
            entries.add(SILVERFISH_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SILVERFISH_ORE.asItem().getDefaultStack());
            entries.add(SKELETON_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SKELETON_ORE.asItem().getDefaultStack());
            entries.add(SLIME_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SLIME_ORE.asItem().getDefaultStack());
            entries.add(SNOW_GOLEM_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SNOW_GOLEM_ORE.asItem().getDefaultStack());
            entries.add(SPIDER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SPIDER_ORE.asItem().getDefaultStack());
            entries.add(SQUID_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_SQUID_ORE.asItem().getDefaultStack());
            entries.add(STRIDER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_STRIDER_ORE.asItem().getDefaultStack());
            entries.add(TROPICAL_FISH_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_TROPICAL_FISH_ORE.asItem().getDefaultStack());
            entries.add(TURTLE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_TURTLE_ORE.asItem().getDefaultStack());
            entries.add(VEX_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_VEX_ORE.asItem().getDefaultStack());
            entries.add(VILLAGER_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_VILLAGER_ORE.asItem().getDefaultStack());
            entries.add(WARDEN_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_WARDEN_ORE.asItem().getDefaultStack());
            entries.add(WITHER_SKELETON_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_WITHER_SKELETON_ORE.asItem().getDefaultStack());
            entries.add(WOLF_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_WOLF_ORE.asItem().getDefaultStack());
            entries.add(ZOMBIE_ORE.asItem().getDefaultStack());
            entries.add(DEEPSLATE_ZOMBIE_ORE.asItem().getDefaultStack());
            // Mob Ore Shards
            entries.add(ALLAY_SHARD.getDefaultStack());
            entries.add(AXOLOTL_SHARD.getDefaultStack());
            entries.add(BAT_SHARD.getDefaultStack());
            entries.add(BEE_SHARD.getDefaultStack());
            entries.add(BLAZE_SHARD.getDefaultStack());
            entries.add(CAT_SHARD.getDefaultStack());
            entries.add(CAVE_SPIDER_SHARD.getDefaultStack());
            entries.add(CHICKEN_SHARD.getDefaultStack());
            entries.add(COD_SHARD.getDefaultStack());
            entries.add(COW_SHARD.getDefaultStack());
            entries.add(CREEPER_SHARD.getDefaultStack());
            entries.add(DOLPHIN_SHARD.getDefaultStack());
            entries.add(DONKEY_SHARD.getDefaultStack());
            entries.add(ENDERMAN_SHARD.getDefaultStack());
            entries.add(ENDERMITE_SHARD.getDefaultStack());
            entries.add(FOX_SHARD.getDefaultStack());
            entries.add(FROG_SHARD.getDefaultStack());
            entries.add(GHAST_SHARD.getDefaultStack());
            entries.add(GLOW_SQUID_SHARD.getDefaultStack());
            entries.add(GOAT_SHARD.getDefaultStack());
            entries.add(GUARDIAN_SHARD.getDefaultStack());
            entries.add(HOGLIN_SHARD.getDefaultStack());
            entries.add(HORSE_SHARD.getDefaultStack());
            entries.add(ILLAGER_SHARD.getDefaultStack());
            entries.add(IRON_GOLEM_SHARD.getDefaultStack());
            entries.add(LLAMA_SHARD.getDefaultStack());
            entries.add(MAGMA_CUBE_SHARD.getDefaultStack());
            entries.add(OCELOT_SHARD.getDefaultStack());
            entries.add(PANDA_SHARD.getDefaultStack());
            entries.add(PARROT_SHARD.getDefaultStack());
            entries.add(PHANTOM_SHARD.getDefaultStack());
            entries.add(PIG_SHARD.getDefaultStack());
            entries.add(PIGLIN_SHARD.getDefaultStack());
            entries.add(POLAR_BEAR_SHARD.getDefaultStack());
            entries.add(PUFFERFISH_SHARD.getDefaultStack());
            entries.add(RABBIT_SHARD.getDefaultStack());
            entries.add(RAVAGER_SHARD.getDefaultStack());
            entries.add(SALMON_SHARD.getDefaultStack());
            entries.add(SHEEP_SHARD.getDefaultStack());
            entries.add(SHULKER_SHARD.getDefaultStack());
            entries.add(SILVERFISH_SHARD.getDefaultStack());
            entries.add(SKELETON_SHARD.getDefaultStack());
            entries.add(SLIME_SHARD.getDefaultStack());
            entries.add(SNOW_GOLEM_SHARD.getDefaultStack());
            entries.add(SPIDER_SHARD.getDefaultStack());
            entries.add(SQUID_SHARD.getDefaultStack());
            entries.add(STRIDER_SHARD.getDefaultStack());
            entries.add(TROPICAL_FISH_SHARD.getDefaultStack());
            entries.add(TURTLE_SHARD.getDefaultStack());
            entries.add(VEX_SHARD.getDefaultStack());
            entries.add(VILLAGER_SHARD.getDefaultStack());
            entries.add(WARDEN_SHARD.getDefaultStack());
            entries.add(WITHER_SKELETON_SHARD.getDefaultStack());
            entries.add(WOLF_SHARD.getDefaultStack());
            entries.add(ZOMBIE_SHARD.getDefaultStack());
            entries.add(SMALL_ALLAY_SHARD.getDefaultStack());
            entries.add(SMALL_AXOLOTL_SHARD.getDefaultStack());
            entries.add(SMALL_BAT_SHARD.getDefaultStack());
            entries.add(SMALL_BEE_SHARD.getDefaultStack());
            entries.add(SMALL_BLAZE_SHARD.getDefaultStack());
            entries.add(SMALL_CAT_SHARD.getDefaultStack());
            entries.add(SMALL_CAVE_SPIDER_SHARD.getDefaultStack());
            entries.add(SMALL_CHICKEN_SHARD.getDefaultStack());
            entries.add(SMALL_COD_SHARD.getDefaultStack());
            entries.add(SMALL_COW_SHARD.getDefaultStack());
            entries.add(SMALL_CREEPER_SHARD.getDefaultStack());
            entries.add(SMALL_DOLPHIN_SHARD.getDefaultStack());
            entries.add(SMALL_DONKEY_SHARD.getDefaultStack());
            entries.add(SMALL_ENDERMAN_SHARD.getDefaultStack());
            entries.add(SMALL_ENDERMITE_SHARD.getDefaultStack());
            entries.add(SMALL_FOX_SHARD.getDefaultStack());
            entries.add(SMALL_FROG_SHARD.getDefaultStack());
            entries.add(SMALL_GHAST_SHARD.getDefaultStack());
            entries.add(SMALL_GLOW_SQUID_SHARD.getDefaultStack());
            entries.add(SMALL_GOAT_SHARD.getDefaultStack());
            entries.add(SMALL_GUARDIAN_SHARD.getDefaultStack());
            entries.add(SMALL_HOGLIN_SHARD.getDefaultStack());
            entries.add(SMALL_HORSE_SHARD.getDefaultStack());
            entries.add(SMALL_ILLAGER_SHARD.getDefaultStack());
            entries.add(SMALL_IRON_GOLEM_SHARD.getDefaultStack());
            entries.add(SMALL_LLAMA_SHARD.getDefaultStack());
            entries.add(SMALL_MAGMA_CUBE_SHARD.getDefaultStack());
            entries.add(SMALL_OCELOT_SHARD.getDefaultStack());
            entries.add(SMALL_PANDA_SHARD.getDefaultStack());
            entries.add(SMALL_PARROT_SHARD.getDefaultStack());
            entries.add(SMALL_PHANTOM_SHARD.getDefaultStack());
            entries.add(SMALL_PIG_SHARD.getDefaultStack());
            entries.add(SMALL_PIGLIN_SHARD.getDefaultStack());
            entries.add(SMALL_POLAR_BEAR_SHARD.getDefaultStack());
            entries.add(SMALL_PUFFERFISH_SHARD.getDefaultStack());
            entries.add(SMALL_RABBIT_SHARD.getDefaultStack());
            entries.add(SMALL_RAVAGER_SHARD.getDefaultStack());
            entries.add(SMALL_SALMON_SHARD.getDefaultStack());
            entries.add(SMALL_SHEEP_SHARD.getDefaultStack());
            entries.add(SMALL_SHULKER_SHARD.getDefaultStack());
            entries.add(SMALL_SILVERFISH_SHARD.getDefaultStack());
            entries.add(SMALL_SKELETON_SHARD.getDefaultStack());
            entries.add(SMALL_SLIME_SHARD.getDefaultStack());
            entries.add(SMALL_SNOW_GOLEM_SHARD.getDefaultStack());
            entries.add(SMALL_SPIDER_SHARD.getDefaultStack());
            entries.add(SMALL_SQUID_SHARD.getDefaultStack());
            entries.add(SMALL_STRIDER_SHARD.getDefaultStack());
            entries.add(SMALL_TROPICAL_FISH_SHARD.getDefaultStack());
            entries.add(SMALL_TURTLE_SHARD.getDefaultStack());
            entries.add(SMALL_VEX_SHARD.getDefaultStack());
            entries.add(SMALL_VILLAGER_SHARD.getDefaultStack());
            entries.add(SMALL_WARDEN_SHARD.getDefaultStack());
            entries.add(SMALL_WITHER_SKELETON_SHARD.getDefaultStack());
            entries.add(SMALL_WOLF_SHARD.getDefaultStack());
            entries.add(SMALL_ZOMBIE_SHARD.getDefaultStack());
            // Spawn Eggs
            entries.add(ALLAY_SPAWN_EGG.getDefaultStack());
            entries.add(AXOLOTL_SPAWN_EGG.getDefaultStack());
            entries.add(BAT_SPAWN_EGG.getDefaultStack());
            entries.add(BEE_SPAWN_EGG.getDefaultStack());
            entries.add(BLAZE_SPAWN_EGG.getDefaultStack());
            entries.add(CAT_SPAWN_EGG.getDefaultStack());
            entries.add(CAVE_SPIDER_SPAWN_EGG.getDefaultStack());
            entries.add(CHICKEN_SPAWN_EGG.getDefaultStack());
            entries.add(COD_SPAWN_EGG.getDefaultStack());
            entries.add(COW_SPAWN_EGG.getDefaultStack());
            entries.add(CREEPER_SPAWN_EGG.getDefaultStack());
            entries.add(DOLPHIN_SPAWN_EGG.getDefaultStack());
            entries.add(DONKEY_SPAWN_EGG.getDefaultStack());
            entries.add(DROWNED_SPAWN_EGG.getDefaultStack());
            entries.add(ENDERMAN_SPAWN_EGG.getDefaultStack());
            entries.add(ENDERMITE_SPAWN_EGG.getDefaultStack());
            entries.add(ELDER_GUARDIAN_SPAWN_EGG.getDefaultStack());
            entries.add(EVOKER_SPAWN_EGG.getDefaultStack());
            entries.add(FOX_SPAWN_EGG.getDefaultStack());
            entries.add(FROG_SPAWN_EGG.getDefaultStack());
            entries.add(GHAST_SPAWN_EGG.getDefaultStack());
            entries.add(GLOW_SQUID_SPAWN_EGG.getDefaultStack());
            entries.add(GOAT_SPAWN_EGG.getDefaultStack());
            entries.add(GUARDIAN_SPAWN_EGG.getDefaultStack());
            entries.add(HOGLIN_SPAWN_EGG.getDefaultStack());
            entries.add(HORSE_SPAWN_EGG.getDefaultStack());
            entries.add(HUSK_SPAWN_EGG.getDefaultStack());
            entries.add(IRON_GOLEM_SPAWN_EGG.getDefaultStack());
            entries.add(LLAMA_SPAWN_EGG.getDefaultStack());
            entries.add(MAGMA_CUBE_SPAWN_EGG.getDefaultStack());
            entries.add(OCELOT_SPAWN_EGG.getDefaultStack());
            entries.add(PANDA_SPAWN_EGG.getDefaultStack());
            entries.add(PARROT_SPAWN_EGG.getDefaultStack());
            entries.add(PHANTOM_SPAWN_EGG.getDefaultStack());
            entries.add(PIG_SPAWN_EGG.getDefaultStack());
            entries.add(PIGLIN_SPAWN_EGG.getDefaultStack());
            entries.add(PILLAGER_SPAWN_EGG.getDefaultStack());
            entries.add(POLAR_BEAR_SPAWN_EGG.getDefaultStack());
            entries.add(PUFFERFISH_SPAWN_EGG.getDefaultStack());
            entries.add(RABBIT_SPAWN_EGG.getDefaultStack());
            entries.add(RAVAGER_SPAWN_EGG.getDefaultStack());
            entries.add(SALMON_SPAWN_EGG.getDefaultStack());
            entries.add(SHEEP_SPAWN_EGG.getDefaultStack());
            entries.add(SHULKER_SPAWN_EGG.getDefaultStack());
            entries.add(SILVERFISH_SPAWN_EGG.getDefaultStack());
            entries.add(SKELETON_SPAWN_EGG.getDefaultStack());
            entries.add(SLIME_SPAWN_EGG.getDefaultStack());
            entries.add(SNOW_GOLEM_SPAWN_EGG.getDefaultStack());
            entries.add(SPIDER_SPAWN_EGG.getDefaultStack());
            entries.add(SQUID_SPAWN_EGG.getDefaultStack());
            entries.add(STRAY_SPAWN_EGG.getDefaultStack());
            entries.add(STRIDER_SPAWN_EGG.getDefaultStack());
            entries.add(TROPICAL_FISH_SPAWN_EGG.getDefaultStack());
            entries.add(TURTLE_SPAWN_EGG.getDefaultStack());
            entries.add(VEX_SPAWN_EGG.getDefaultStack());
            entries.add(VILLAGER_SPAWN_EGG.getDefaultStack());
            entries.add(VINDICATOR_SPAWN_EGG.getDefaultStack());
            entries.add(WARDEN_SPAWN_EGG.getDefaultStack());
            entries.add(WITHER_SKELETON_SPAWN_EGG.getDefaultStack());
            entries.add(WOLF_SPAWN_EGG.getDefaultStack());
            entries.add(ZOGLIN_SPAWN_EGG.getDefaultStack());
            entries.add(ZOMBIE_SPAWN_EGG.getDefaultStack());
            entries.add(ZOMBIE_VILLAGER_SPAWN_EGG.getDefaultStack());
            entries.add(ZOMBIFIED_PIGLIN_SPAWN_EGG.getDefaultStack());
            // Materials
            entries.add(EMPTY_DISC.getDefaultStack());
            entries.add(IRON_BUNDLE_UPGRADE.getDefaultStack());
            entries.add(DIAMOND_BUNDLE_UPGRADE.getDefaultStack());
            entries.add(BUNDLED_FLOWERS.getDefaultStack());
            entries.add(FORTRESS_NUGGET.getDefaultStack());
            entries.add(ENDER_PEARL_SHARD.getDefaultStack());
            entries.add(MAGIC_INGOT.getDefaultStack());
            // Tools
            entries.add(AMETHYST_SWORD.getDefaultStack());
            entries.add(AMETHYST_SHOVEL.getDefaultStack());
            entries.add(AMETHYST_PICKAXE.getDefaultStack());
            entries.add(AMETHYST_AXE.getDefaultStack());
            entries.add(AMETHYST_HOE.getDefaultStack());
            entries.add(EMERALD_SWORD.getDefaultStack());
            entries.add(EMERALD_SHOVEL.getDefaultStack());
            entries.add(EMERALD_PICKAXE.getDefaultStack());
            entries.add(EMERALD_AXE.getDefaultStack());
            entries.add(EMERALD_HOE.getDefaultStack());
            //Utility
            entries.add(BUNDLE.getDefaultStack());
            entries.add(IRON_BUNDLE.getDefaultStack());
            entries.add(DIAMOND_BUNDLE.getDefaultStack());
            entries.add(NETHERITE_BUNDLE.getDefaultStack());
            entries.add(DRAGON_BUNDLE.getDefaultStack());
        });
    }
}
