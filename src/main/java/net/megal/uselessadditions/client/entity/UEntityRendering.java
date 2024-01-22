package net.megal.uselessadditions.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.client.entity.render.FrigidZombieRenderer;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class UEntityRendering {
    public static final EntityModelLayer FRIGID_ZOMBIE = createMain(new Identifier(UAdd.MOD_ID, "frigid_zombie"));
    public static final EntityModelLayer FRIGID_ZOMBIE_INNER = createInner(new Identifier(UAdd.MOD_ID, "frigid_zombie"));
    public static final EntityModelLayer FRIGID_ZOMBIE_OUTER = createOuter(new Identifier(UAdd.MOD_ID, "frigid_zombie"));
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(net.megal.uselessadditions.entity.UEntities.FRIGID_ZOMBIE, FrigidZombieRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(FRIGID_ZOMBIE, UEntityRendering::getZombieModelData);
        EntityModelLayerRegistry.registerModelLayer(FRIGID_ZOMBIE_INNER, UEntityRendering::getZombieModelData);
        EntityModelLayerRegistry.registerModelLayer(FRIGID_ZOMBIE_OUTER, UEntityRendering::getZombieModelData);
    }

    public static TexturedModelData getZombieModelData() {
        return TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0.0F), 64, 64);
    }

    private static EntityModelLayer createLayer(Identifier id, String name) {
        return new EntityModelLayer(id, name);
    }

    private static EntityModelLayer createMain(Identifier id) {
        return createLayer(id, "main");
    }

    public static EntityModelLayer createInner(Identifier id) {
        return createLayer(id, "inner_armor");
    }

    public static EntityModelLayer createOuter(Identifier id) {
        return createLayer(id, "outer_armor");
    }


}
