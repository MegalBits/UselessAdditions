package net.megal.entity;

import net.megal.network.UNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SkeletonRogueEntity extends AbstractSkeletonEntity implements HasSecondaryStack {
    private static final TrackedData<ItemStack> SECONDARY_STACK = DataTracker.registerData(SkeletonRogueEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private static final TrackedData<Boolean> HOLDING_SECONDARY_STACK = DataTracker.registerData(SkeletonRogueEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final float meleeDistance = 8f;
    public static final int swapCooldown = 60;

    public ItemStack secondaryStack;
    public boolean holdingSecondaryStack;
    public int swapCooldownTicks;

    public SkeletonRogueEntity(EntityType<? extends SkeletonRogueEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected static DefaultAttributeContainer.Builder createSkeletonRogueAttributes() {
        return AbstractSkeletonEntity.createAbstractSkeletonAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3d);
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient()) {
            if (swapCooldownTicks > 0) swapCooldownTicks--;

            LivingEntity target = getTarget();
            if (target != null) {
                boolean shouldUseMelee = !holdingSecondaryStack && getPos().distanceTo(target.getPos()) < meleeDistance;
                boolean shouldUseRanged = holdingSecondaryStack && getPos().distanceTo(target.getPos()) > meleeDistance;
                if (swapCooldownTicks == 0 && (shouldUseMelee || shouldUseRanged)) {
                    swapStack();
                }
            }
        }
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
        UNetworking.syncSecondaryStack(this);

        return data;
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        super.initEquipment(random, localDifficulty);
        secondaryStack = new ItemStack(Items.IRON_SWORD);
    }

    @Override
    protected void updateEnchantments(Random random, LocalDifficulty localDifficulty) {
        super.updateEnchantments(random, localDifficulty);
        float difficulty = localDifficulty.getClampedLocalDifficulty();
        if (random.nextFloat() < 0.125F * difficulty) {
            secondaryStack = EnchantmentHelper.enchant(this.getWorld().getEnabledFeatures(), random, secondaryStack, (int)(5f + difficulty * (float)random.nextInt(18)), false);
        }
    }

    @Override
    public ItemStack getSecondaryStack() {
        return secondaryStack;
    }

    @Override
    public void setSecondaryStack(ItemStack stack) {
        secondaryStack = stack;

        if (!getWorld().isClient()) {
            UNetworking.syncSecondaryStack(this);
        }
    }

    @Override
    public void swapStack() {
        HasSecondaryStack.swapStack(this);

        holdingSecondaryStack = !holdingSecondaryStack;

        swapCooldownTicks = swapCooldown;

        updateAttackType();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SECONDARY_STACK, ItemStack.EMPTY);
        builder.add(HOLDING_SECONDARY_STACK, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (secondaryStack != null && !secondaryStack.equals(ItemStack.EMPTY)) {
            nbt.put("secondary_stack", secondaryStack.encode(this.getRegistryManager()));
        }
        nbt.putBoolean("holding_secondary_stack", holdingSecondaryStack);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        secondaryStack = ItemStack.fromNbt(this.getRegistryManager(), nbt.get("secondary_stack")).orElse(ItemStack.EMPTY);
        holdingSecondaryStack = nbt.getBoolean("holding_secondary_stack");
    }
}
