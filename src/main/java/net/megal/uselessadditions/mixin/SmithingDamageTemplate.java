package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.DamageableItem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SmithingScreenHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.megal.uselessadditions.UAdd.*;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingDamageTemplate extends ForgingScreenHandler {
    public SmithingDamageTemplate(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }
    @Inject(at = @At("HEAD"),
            method = "decrementStack(I)V",
            cancellable = true)
    private void decrementStack(int slot, CallbackInfo ci) {
        if (slot == 0) {
            ItemStack stack = input.getStack(slot);
            if((stack.getItem() instanceof DamageableItem || (stack.isIn(HAMMERS) && stack.isDamageable())) && stack.getDamage() <= stack.getMaxDamage() - 2) {
                stack.setDamage(stack.getDamage() + 1);
                ci.cancel();
            }
        }
    }
}