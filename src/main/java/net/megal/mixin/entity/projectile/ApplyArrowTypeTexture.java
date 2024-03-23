package net.megal.mixin.entity.projectile;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(ArrowEntityRenderer.class)
public abstract class ApplyArrowTypeTexture {
    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "getTexture(Lnet/minecraft/entity/projectile/ArrowEntity;)Lnet/minecraft/util/Identifier;"
    )
    private Identifier changeTextureToType(Identifier original, ArrowEntity arrowEntity) {
        Item item = arrowEntity.asItemStack().getItem();

        return UAdd.arrowTypes.contains(item) && item != Items.ARROW && item != Items.TIPPED_ARROW ? new Identifier(UAdd.ID, "textures/entity/arrows/" + Registries.ITEM.getId(item).getPath() + ".png") : original;
    }
}
