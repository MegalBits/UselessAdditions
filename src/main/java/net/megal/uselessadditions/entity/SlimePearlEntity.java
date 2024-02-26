package net.megal.uselessadditions.entity;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.item.base.UItem;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class SlimePearlEntity extends UPearlEntity {
    private boolean canBounce;

    public SlimePearlEntity(EntityType<? extends SlimePearlEntity> entityType, World world) {
        super(entityType, world);
        canBounce = true;
    }

    public SlimePearlEntity(World world, LivingEntity owner) {
        super(UEntities.SLIME_PEARL, world, owner);
        canBounce = true;
    }

    @Override
    protected Item getDefaultItem() {
        return UItems.SLIME_PEARL;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (hitResult.getType() != HitResult.Type.BLOCK || !canBounce) super.onCollision(hitResult);
    }

    @Override
    public void tick() {
        if (canBounce) {
            BlockHitResult blockHitResult = getCollision(this);

            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                Direction.Axis axis = blockHitResult.getSide().getAxis();
                Vec3d vel = getVelocity();

                setVelocity(vel.getX() * (axis == Direction.Axis.X ? -0.75f : 1), vel.getY() * (axis == Direction.Axis.Y ? -0.75f : 1), vel.getZ() * (axis == Direction.Axis.Z ? -0.75f : 1));

                canBounce = false;
            }
        }
        super.tick();
    }

    public static BlockHitResult getCollision(Entity entity) {
        World world = entity.getWorld();
        Vec3d vel = entity.getVelocity();
        Vec3d pos = entity.getPos();

        Vec3d end = entity.getPos().add(vel);

        return world.raycast(new RaycastContext(pos, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("bounce", canBounce);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        canBounce = nbt.getBoolean("bounce");
    }
}
