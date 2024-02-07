package net.megal.uselessadditions.mixin.client;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.item.WarpPearl;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class CancelItemUsage {
    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/util/UseAction;"
            ),
            method = "getArmPose",
            cancellable = true)
    private static void setNoPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        //if (player.getItemCooldownManager().isCoolingDown(player.getStackInHand(hand).getItem())) cir.setReturnValue(BipedEntityModel.ArmPose.EMPTY);
        if (player.hasStatusEffect(UStatusEffects.DESTABILIZED) && player.getStackInHand(hand).getItem() instanceof WarpPearl) cir.setReturnValue(BipedEntityModel.ArmPose.EMPTY);
    }
}
