package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.List;

@Mixin(PlayerInventory.class)
public class DontDropSoulBoundItems {
    @WrapWithCondition(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"
    ), method = "dropAll()V")
    private boolean dontDrop(PlayerEntity player, ItemStack stack, boolean throwRandomly, boolean retainOwnership) {
        return !(EnchantmentHelper.getLevel(UEnchantments.SOUL_BOUND, stack) > 0);
    }

    @WrapWithCondition(at = @At(
            value = "INVOKE",
            target = "Ljava/util/List;set(ILjava/lang/Object;)Ljava/lang/Object;"
    ), method = "dropAll()V")
    private boolean dontDelete(List list, int index, Object object, @Local(ordinal = 0) ItemStack stack) {
        return !(EnchantmentHelper.getLevel(UEnchantments.SOUL_BOUND, stack) > 0);
    }
}
