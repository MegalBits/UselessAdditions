package net.megal.uselessadditions.client.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.PipeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class PipeEntityRenderer implements BlockEntityRenderer<PipeEntity> {
    private final ItemRenderer itemRenderer;
    public PipeEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(PipeEntity pipeEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = pipeEntity.getWorld();
        if (world == null) return;
        for (PipeEntity.StoredItemStack storedStack : pipeEntity.storedStacks) {
            matrices.push();

            Vec3d offset = calculateOffset(storedStack.direction, storedStack).multiply(-0.5f);
            matrices.translate(offset.getX() + 0.5f, offset.getY() + 0.5f, offset.getZ() + 0.5f);

            matrices.scale(0.25f, 0.25f, 0.25f);

            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(storedStack.rotation));

            itemRenderer.renderItem(storedStack.stack, ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, world, 0);
            matrices.pop();
        }
    }

    public Vec3d calculateOffset(@Nullable Direction direction, PipeEntity.StoredItemStack storedStack) {
        if (storedStack.getTime() < 10) {
            float progress = 10 - storedStack.getTime();
            return storedStack.initialPos.multiply(progress / 10);
        }

        Vec3d offset = Vec3d.ZERO;
        if (direction == null) return offset;

        switch (direction) {
            case UP -> offset = new Vec3d(0, -1, 0);
            case DOWN -> offset = new Vec3d(0, 1, 0);
            case NORTH -> offset = new Vec3d(0, 0, 1);
            case EAST -> offset = new Vec3d(-1, 0, 0);
            case SOUTH -> offset = new Vec3d(0, 0, -1);
            case WEST -> offset = new Vec3d(1, 0, 0);
        }

        float progress = storedStack.getTime() - 10;
        return offset.multiply(progress / 10);
    }
}
