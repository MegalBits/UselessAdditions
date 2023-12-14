package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

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
            if (EnchantmentHelper.getLevel(UEnchantments.SUBTERRANEAN, stack) != 0 && player.getPos().getY() < player.getWorld().getSeaLevel()
                    && player.getWorld().getDimensionKey() == DimensionTypes.OVERWORLD) {
                double distance = player.getWorld().getSeaLevel() - player.getPos().getY();
                distance = Math.sqrt(distance * distance);
                f += (float)distance / 18f;
            }
            if (stack.hasEnchantments()) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    int level = enchantments.get(ench);
                    if (ench instanceof AugmentEnchantment aug) {
                        f += aug.getMiningSpeed(level, block);
                    }
                }
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
                if (ench instanceof AugmentEnchantment aug) {
                    f *= aug.getMiningMultiplier(level, block);
                }
            }
        }
        return f;
    }
}
