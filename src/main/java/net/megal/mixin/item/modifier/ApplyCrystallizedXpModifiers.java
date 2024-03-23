package net.megal.mixin.item.modifier;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.megal.item.modifier.Modifiers;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

import static net.megal.item.modifier.Modifiers.cxp_damageAmount;
import static net.megal.item.modifier.Modifiers.cxp_miningAmount;

@Mixin(PlayerEntity.class)
public abstract class ApplyCrystallizedXpModifiers {
    @Inject(
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I",
                shift = At.Shift.BEFORE
            ),
            method = "attack"
    )
    private void addDamage(Entity target, CallbackInfo ci, @Local(ordinal = 0) LocalFloatRef dmg, @Local(ordinal = 2) float h) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        float f = 0;

        ItemStack stack = player.getMainHandStack();
        if (Modifiers.getModifiersFromStack(stack).contains(Modifiers.CRYSTALLIZED_XP)) f += Math.min(player.experienceLevel, 100) * cxp_damageAmount;
        dmg.set(dmg.get() + (f * h));
    }

    @ModifyVariable(
            at = @At("STORE"),
            method = "getBlockBreakingSpeed",
            ordinal = 0
    )
    private float additionalBlockBreakingSpeed(float f, BlockState block) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        ItemStack stack = player.getMainHandStack();

        float ff = stack.getMiningSpeedMultiplier(block);
        if (ff > 1.0f && stack.getItem() instanceof MiningToolItem) {
            List<String> effects = Modifiers.getModifiersFromStack(stack);
            for (String s : effects) {
                if (s.equals(Modifiers.CRYSTALLIZED_XP)) f += Math.min(player.experienceLevel, 100) * cxp_miningAmount;
            }
        }
        return f;
    }
}
