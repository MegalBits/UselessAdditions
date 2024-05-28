package net.megal.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.megal.UAdd;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class UEntityRendering {
    private static final Dilation OUTER_DILATION = new Dilation(1.0F);
    private static final Dilation INNER_DILATION = new Dilation(0.5F);

    public static final EntityModelLayer SKELETON_ROGUE = createMain( "skeleton_rogue");
    public static final EntityModelLayer SKELETON_ROGUE_INNER_LAYER = createInner("skeleton_rogue");
    public static final EntityModelLayer SKELETON_ROGUE_OUTER_LAYER = createOuter("skeleton_rogue");

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(UEntities.SKELETON_ROGUE, SkeletonRogueEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SKELETON_ROGUE, UEntityRendering::getSkeletonModelData);
        EntityModelLayerRegistry.registerModelLayer(SKELETON_ROGUE_INNER_LAYER, UEntityRendering::getInnerArmorModelData);
        EntityModelLayerRegistry.registerModelLayer(SKELETON_ROGUE_OUTER_LAYER, UEntityRendering::getOuterArmorModelData);
    }

    public static TexturedModelData getBipedModelData() {
        return TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0.0F), 64, 64);
    }

    public static TexturedModelData getSkeletonModelData() {
        return SkeletonEntityModel.getTexturedModelData();
    }

    public static TexturedModelData getInnerArmorModelData() {
        return TexturedModelData.of(ArmorEntityModel.getModelData(INNER_DILATION), 64, 32);
    }

    public static TexturedModelData getOuterArmorModelData() {
        return TexturedModelData.of(ArmorEntityModel.getModelData(OUTER_DILATION), 64, 32);
    }


    private static EntityModelLayer createLayer(String id, String name) {
        return new EntityModelLayer(new Identifier(UAdd.ID, id), name);
    }

    private static EntityModelLayer createMain(String id) {
        return createLayer(id, "main");
    }

    public static EntityModelLayer createInner(String id) {
        return createLayer(id, "inner_armor");
    }

    public static EntityModelLayer createOuter(String id) {
        return createLayer(id, "outer_armor");
    }
}
