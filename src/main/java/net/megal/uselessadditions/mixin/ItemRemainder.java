package net.megal.uselessadditions.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemRemainder {
    @Accessor("recipeRemainder")
    @Mutable
    public void setRecipeRemainder(Item recipeRemainder);
}
