package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.enchantment.UEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class ExtraExperienceOnKill {
    @ModifyArg(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"),
            method = "dropXp()V",
            index = 2)
    private int modifyXp(int xp) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if (entity instanceof PlayerEntity) return xp;

        float modifiedXp = xp;
        if (xp > 0) {
            @Nullable ItemStack stack = entity.getLastAttacker().getMainHandStack();
            if (stack != null && !stack.isEmpty() && stack.hasEnchantments()) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    int level = enchantments.get(ench);
                    if (ench instanceof UEnchantment uEnch) {
                        modifiedXp += uEnch.getExperience(level);
                        modifiedXp *= uEnch.getExperienceMultiplier(level);
                    }
                }
            }
        }
        return (int) modifiedXp;
    }
}