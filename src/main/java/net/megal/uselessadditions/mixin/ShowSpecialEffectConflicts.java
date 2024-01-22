package net.megal.uselessadditions.mixin;

import com.google.common.primitives.Ints;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(EnchantmentScreen.class)
public abstract class ShowSpecialEffectConflicts extends HandledScreen<EnchantmentScreenHandler> {
    private static final Identifier TEXTURE2 = new Identifier(UAdd.MOD_ID, "textures/gui/container/modified_enchanting.png");

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"),
            method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V")
    private Identifier getBackgroundTexture(Identifier texture, @Local(ordinal = 3) int l) {
        return isCompatible(((EnchantmentScreenHandler) handler), l) ? texture : TEXTURE2;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWrapped(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/StringVisitable;IIII)V"),
            method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", index = 5)
    private int getTextColor(int color, @Local(ordinal = 3) int l) {
        return isCompatible(((EnchantmentScreenHandler) handler), l) ? color : 0x352235;
    }

    private static boolean isCompatible(EnchantmentScreenHandler screenHandler, int index) {
        ItemStack stack = screenHandler.inventory.getStack(0);
        for (String s : UItemHelper.getEffects(stack)) {
            @Nullable Enchantment enchantment = Enchantment.byRawId(screenHandler.enchantmentId[index]);
            if (enchantment != null && SpecialEffects.effects.get(s).enchantments.contains(enchantment)) {
                return false;
            }
        }
        return true;
    }

    public ShowSpecialEffectConflicts(EnchantmentScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
}
