package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class CantCombineAugment extends ForgingScreenHandler {
    public CantCombineAugment(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }
    @Inject(at = @At("HEAD"),
            method = "canTakeOutput(Lnet/minecraft/entity/player/PlayerEntity;Z)Z",
            cancellable = true)
    private void canTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
        //AnvilScreenHandler anvil = ((AnvilScreenHandler)(Object)this);
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(input.getStack(1));
        for (Enchantment ench : enchantments.keySet()) {
            if (ench instanceof AugmentEnchantment) {
                output.setStack(0, ItemStack.EMPTY);
                cir.setReturnValue(false);
            }
        }
    }
}