package net.megal.mixin.item.modifier;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.megal.item.modifier.Modifiers;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(EnchantmentScreen.class)
public abstract class ShowModifierEnchantingIncompatibilities extends HandledScreen<EnchantmentScreenHandler> {
    @Unique
    private static final Identifier altSlotTexture = new Identifier(UAdd.ID, "container/enchanting_table/enchantment_slot");

    @Unique
    private static final Identifier altDisabledSlotTexture = new Identifier(UAdd.ID, "container/enchanting_table/enchantment_slot_disabled");

    @Unique
    private static final Identifier altLevelTexture = new Identifier(UAdd.ID, "container/enchanting_table/unavailable");

    @Unique
    private static final Identifier altDisabledLevelTexture = new Identifier(UAdd.ID, "container/enchanting_table/unavailable_disabled");

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 0
            ),
            method = "drawBackground"
    )
    private Identifier changeUnpoweredSlotTexture(Identifier texture, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? texture : altDisabledSlotTexture;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 1
            ),
            method = "drawBackground"
    )
    private Identifier changeDisabledSlotTexture(Identifier texture, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? texture : altDisabledSlotTexture;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 2
            ),
            method = "drawBackground"
    )
    private Identifier changeDisabledLevelTexture(Identifier texture, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? texture : altLevelTexture;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 3
            ),
            method = "drawBackground"
    )
    private Identifier changeHighlightedSlotTexture(Identifier texture, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? texture : altSlotTexture;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 4
            ),
            method = "drawBackground"
    )
    private Identifier changeSlotTexture(Identifier texture, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? texture : altSlotTexture;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 5
            ),
            method = "drawBackground"
    )
    private Identifier changeLevelTexture(Identifier texture, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? texture : altDisabledLevelTexture;
    }

    @ModifyArg(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWrapped(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/StringVisitable;IIII)V"),
            method = "drawBackground",
            index = 5
    )
    private int getTextColor(int color, @Local(ordinal = 5) int l) {
        return checkCompatibility((handler), l) ? color : 0x252525;
    }

    @Unique
    private static boolean checkCompatibility(EnchantmentScreenHandler screenHandler, int index) {
        ItemStack stack = screenHandler.inventory.getStack(0);
        for (String modifierId : Modifiers.getModifiersFromStack(stack)) {
            @Nullable Enchantment enchantment = Enchantment.byRawId(screenHandler.enchantmentId[index]);
            if (enchantment != null && !Modifiers.getModifier(modifierId).isCompatibleWith(enchantment)) {
                return false;
            }
        }
        return true;
    }

    private ShowModifierEnchantingIncompatibilities(EnchantmentScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
}
