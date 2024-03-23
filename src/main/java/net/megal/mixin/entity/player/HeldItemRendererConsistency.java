package net.megal.mixin.entity.player;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererConsistency {
    @ModifyVariable(
            at = @At("STORE"),
            method = "getHandRenderType",
            ordinal = 0
    )
    private static boolean allowModdedBows(boolean original, ClientPlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();

        return original || (itemStack.getItem() instanceof BowItem || itemStack2.getItem() instanceof BowItem);
    }

    @WrapOperation(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            ),
            method = "getUsingItemHandRenderType"
    )
    private static boolean allowModdedBows(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) && (item instanceof BowItem);
    }
}
