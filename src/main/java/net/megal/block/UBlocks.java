package net.megal.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.megal.UAdd;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class UBlocks {
    private static <T extends Block, Y extends Item.Settings> T register(String id, T block, Y settings) {
        registerBlockItem(id, block, settings);
        return Registry.register(Registries.BLOCK, new Identifier(UAdd.ID, id), block);
    }
    private static <T extends Block> T register(String id, T block) {
        return Registry.register(Registries.BLOCK, new Identifier(UAdd.ID, id), block);
    }
    private static <T extends Item.Settings> void registerBlockItem(String id, Block block, T settings) {
        Registry.register(Registries.ITEM, new Identifier(UAdd.ID, id), new BlockItem(block, settings));
    }

    public static void loadStuff() {}
}
