package net.megal.uselessadditions.client.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.client.block.render.PipeEntityRenderer;
import net.megal.uselessadditions.client.block.render.SieveEntityRenderer;
import net.megal.uselessadditions.client.block.render.SurvivalSpawnerEntityRenderer;
import net.megal.uselessadditions.client.entity.render.FrigidZombieRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class UBlockRendering {
    public static void registerBlockRenderers() {
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.PLANT_FIBRE_TRIPWIRE, RenderLayer.getTripwire());

        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.WOODEN_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.STONE_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.IRON_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.GOLD_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.DIAMOND_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.NETHERITE_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.DRAGON_PIPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.SIEVE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.EMPTY_SPAWNER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.SURVIVAL_SPAWNER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.PILE_OF_SLIME, RenderLayer.getTranslucent());

        BlockEntityRendererFactories.register(UBlocks.SIEVE_ENTITY, SieveEntityRenderer::new);
        BlockEntityRendererFactories.register(UBlocks.PIPE_ENTITY, PipeEntityRenderer::new);
        BlockEntityRendererFactories.register(UBlocks.SURVIVAL_SPAWNER_ENTITY, SurvivalSpawnerEntityRenderer::new);
    }
}
