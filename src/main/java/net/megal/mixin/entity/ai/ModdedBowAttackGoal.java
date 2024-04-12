package net.megal.mixin.entity.ai;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.megal.item.UBowItem;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(BowAttackGoal.class)
public abstract class ModdedBowAttackGoal<T extends HostileEntity & RangedAttackMob> {
    @Shadow @Final private T actor;

    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "isHoldingBow"
    )
    private boolean allowModdedBows(boolean original) {
        return original || actor.isHolding(stack -> stack.getItem() instanceof BowItem);
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/HostileEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"
            ),
            method = "tick"
    )
    private Hand getHandWithBow(Hand original) {
        return actor.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    @ModifyExpressionValue(
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=20"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;getItemUseTime()I"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;clearActiveItem()V", ordinal = 1)
            ),
            method = "tick"
    )
    private int setRequiredUseTicks(int original) {
        return actor.getActiveItem().getItem() instanceof UBowItem uBowItem ? MathHelper.floor(uBowItem.useTime * 20) : MathHelper.floor(UBowItem.WoodenBowUseTime);
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ai/RangedAttackMob;shootAt(Lnet/minecraft/entity/LivingEntity;F)V"
            ),
            method = "tick",
            index = 1
    )
    private float getModdedUseTicks(float pullProgress, @Local(ordinal = 0) int i) {
        return UBowItem.getUseTime(i, actor.getActiveItem().getItem() instanceof UBowItem uBowItem ? uBowItem.useTime : UBowItem.WoodenBowUseTime);
    }
}
