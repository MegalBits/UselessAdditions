package net.megal.uselessadditions.client.entity.render;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.client.entity.UEntityRendering;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.StrayEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class FrigidZombieRenderer extends ZombieEntityRenderer {
    public FrigidZombieRenderer(EntityRendererFactory.Context context) {
        super(context, UEntityRendering.FRIGID_ZOMBIE, UEntityRendering.FRIGID_ZOMBIE_INNER, UEntityRendering.FRIGID_ZOMBIE_OUTER);
    }

    public Identifier getTexture(ZombieEntity zombieEntity) {
        return new Identifier(UAdd.MOD_ID, "textures/entity/zombie/frigid_zombie.png");
    }
}
