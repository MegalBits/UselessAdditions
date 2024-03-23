package net.megal.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(ModelPredicateProviderRegistry.class)
public abstract class ShowLongerBowPullback {
    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "method_27890"
    )
    private static float modifyPull(float original, ItemStack stack, ClientWorld world, LivingEntity entity, int seed) {
        if (entity != null && stack == entity.getActiveItem()) return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 40;
        return original;
    }
}
