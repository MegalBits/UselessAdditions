package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.enchantment.UEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import static net.megal.uselessadditions.UAdd.autoSmeltItems;
import static net.megal.uselessadditions.UAdd.naturalMendingItems;

@Mixin(PlayerEntity.class)
public abstract class EarthenMinesFaster {
//    @Shadow
//    private @Final PlayerInventory inventory;
    @ModifyReturnValue(at = @At("RETURN"),
            method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F")
    public float getBlockBreakingSpeed(float s, BlockState block) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        if (EnchantmentHelper.getLevel(UEnchantments.SUBTERRANEAN, player.getMainHandStack()) != 0 && player.getPos().getY() < player.getWorld().getSeaLevel()
                && player.getMainHandStack().isSuitableFor(block) && player.getWorld().getDimensionKey() == DimensionTypes.OVERWORLD) {
            double distance = player.getWorld().getSeaLevel() - player.getPos().getY();
            distance = Math.sqrt(distance * distance);
            s += speedModifiers(player, (float)distance / 24f);
        }
        return s;
    }

    private float speedModifiers(PlayerEntity player, float f) {
        if (StatusEffectUtil.hasHaste(player)) {
            f *= 1.0f + (float)(StatusEffectUtil.getHasteAmplifier(player) + 1) * 0.2f;
        }
        if (player.hasStatusEffect(StatusEffects.MINING_FATIGUE)) {
            f *= (switch (player.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) {
                case 0 -> 0.3f;
                case 1 -> 0.09f;
                case 2 -> 0.0027f;
                default -> 8.1E-4f;
            });
        }
        if (player.isSubmergedIn(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(player)) {
            f /= 5.0f;
        }
        if (!player.isOnGround()) {
            f /= 5.0f;
        }
        return f;
    }
}