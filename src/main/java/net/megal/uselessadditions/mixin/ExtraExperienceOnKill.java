package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.UAddClient;
import net.megal.uselessadditions.enchantment.AugmentEnchantment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class ExtraExperienceOnKill {
    /*
    @Inject(at = @At("RETURN"),
            method = "getXpToDrop()I")
    private int getXpToDrop(int xp) {
        if (xp > 0) {
            LivingEntity entity = ((LivingEntity) (Object) this);
            @Nullable ItemStack stack = entity.getLastAttacker().getMainHandStack();
            UAdd.LOGGER.info(entity.getLastAttacker().toString());
            UAdd.LOGGER.info(stack.toString());
            if (stack != null && !stack.isEmpty() && stack.hasEnchantments()) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    int level = enchantments.get(ench);
                    if (ench instanceof AugmentEnchantment aug) {
                        xp += aug.getExperience(level);
                        xp *= aug.getExperienceMultiplier(level);
                        UAdd.LOGGER.info("WORKED!");
                    }
                }
            }
        }
        return xp;
    }*/

    @ModifyArg(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"),
            method = "dropXp()V",
            index = 2)
    private int modifyXp(int xp) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if (entity instanceof PlayerEntity) return xp;
        if (xp > 0) {
            @Nullable ItemStack stack = entity.getLastAttacker().getMainHandStack();
            if (stack != null && !stack.isEmpty() && stack.hasEnchantments()) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    int level = enchantments.get(ench);
                    if (ench instanceof AugmentEnchantment aug) {
                        xp += aug.getExperience(level);
                        xp *= aug.getExperienceMultiplier(level);
                    }
                }
            }
        }
        return xp;
    }
}