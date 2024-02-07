package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.uselessadditions.block.UBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShearsItem.class)
public abstract class ShearsWorkOnPlantFibre {
    @ModifyReturnValue(at = @At("RETURN"),
            method = "postMine")
    private boolean modifyMiningLevel(boolean b, ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return b && !state.isOf(UBlocks.PLANT_FIBRE_TRIPWIRE);
    }

    @ModifyReturnValue(at = @At("RETURN"),
            method = "isSuitableFor")
    private boolean modifyMiningLevel(boolean b, BlockState state) {
        return b || state.isOf(UBlocks.PLANT_FIBRE_TRIPWIRE);
    }
}
