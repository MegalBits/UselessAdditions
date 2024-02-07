package net.megal.uselessadditions.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.item.WarpPearl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class CustomItemPoseRendering {
    @Shadow @Final private MinecraftClient client;

    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/render/item/HeldItemRenderer;applyEquipOffset(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/Arm;F)V",
                ordinal = 2,
                shift = At.Shift.AFTER
            ),
            method = "renderFirstPersonItem")
    private void setSpecialPose(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci,
                                @Local(ordinal = 0) int l, @Local(ordinal = 0) LocalFloatRef f2, @Local(ordinal = 1) LocalFloatRef g2, @Local(ordinal = 2) LocalFloatRef h2, @Local(ordinal = 3) LocalFloatRef j2) {
        HeldItemRenderer heldItemRenderer = ((HeldItemRenderer)(Object)this);
        float m;
        float f = f2.get();
        float g = g2.get();
        float h = h2.get();
        float j = j2.get();

        UAdd.LOGGER.info("f: " + f + " g: " + g + " h: " + h + " j: " + j);

        if (player.getStackInHand(hand).getItem() instanceof WarpPearl && !player.hasStatusEffect(UStatusEffects.DESTABILIZED)) {
            UAdd.LOGGER.info("didIt");
            matrices.translate((float)l * -0.2785682F, 0.18344387F, 0.15731531F);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-13.935F));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)l * 35.3F));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)l * -9.785F));
            m = (float)item.getMaxUseTime() - ((float)client.player.getItemUseTimeLeft() - tickDelta + 1.0F);
            f = m / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if (f > 1.0F) {
                f = 1.0F;
            }

            if (f > 0.1F) {
                g = MathHelper.sin((m - 0.1F) * 1.3F);
                h = f - 0.1F;
                j = g * h;
                matrices.translate(j * 0.0F, j * 0.004F, j * 0.0F);
            }

            matrices.translate(f * 0.0F, f * 0.0F, f * 0.04F);
            matrices.scale(1.0F, 1.0F, 1.0F + f * 0.2F);
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees((float)l * 45.0F));
        }

        UAdd.LOGGER.info("f: " + f + " g: " + g + " h: " + h + " j: " + j);

        f2.set(f);
        g2.set(g);
        h2.set(h);
        j2.set(j);
    }

}
