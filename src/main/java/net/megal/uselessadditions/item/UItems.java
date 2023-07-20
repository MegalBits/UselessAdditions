package net.megal.uselessadditions.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.base.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
    //Materials
    public static final Item MAGIC_INGOT =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "magic_ingot"),
                    createItem());
    //Tools & Utility
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
    private static Item createItem() {
        return new Item(new FabricItemSettings());
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
            // Materials
            entries.add(MAGIC_INGOT.getDefaultStack());
            // Tools & Utility
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
        });
    }
}
