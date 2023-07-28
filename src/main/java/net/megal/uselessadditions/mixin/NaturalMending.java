package net.megal.uselessadditions.mixin;

import com.google.common.collect.Lists;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

@Mixin(ExperienceOrbEntity.class)
public abstract class NaturalMending {
    @Shadow
    private int amount;
    @Shadow
    protected abstract int getMendingRepairAmount(int experienceAmount);
    @Shadow
    protected abstract int getMendingRepairCost(int repairAmount);
    @Shadow
    protected abstract int repairPlayerGears(PlayerEntity player, int amount);
    @Inject(at = @At("HEAD"),
            method = "repairPlayerGears(Lnet/minecraft/entity/player/PlayerEntity;I)I",
            cancellable = true)
    private void naturalRepair(PlayerEntity player, int amount, CallbackInfoReturnable<Integer> cir) {
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.chooseEquipmentWith(UEnchantments.NATURAL_MENDING, player, ItemStack::isDamaged);
        if (entry != null) {
            ItemStack itemStack = entry.getValue();
            int i = Math.min(getMendingRepairAmount(this.amount), itemStack.getDamage());
            itemStack.setDamage(itemStack.getDamage() - i);
            int j = amount - getMendingRepairCost(i);
            Random random = new Random();
            if (j > 0 && random.nextInt(2) == 0) {
                cir.setReturnValue(repairPlayerGears(player, j));
            }
        }
    }
}
