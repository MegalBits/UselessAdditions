package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerPlayerEntity.class)
public class DontDeleteSoulBoundItems {
    @Inject(at = @At("TAIL"),
            method = "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V")
    private void copyItems(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (!alive) {
            ServerPlayerEntity player = ((ServerPlayerEntity)(Object)this);
            PlayerInventory inventory = player.getInventory();
            PlayerInventory oldInventory = oldPlayer.getInventory();
            for(int i = 0; i < inventory.size(); ++i) {
                ItemStack stack = oldInventory.getStack(i);
                if (EnchantmentHelper.getLevel(UEnchantments.SOUL_BOUND, stack) > 0) inventory.setStack(i, stack);
            }
        }
    }
}
