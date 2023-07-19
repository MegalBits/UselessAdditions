package net.megal.uselessadditions;

import net.fabricmc.api.ModInitializer;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.UGroups;
import net.megal.uselessadditions.item.UItems;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UAdd implements ModInitializer {
    public static final String MOD_ID = "uselessadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    //Used to apply the enchantments when using /give or grabbing via creative, crafting recipes are handled by a separate list in a mixin
    public static final TagKey<Item> NATURAL_MENDING = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "natural_mending"));
    @Override
    public void onInitialize() {
        //Loads enchantments
        UEnchantments.enchLoad();

        //Loads blocks
        UBlocks.blockLoad();

        //Registers item groups
        UGroups.registerItemGroups();

        //Adds items to their respective creative tabs
        UItems.itemTabs();

        //Loads in world gen stuff such as ores
        UWorldgen.wgenLoad();
    }
}