package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EffectsOnFall {
    @Inject(at = @At("HEAD"),
            method = "tick()V",
            cancellable = true)
    private void tick(CallbackInfo ci) {
        LivingEntity entity = ((LivingEntity)(Object)this);
        BlockPos pos1 = entity.getBlockPos().subtract(new Vec3i(0,4,0));
        BlockPos pos2 = entity.getBlockPos().add(new Vec3i(0,0,0));
        if (!entity.getWorld().isClient && !entity.isOnGround() && !entity.isTouchingWater() && entity.fallDistance > 3) {
            if (entity instanceof PlayerEntity player) {
                ItemStack stack = player.getInventory().getArmorStack(3);
                if (!stack.isEmpty() && stack.hasEnchantments() && EnchantmentHelper.getLevel(UEnchantments.PARACHUTE, stack) > 0) {
                    World world = entity.getWorld();
                    for (BlockPos pos : BlockPos.iterate(pos1, pos2)) {
                        BlockState state = world.getBlockState(pos);
                        if (state.isAir()) continue;
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 2));
                        break;
                    }
                }
            }
        }
    }
}
