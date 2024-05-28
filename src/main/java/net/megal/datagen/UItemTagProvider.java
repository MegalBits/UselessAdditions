package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.megal.block.UBlocks;
import net.megal.item.UItems;
import net.megal.tag.UItemTags;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class UItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public UItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.SWORDS).add(
                UItems.AMETHYST_SWORD
        );
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(
                UItems.AMETHYST_SHOVEL
        );
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(
                UItems.AMETHYST_PICKAXE
        );
        getOrCreateTagBuilder(ItemTags.AXES).add(
                UItems.AMETHYST_AXE
        );
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(
                UItems.AMETHYST_HOE
        );
    }
}
