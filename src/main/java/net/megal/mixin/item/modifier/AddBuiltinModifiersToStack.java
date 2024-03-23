package net.megal.mixin.item.modifier;

import net.megal.item.UItem;
import net.megal.item.modifier.Modifiers;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class AddBuiltinModifiersToStack {
    @Inject(
            at = @At("TAIL"),
            method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V"
    )
    private void addBuiltinModifiersToNbt(ItemConvertible item, int count, CallbackInfo ci) {
        ItemStack stack = (ItemStack)(Object)this;
        Modifiers.setModifiers(stack, ((UItem)item.asItem()).UAdd$getModifiers().toArray(new String[0]));
    }
}
