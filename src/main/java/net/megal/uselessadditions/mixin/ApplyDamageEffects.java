package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.megal.uselessadditions.item.SpecialEffects;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ApplyDamageEffects {
    @Shadow private @Nullable LivingEntity attacker;

    @Shadow public abstract Random getRandom();

    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"
            ),
            method = "damage")
    private void applyEffects(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = ((LivingEntity)(Object)this);
        if (source.getAttacker() != null && source.getAttacker() instanceof LivingEntity attacker) {
            boolean fullAttack = !(attacker instanceof PlayerEntity player) || player.getAttackCooldownProgress(1) >= 0.06f;
            ItemStack stack = attacker.getMainHandStack();

            int level = EnchantmentHelper.getLevel(UEnchantments.LIFE_LEECH, attacker.getMainHandStack());
            if (amount > 0 && level > 0 && fullAttack) attacker.heal(Math.min(level * 1f, amount/2f));

            if (UItemHelper.getEffects(stack).contains(SpecialEffects.SERRATED)) {
                entity.addStatusEffect(new StatusEffectInstance(UStatusEffects.BLEEDING, 100, 0), attacker);
            }

            if (UItemHelper.getEffects(stack).contains(SpecialEffects.FRACTURING)) {
                entity.addStatusEffect(new StatusEffectInstance(UStatusEffects.FRACTURED, getRandom().nextBetween(100, 200), 0), attacker);
            }

            if (UItemHelper.getEffects(stack).contains(SpecialEffects.STUNNING)) {
                entity.addStatusEffect(new StatusEffectInstance(UStatusEffects.STUNNED, 100, 0), attacker);
            }
        }
    }
}
