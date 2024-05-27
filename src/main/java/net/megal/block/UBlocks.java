package net.megal.block;

import net.megal.UAdd;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UBlocks {
    private static <T extends Block> T register(String id, T block, Item.Settings settings) {
        registerBlockItem(id, block, settings);
        return Registry.register(Registries.BLOCK, new Identifier(UAdd.ID, id), block);
    }

    private static <T extends Block> T register(String id, T block) {
        return Registry.register(Registries.BLOCK, new Identifier(UAdd.ID, id), block);
    }

    private static BlockItem registerBlockItem(String id, Block block, Item.Settings settings) {
        return Registry.register(Registries.ITEM, new Identifier(UAdd.ID, id), new BlockItem(block, settings));
    }

    public static void loadStuff() {}
}
