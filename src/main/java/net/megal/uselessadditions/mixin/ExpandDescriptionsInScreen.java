package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.UAddClient;
import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public abstract class ExpandDescriptionsInScreen {
    private int i = 0;

    @Inject(at = @At("HEAD"),
            method = "tick()V",
            cancellable = false)
    private void tick(CallbackInfo ci) {
        if (i > 0) i -= 1;
        else UAdd.expandDescriptions = false;
    }
    @Inject(at = @At("HEAD"),
            method = "keyPressed(III)Z",
            cancellable = false)
    private void handleHotbarKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (UAddClient.EXPAND_TOOLTIP.matchesKey(keyCode, scanCode)) {
            UAdd.expandDescriptions = true;
            i = Math.max(i > 0 ? 2 : 8, i);
        }
    }



}