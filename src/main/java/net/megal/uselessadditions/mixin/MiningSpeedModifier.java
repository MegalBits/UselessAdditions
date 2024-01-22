package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.enchantment.UEnchantment;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.Map;

import static net.megal.uselessadditions.item.SpecialEffects.*;

@Mixin(PlayerEntity.class)
public abstract class MiningSpeedModifier {
    @ModifyVariable(at = @At("STORE"),
            method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
            ordinal = 0)
    private float additionalBlockBreakingSpeed(float f, BlockState block) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        float ff = player.getInventory().getBlockBreakingSpeed(block);
        if (ff > 1.0f) {
            ItemStack stack = player.getMainHandStack();
            if (stack.hasEnchantments()) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    int level = enchantments.get(ench);
                    if (ench instanceof UEnchantment uEnch) {
                        f += uEnch.getMiningSpeed(level, block);
                    }
                }
            }
            List<String> effects = UItemHelper.getEffects(stack);
            for (String s : effects) {
                if (s.equals(SpecialEffects.CRYSTALLIZED_XP)) f += Math.min(player.experienceLevel, 80) * CXP_EFFICIENCY;
                f += SpecialEffects.effects.get(s).miningSpeedMod;
            }
        }
        return f;
    }
    @ModifyReturnValue(at = @At("RETURN"),
            method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F")
    public float getBlockBreakingSpeed(float f, BlockState block) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        ItemStack stack = player.getMainHandStack();
        if (stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            for (Enchantment ench : enchantments.keySet()) {
                int level = enchantments.get(ench);
                if (ench instanceof UEnchantment uEnch) {
                    f *= uEnch.getMiningSpeedMultiplier(level, block);
                }
            }
        }
        List<String> effects = UItemHelper.getEffects(stack);
        for (String s : effects) {
            if (s.equals(SpecialEffects.TREE_FELLING) && stack.isSuitableFor(block) && block.getBlock() instanceof PillarBlock) f *= 0.07f;
            f *= SpecialEffects.effects.get(s).miningSpeedMul;
        }
        return f;
    }
}
