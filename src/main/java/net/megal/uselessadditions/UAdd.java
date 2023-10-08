package net.megal.uselessadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.UGroups;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class UAdd implements ModInitializer {
    public static final String MOD_ID = "uselessadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TagKey<Item> BUNDLES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "bundles"));
    public static final TagKey<Item> HAMMERS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "smithing_hammers"));
    public static final TagKey<Item> NATURAL_MENDING = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "natural_mending"));
    public static final TagKey<Item> AUTO_SMELT = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "auto_smelt"));
    public static final TagKey<Item> MOB_SHARDS = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "mob_shards"));
    public static final TagKey<Item> SMALL_MOB_SHARDS = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "small_mob_shards"));
    public static final List<Item> naturalMendingItems = new ArrayList<>();
    public static final List<Item> autoSmeltItems = new ArrayList<>();
    static {
        naturalMendingItems.add(UItems.ENCHANTED_HAMMER);
        naturalMendingItems.add(UItems.AMETHYST_SWORD);
        naturalMendingItems.add(UItems.AMETHYST_SHOVEL);
        naturalMendingItems.add(UItems.AMETHYST_PICKAXE);
        naturalMendingItems.add(UItems.AMETHYST_AXE);
        naturalMendingItems.add(UItems.AMETHYST_HOE);
        autoSmeltItems.add(UItems.BLAZE_METAL_SWORD);
        autoSmeltItems.add(UItems.BLAZE_METAL_SHOVEL);
        autoSmeltItems.add(UItems.BLAZE_METAL_PICKAXE);
        autoSmeltItems.add(UItems.BLAZE_METAL_AXE);
        autoSmeltItems.add(UItems.BLAZE_METAL_HOE);
    }
    private void AddCompostable() {
        compostableItem(UItems.LESSER_GOLDEN_APPLE, ComposterRarities.HIGH);
        compostableItem(UItems.LESSER_GOLDEN_CARROT, ComposterRarities.HIGH);
        compostableItem(UItems.BUNDLED_FLOWERS, ComposterRarities.HIGH);
    }
    @Override
    public void onInitialize() {
        //Loads enchantments
        UEnchantments.enchLoad();

        //Loads blocks
        UBlocks.blockLoad();

        //Loads item tabs
        UGroups.groupLoad();

        //Adds items to their respective creative tabs
        UItems.itemTabs();

        //Makes items compostable
        AddCompostable();

        //Loads in world gen stuff such as ores
        UWorldgen.wgenLoad();

        //Adds stuff to loot tables
        ULootTables.modifyLootTables();
    }
    private void compostableItem(Item item, ComposterRarities rarity) {
        CompostingChanceRegistry.INSTANCE.add(item, rarity.getRarity());
    }
    private enum ComposterRarities {
        VERY_LOW(.3f),
        LOW(.5f),
        MED(.65f),
        HIGH(.85f),
        GUARUNTEED(1f);
        private final float rarity;
        private ComposterRarities(float rarity) {
            this.rarity = rarity;
        }
        public float getRarity() {return rarity;}
    }
}