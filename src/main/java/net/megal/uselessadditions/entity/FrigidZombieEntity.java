package net.megal.uselessadditions.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class FrigidZombieEntity extends ZombieEntity {
	public FrigidZombieEntity(EntityType<? extends FrigidZombieEntity> entityType, World world) {
		super(entityType, world);
	}

	public static boolean canSpawn(EntityType<FrigidZombieEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		return canSpawnInDark(type, world, spawnReason, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(pos));
	}

	protected boolean burnsInDaylight() {
		return true;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_ZOMBIE_STEP;
	}

	public boolean tryAttack(Entity target) {
		boolean bl = super.tryAttack(target);
		if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
			float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
			((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 140 * (int)f), this);
		}

		return bl;
	}

	protected boolean canConvertInWater() {
		return true;
	}

	protected void convertInWater() {
		this.convertTo(EntityType.DROWNED);
		if (!this.isSilent()) {
			this.getWorld().syncWorldEvent(null, WorldEvents.ZOMBIE_CONVERTS_TO_DROWNED, this.getBlockPos(), 0);
		}
	}

	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}
}
