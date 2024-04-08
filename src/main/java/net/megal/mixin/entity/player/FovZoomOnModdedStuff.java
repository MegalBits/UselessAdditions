package net.megal.mixin.entity.player;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.item.HasFovZoom;
import net.megal.item.UBowItem;
import net.megal.item.WarpPearl;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class FovZoomOnModdedStuff {
    @WrapOperation(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            ),
            method = "getFovMultiplier"
    )
    private boolean allowModdedBows(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || instance.getItem() instanceof BowItem || instance.getItem() instanceof HasFovZoom;
    }

    @ModifyConstant(
            method = "getFovMultiplier",
            constant = @Constant(floatValue = 20)
    )
    private float changeTimeForZoom(float constant, @Local ItemStack stack) {
        Item item = stack.getItem();
        if (stack.isOf(Items.BOW)) return 40;
        if (item instanceof UBowItem bow) return bow.useTime * 20;
        if (item instanceof HasFovZoom) return item.getMaxUseTime(stack);

        return constant;
    }
}
