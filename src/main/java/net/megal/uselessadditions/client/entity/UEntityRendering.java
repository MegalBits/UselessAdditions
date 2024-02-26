package net.megal.uselessadditions.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.client.entity.render.FrigidZombieRenderer;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static net.megal.uselessadditions.entity.UEntities.FRIGID_ZOMBIE;
import static net.megal.uselessadditions.entity.UEntities.SLIME_PEARL;

@Environment(EnvType.CLIENT)
public class UEntityRendering {
    private static final Dilation OUTER_DILATION = new Dilation(1.0F);
    private static final Dilation INNER_DILATION = new Dilation(0.5F);

    public static final EntityModelLayer FRIGID_ZOMBIE_LAYER = createMain(new Identifier(UAdd.MOD_ID, "frigid_zombie"));
    public static final EntityModelLayer FRIGID_ZOMBIE_INNER_LAYER = createInner(new Identifier(UAdd.MOD_ID, "frigid_zombie"));
    public static final EntityModelLayer FRIGID_ZOMBIE_OUTER_LAYER = createOuter(new Identifier(UAdd.MOD_ID, "frigid_zombie"));
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(SLIME_PEARL, FlyingItemEntityRenderer::new);

        EntityRendererRegistry.register(FRIGID_ZOMBIE, FrigidZombieRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(FRIGID_ZOMBIE_LAYER, UEntityRendering::getZombieModelData);
        EntityModelLayerRegistry.registerModelLayer(FRIGID_ZOMBIE_INNER_LAYER, UEntityRendering::getInnerArmorModelData);
        EntityModelLayerRegistry.registerModelLayer(FRIGID_ZOMBIE_OUTER_LAYER, UEntityRendering::getOuterArmorModelData);
    }

    public static TexturedModelData getZombieModelData() {
        return TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0.0F), 64, 64);
    }

    public static TexturedModelData getInnerArmorModelData() {
        return TexturedModelData.of(ArmorEntityModel.getModelData(INNER_DILATION), 64, 32);
    }

    public static TexturedModelData getOuterArmorModelData() {
        return TexturedModelData.of(ArmorEntityModel.getModelData(OUTER_DILATION), 64, 32);
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
