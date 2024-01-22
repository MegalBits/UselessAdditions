package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class EverlastingDisablesDespawn {
    @WrapWithCondition(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/ItemEntity;discard()V"
    ), method = "tick()V")
    private boolean hasEverlasting(ItemEntity itemEntity) {
        return !(EnchantmentHelper.getLevel(UEnchantments.EVERLASTING, itemEntity.getStack()) > 0);
    }
}
