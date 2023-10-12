package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.item.DamageableItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SmithingScreenHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.megal.uselessadditions.UAdd.HAMMERS;

@Mixin(Item.class)
public abstract class NoGlintForCurses {
    @Overwrite
    public boolean hasGlint(ItemStack stack) {
        for (Enchantment enchantment : EnchantmentHelper.get(stack).keySet()) {
            if (!enchantment.isCursed()) return true;
        }
        return false;
    }
}