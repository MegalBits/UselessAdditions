package net.megal.uselessadditions.client.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.client.block.render.SieveEntityRenderer;
import net.megal.uselessadditions.client.block.render.SurvivalSpawnerEntityRenderer;
import net.megal.uselessadditions.client.entity.render.FrigidZombieRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class UBlockRendering {
    public static void registerBlockRenderers() {
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.PLANT_FIBRE_TRIPWIRE, RenderLayer.getTripwire());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.SIEVE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.EMPTY_SPAWNER, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.SURVIVAL_SPAWNER, RenderLayer.getCutoutMipped());

        BlockEntityRendererFactories.register(UBlocks.SIEVE_ENTITY, SieveEntityRenderer::new);
        BlockEntityRendererFactories.register(UBlocks.SURVIVAL_SPAWNER_ENTITY, SurvivalSpawnerEntityRenderer::new);
    }
}
