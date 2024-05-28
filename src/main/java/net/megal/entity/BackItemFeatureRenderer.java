package net.megal.entity;

import net.megal.UAdd;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;

public class BackItemFeatureRenderer<T extends MobEntity & HasSecondaryStack, M extends EntityModel<T> & ModelWithArms> extends FeatureRenderer<T, M> {
    public final HeldItemRenderer heldItemRenderer;

    public BackItemFeatureRenderer(FeatureRendererContext<T, M> context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getSecondaryStack() != null) {
            matrices.push();
            matrices.translate(0f, 0.4f, 0.15f);

            heldItemRenderer.renderItem(
                    entity,
                    entity.getSecondaryStack(),
                    ModelTransformationMode.FIXED,
                    false,
                    matrices,
                    vertexConsumers,
                    light
            );
            matrices.pop();
        }
    }
}
