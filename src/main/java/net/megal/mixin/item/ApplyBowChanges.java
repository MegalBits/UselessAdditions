package net.megal.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.megal.item.HasProjectileStats;
import net.megal.item.UBowItem;
import net.megal.item.modifier.Modifiers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BowItem.class)
public abstract class ApplyBowChanges {
    @ModifyArgs(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V"
            ),
            method = "onStoppedUsing"
    )
    private void setVelocity(Args args, @Local(ordinal = 0) float f, @Local(ordinal = 0) ArrowItem arrow) {
        BowItem bow = (BowItem)(Object)this;
        float arrowVelocityMultiplier = 1f;

        if (arrow instanceof HasProjectileStats projectileStats) {
            arrowVelocityMultiplier = projectileStats.getVelocityMultiplier();
        }

        if (bow instanceof UBowItem uBow) {
            args.set(4, f * uBow.speed * arrowVelocityMultiplier);
            args.set(5, uBow.accuracy);
        }
        else if (bow == Items.BOW) {
            args.set(4, f * arrowVelocityMultiplier);  // Speed
            args.set(5, 2f); // Accuracy
        }
    }

    @ModifyVariable(
            at = @At("STORE"),
            method = "onStoppedUsing",
            ordinal = 0
    )
    private float getCustomUseTime(float pullProgress, @Local(ordinal = 1) int i) {
        BowItem bow = (BowItem)(Object)this;
        if (bow instanceof UBowItem uBow) {
            pullProgress = getUseTime(i, uBow.useTime);
        }
        else if (bow == Items.BOW) {
            pullProgress = getUseTime(i, 2f);
        }

        return pullProgress;
    }

    @Inject(
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V",
                shift = At.Shift.BEFORE
            ),
            method = "onStoppedUsing"
    )
    private void applyArrowEffects(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci, @Local PersistentProjectileEntity projectile) {
        if (Modifiers.getModifiersFromStack(stack).contains(Modifiers.AUTO_SMELTING)) projectile.setOnFireFor(100);
    }

    @Unique
    private static float getUseTime(int useTicks, float useTime) {
        float f = (float)useTicks / (useTime * 20f);
        f = (f * f + f * 2.0f) / 3.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }

        return f;
    }
}
