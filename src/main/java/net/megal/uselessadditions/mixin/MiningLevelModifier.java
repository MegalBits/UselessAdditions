package net.megal.uselessadditions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.megal.uselessadditions.item.base.UItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.megal.uselessadditions.item.SpecialEffects.*;

@Mixin(PlayerEntity.class)
public abstract class MiningLevelModifier {
    @ModifyReturnValue(at = @At("RETURN"),
            method = "canHarvest(Lnet/minecraft/block/BlockState;)Z")
    private boolean modifyMiningLevel(boolean b, BlockState state) {
        PlayerEntity player = ((PlayerEntity)(Object)this);
        ItemStack stack = player.getMainHandStack();
        if (stack.getItem() instanceof ToolItem toolItem) {
            int levelReq = MiningLevelManager.getRequiredMiningLevel(state);
            int level = toolItem.getMaterial().getMiningLevel();
            if (UItemHelper.getEffects(stack).contains(CRYSTALLIZED_XP)) level += (int) Math.min(Math.floor(player.experienceLevel / CXP_MINING_LEVEL_REQ), CXP_MINING_LEVEL_CAP);
            return level >= levelReq && b;
        }
        return b;
    }
}
