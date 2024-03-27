package net.megal.mixin.item.modifier;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.item.modifier.Modifiers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Enchantment.class)
public abstract class ApplyModifierAnvilIncompatibilities {
    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z"
    )
    private boolean applyIncompatibility(boolean b, ItemStack stack) {
        Enchantment enchantment = ((Enchantment)(Object)this);

        List<String> modifiers = Modifiers.getModifiersFromStack(stack);
        for (String modifier : modifiers) {
            if (!Modifiers.getModifier(modifier).isCompatibleWith(enchantment)) return false;
        }

        return b;
    }
}
