package net.megal.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FishermanZombie extends ZombieEntity implements FishingAttackMob {
    public static final float meleeDistance = 5f;
    public static final int swapCooldown = 20;

    private final ZombieAttackGoal meleeAttackGoal = new ZombieAttackGoal(this, 1.0, false);
    private final ZombieAttackGoal rodAttackGoal = new ZombieAttackGoal(this, 1.0, false);

    public int swapCooldownTicks;
    public boolean isMelee = true;

    public FishermanZombie(EntityType<? extends FishermanZombie> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();

    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData);

        updateAttackType();

        return entityData;
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient()) {
            if (swapCooldownTicks > 0) swapCooldownTicks--;

            LivingEntity target = getTarget();
            if (target != null) {
                boolean shouldUseMelee = !isMelee && getPos().distanceTo(target.getPos()) < meleeDistance;
                boolean shouldUseRanged = isMelee && getPos().distanceTo(target.getPos()) > meleeDistance;
                if (swapCooldownTicks == 0 && (shouldUseMelee || shouldUseRanged)) {
                    swapCooldownTicks = swapCooldown;
                    updateAttackType();
                }
            }
        }
    }

    public void updateAttackType() {
        if (this.getWorld() != null && !this.getWorld().isClient) {
            this.goalSelector.remove(meleeAttackGoal);
            this.goalSelector.remove(rodAttackGoal);
            ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.FISHING_ROD));
            if (itemStack.isOf(Items.FISHING_ROD) && !isMelee) {
                this.goalSelector.add(2, rodAttackGoal);
            }
            else {
                this.goalSelector.add(2, meleeAttackGoal);
            }
        }
    }

    @Override
    public void throwAt(LivingEntity target, float pullProgress) {
        FishingBobberEntity fishingBobberEntity = FishingRo
        PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack, pullProgress);
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(persistentProjectileEntity);
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        super.equipStack(slot, stack);
        if (!this.getWorld().isClient) {
            this.updateAttackType();
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("isMelee", isMelee);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        isMelee = nbt.getBoolean("isMelee");
        updateAttackType();
    }
}
