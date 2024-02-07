package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.TwoHanded;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.megal.uselessadditions.item.SpecialEffects.*;

@Mixin(PlayerEntity.class)
public abstract class AttackDamageModifier {
    @Inject(at = @At(value = "INVOKE",
                target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I",
                shift = At.Shift.BEFORE
            ),
            method = "attack(Lnet/minecraft/entity/Entity;)V")
    private void addDamage(Entity target, CallbackInfo ci, @Local(ordinal = 0) LocalFloatRef dmg, @Local(ordinal = 2) float h) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        float f = 0;

        ItemStack stack = player.getMainHandStack();
        if (UItemHelper.getEffects(stack).contains(SpecialEffects.CRYSTALLIZED_XP)) f += Math.min(player.experienceLevel, 80) * CXP_DAMAGE;
        dmg.set(dmg.get() + (f * h));

        if (stack.getItem() instanceof TwoHanded && !player.getOffHandStack().isEmpty()) dmg.set(dmg.get() * 0.05f);
    }
}
