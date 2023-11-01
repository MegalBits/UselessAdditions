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
    @ModifyVariable(at = @At("STORE"),
            method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
            ordinal = 0)
    private float additionalBlockBreakingSpeed(float f) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        if (f > 1.0f) {
            if (EnchantmentHelper.getLevel(UEnchantments.SUBTERRANEAN, player.getMainHandStack()) != 0 && player.getPos().getY() < player.getWorld().getSeaLevel()
                    && player.getWorld().getDimensionKey() == DimensionTypes.OVERWORLD) {
                double distance = player.getWorld().getSeaLevel() - player.getPos().getY();
                distance = Math.sqrt(distance * distance);
                f += (float)distance / 24f;
            }
        }
        return f;
    }
}
