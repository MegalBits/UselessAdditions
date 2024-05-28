package net.megal.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

public class SkeletonRogueEntityRenderer extends SkeletonEntityRenderer<SkeletonRogueEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/skeleton/skeleton.png");
    //private static final Identifier OVERLAY_TEXTURE = new Identifier("textures/entity/skeleton/bogged_overlay.png");

    public SkeletonRogueEntityRenderer(EntityRendererFactory.Context context) {
        super(context, UEntityRendering.SKELETON_ROGUE_INNER_LAYER, UEntityRendering.SKELETON_ROGUE_OUTER_LAYER, new SkeletonEntityModel<>(context.getPart(UEntityRendering.SKELETON_ROGUE)));
        this.addFeature(new BackItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(SkeletonRogueEntity rogue) {
        return TEXTURE;
    }
}
