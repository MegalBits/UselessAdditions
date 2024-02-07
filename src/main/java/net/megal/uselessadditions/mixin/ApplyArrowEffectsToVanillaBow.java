package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.item.base.UBowItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public abstract class ApplyArrowEffectsToVanillaBow {
    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V",
                shift = At.Shift.BEFORE
            ), method = "onStoppedUsing")
    private void applyEffects(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci, @Local PersistentProjectileEntity projectile) {
        UBowItem.applyArrowEffects(projectile, stack, world, user);
    }
}
