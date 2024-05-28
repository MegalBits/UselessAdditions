package net.megal.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PullingFishingBobberEntity extends ProjectileEntity {
    public PullingFishingBobberEntity(EntityType<? extends PullingFishingBobberEntity> type, World world) {
        super(type, world);
        this.ignoreCameraFrustum = true;
    }

    public PullingFishingBobberEntity(MobEntity thrower, World world) {
        this(UEntities.PULLING_FISHING_BOBBER, world);
        this.setOwner(thrower);
        float f = thrower.getPitch();
        float g = thrower.getYaw();
        float h = MathHelper.cos(-g * (float) (Math.PI / 180.0) - (float) Math.PI);
        float i = MathHelper.sin(-g * (float) (Math.PI / 180.0) - (float) Math.PI);
        float j = -MathHelper.cos(-f * (float) (Math.PI / 180.0));
        float k = MathHelper.sin(-f * (float) (Math.PI / 180.0));
        double d = thrower.getX() - (double)i * 0.3;
        double e = thrower.getEyeY();
        double l = thrower.getZ() - (double)h * 0.3;
        this.refreshPositionAndAngles(d, e, l, g, f);
        Vec3d vec3d = new Vec3d(-i, MathHelper.clamp(-(k / j), -5.0F, 5.0F), -h);
        double m = vec3d.length();
        vec3d = vec3d.multiply(
                0.6 / m + this.random.nextTriangular(0.5, 0.0103365),
                0.6 / m + this.random.nextTriangular(0.5, 0.0103365),
                0.6 / m + this.random.nextTriangular(0.5, 0.0103365)
        );
        this.setVelocity(vec3d);
        this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 180.0F / (float)Math.PI));
        this.setPitch((float)(MathHelper.atan2(vec3d.y, vec3d.horizontalLength()) * 180.0F / (float)Math.PI));
        this.prevYaw = this.getYaw();
        this.prevPitch = this.getPitch();
    }
}
