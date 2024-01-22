package net.megal.uselessadditions.client.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.block.SieveEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class SieveEntityRenderer implements BlockEntityRenderer<SieveEntity> {
    private final BlockRenderManager renderManager;
    public SieveEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        renderManager = ctx.getRenderManager();
    }

    @Override
    public void render(SieveEntity sieveEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = sieveEntity.getWorld();
        if (world == null) return;
        matrices.push();
        matrices.translate(0.125, (float)sieveEntity.getUses()/(sieveEntity.getMesh() != null ? sieveEntity.getMesh().meshType.uses : sieveEntity.defaultUses)*0.25+0.03125, 0.125);
        matrices.scale(0.75f, 0.75f, 0.75f);
        BlockState block = sieveEntity.getBlock() != null ? sieveEntity.getBlock().getDefaultState() : Blocks.AIR.getDefaultState();
        renderManager.renderBlock(block, sieveEntity.getPos(), world, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), false, world.getRandom());
        matrices.pop();
    }
}
