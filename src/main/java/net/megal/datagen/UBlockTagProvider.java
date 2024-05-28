package net.megal.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.megal.UAdd;
import net.megal.block.UBlocks;
import net.megal.item.UItems;
import net.megal.item.UToolMaterials;
import net.megal.tag.UBlockTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class UBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public UBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        registerInverseBlockTag(
                UToolMaterials.AMETHYST
        );

        //getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add();
        //getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add();
        //getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add();
        //getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add();
        //getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add();
    }

    private void registerInverseBlockTag(ToolMaterial... material) {
        for (ToolMaterial m : material) {
            getOrCreateTagBuilder(m.getInverseTag());
        }
    }
}
