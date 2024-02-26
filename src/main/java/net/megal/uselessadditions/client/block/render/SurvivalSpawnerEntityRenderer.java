package net.megal.uselessadditions.client.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.block.SurvivalSpawnerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(value= EnvType.CLIENT)
public class SurvivalSpawnerEntityRenderer implements BlockEntityRenderer<SurvivalSpawnerEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public SurvivalSpawnerEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }

    public void render(SurvivalSpawnerEntity spawnerEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        if (spawnerEntity.getWorld() == null) return;

        matrixStack.push();
        matrixStack.translate(0.5F, 0.0F, 0.5F);
        Entity entity = spawnerEntity.getRenderedEntity(spawnerEntity.getWorld(), spawnerEntity.getWorld().getRandom(), spawnerEntity.getPos());
        if (entity != null) {
            float g = 0.53125F;
            float h = Math.max(entity.getWidth(), entity.getHeight());
            if ((double)h > 1.0) {
                g /= h;
            }

            matrixStack.translate(0.0F, 0.4F, 0.0F);
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) MathHelper.lerp((double)f, spawnerEntity.getLastRotation(), spawnerEntity.getRotation()) * 10.0F));
            matrixStack.translate(0.0F, -0.2F, 0.0F);
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-30.0F));
            matrixStack.scale(g, g, g);
            this.entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, f, matrixStack, vertexConsumerProvider, i);
        }

        matrixStack.pop();
    }
}