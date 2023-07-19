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
    //Materials
    public static final Item MAGIC_INGOT =
            Registry.register(Registries.ITEM, new Identifier(UAdd.MOD_ID, "magic_ingot"),
                    new Item(new FabricItemSettings()));

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
