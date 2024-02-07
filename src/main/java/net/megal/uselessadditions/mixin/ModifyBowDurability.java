package net.megal.uselessadditions.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Items.class)
public abstract class ModifyBowDurability {
    @ModifyArg(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/item/BowItem;<init>(Lnet/minecraft/item/Item$Settings;)V"),
            method = "<clinit>",
            index = 0)
    private static Item.Settings modifyDurability(Item.Settings settings) {
        return settings.maxDamage(ToolMaterials.WOOD.getDurability());
    }
}
