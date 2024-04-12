package net.megal.mixin.entity.skeleton;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.megal.entity.RangedMob;
import net.megal.item.HasProjectileStats;
import net.megal.item.UBowItem;
import net.megal.item.UItems;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(AbstractSkeletonEntity.class)
public abstract class ModifySkeletonBows {
    @Unique
    private static final float arrowSpeedMultiplier = 1.2f;
    @Unique
    private static final List<Item> arrowTypes = List.of(
            Items.ARROW,
            UItems.IRON_ARROW,
            UItems.GOLD_ARROW,
            UItems.DIAMOND_ARROW
    );
    @Unique
    private static final List<Item> bowTypes = List.of(
            Items.BOW,
            UItems.STONE_BOW,
            UItems.IRON_BOW,
            UItems.GOLDEN_BOW,
            UItems.DIAMOND_BOW
    );

    @Inject(
            at = @At("HEAD"),
            method = "initialize"
    )
    private void initializeArrowType(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        AbstractSkeletonEntity skeleton = ((AbstractSkeletonEntity)(Object)this);
        Random random = world.getRandom();

        ((RangedMob)skeleton).UAdd$setProjectile(arrowTypes.get(random.nextInt(arrowTypes.size())));
    }

    @ModifyExpressionValue(
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/Items;BOW:Lnet/minecraft/item/Item;"
            ),
            method = "initEquipment"
    )
    private Item initializeBowType(Item original, Random random, LocalDifficulty localDifficulty) {
        return bowTypes.get(random.nextInt(bowTypes.size()));
    }

    @ModifyVariable(
            at = @At("STORE"),
            method = "updateAttackType",
            ordinal = 0
    )
    private ItemStack getModdedBows(ItemStack original) {
        return getRangedWeaponStack();
    }

    @WrapOperation(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            ),
            method = "updateAttackType"
    )
    private boolean allowModdedBows(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || instance.getItem() instanceof BowItem;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ai/goal/BowAttackGoal;setAttackInterval(I)V"
            ),
            method = "updateAttackType"
    )
    private int modifyAttackInterval(int attackInterval) {
        float bowUseTime = UBowItem.WoodenBowUseTime;
        ItemStack stack = getRangedWeaponStack();
        if (stack.getItem() instanceof UBowItem uBowItem) {
            bowUseTime = uBowItem.useTime;
        }
        return MathHelper.floor(attackInterval * bowUseTime);
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/AbstractSkeletonEntity;getProjectileType(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"
            ),
            method = "shootAt"
    )
    private ItemStack getProjectileFromModdedBows(ItemStack original) {
        return getRangedWeaponStack();
    }

    @ModifyArgs(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(DDDFF)V"
            ),
            method = "shootAt"
    )
    private void changeArrowStats(Args args, @Local(ordinal = 0) ItemStack arrow) {
        Item bow = getRangedWeaponStack().getItem();
        float arrowVelocityMultiplier = 1f;

        if (arrow.getItem() instanceof HasProjectileStats projectileStats) {
            arrowVelocityMultiplier = projectileStats.getVelocityMultiplier();
        }

        if (bow instanceof UBowItem uBow) {
            args.set(3, uBow.speed * arrowVelocityMultiplier * arrowSpeedMultiplier);
            args.set(4, uBow.accuracy);
        }
        else if (bow == Items.BOW) {
            args.set(3, arrowVelocityMultiplier * arrowSpeedMultiplier);
            args.set(4, UBowItem.WoodenBowUseTime);
        }
    }

    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "canUseRangedWeapon"
    )
    private boolean allowModdedBows(boolean original, RangedWeaponItem weapon) {
        return original || weapon instanceof BowItem;
    }

    @Unique
    private ItemStack getRangedWeaponStack() {
        AbstractSkeletonEntity skeleton = ((AbstractSkeletonEntity)(Object)this);
        return skeleton.getStackInHand(skeleton.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND);
    }
}
