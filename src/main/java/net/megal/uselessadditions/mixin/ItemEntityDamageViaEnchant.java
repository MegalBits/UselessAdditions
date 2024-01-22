package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.DamagePreventingEnch;
import net.megal.uselessadditions.enchantment.UEnchantment;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(ItemEntity.class)
public abstract class ItemEntityDamageViaEnchant {
    @Inject(at = @At("HEAD"),
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = ((ItemEntity) (Object) this);
        ItemStack stack = itemEntity.getStack();
        if (stack.hasNbt()) {
            if (stack.hasEnchantments() || stack.getOrCreateNbt().contains("SpecialEffects")) {
                boolean fireProof = false;

                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
                for (Enchantment ench : enchantments.keySet()) {
                    if (ench instanceof UEnchantment uEnch) {
                        if (uEnch.isFireproof()) fireProof = true;
                        if (uEnch instanceof DamagePreventingEnch dmgEnch) {
                            if (dmgEnch.damageTypeToPrevent() != null && source.isOf(dmgEnch.damageTypeToPrevent())) cir.setReturnValue(true);
                            if (dmgEnch.damageTypesToPrevent() != null && source.isIn(dmgEnch.damageTypesToPrevent())) cir.setReturnValue(true);
                        }
                    }
                }
                List<String> effects = UItemHelper.getEffects(stack);
                if (effects.contains(SpecialEffects.FIREPROOF)) fireProof = true;

                for (String s : effects) {
                    @Nullable RegistryKey<DamageType> type = SpecialEffects.effects.get(s).damageTypeToPrevent;
                    if (type != null) if (source.isOf(type)) cir.setReturnValue(true);
                    @Nullable TagKey<DamageType> typeTag = SpecialEffects.effects.get(s).damageTypesToPrevent;
                    if (typeTag != null) if (source.isIn(typeTag)) cir.setReturnValue(true);
                }

                if (source.isIn(DamageTypeTags.IS_FIRE) && fireProof) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
    @Inject(at = @At("HEAD"),
            method = "isFireImmune()Z",
            cancellable = true)
    private void isFireImmune(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = ((ItemEntity) (Object) this);
        ItemStack stack = itemEntity.getStack();
        if (stack.hasEnchantments()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            for (Enchantment ench : enchantments.keySet()) {
                if (ench instanceof UEnchantment uEnch && uEnch.isFireproof()) cir.setReturnValue(true);
            }
        }
    }
}