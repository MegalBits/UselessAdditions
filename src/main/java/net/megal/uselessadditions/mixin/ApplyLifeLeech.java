package net.megal.uselessadditions.mixin;

import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.effect.UStatusEffects;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ApplyLifeLeech {
    @Shadow private @Nullable LivingEntity attacker;

    @Inject(at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"
            ),
            method = "damage")
    private void applyHeal(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() != null && source.getAttacker() instanceof LivingEntity attacker) {
            boolean fullAttack = !(attacker instanceof PlayerEntity player) || player.getAttackCooldownProgress(0.5f) == 1f;
            int level = EnchantmentHelper.getLevel(UEnchantments.LIFE_LEECH, attacker.getMainHandStack());
            if (amount > 0 && level > 0 && fullAttack) attacker.heal(Math.min(level * 1f, amount/2f));
        }
    }
}
