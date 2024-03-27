package net.megal.mixin.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.item.client.ItemInfo;
import net.megal.shader.ShaderLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class LoadAfterClientInit {
    @Inject(
            at = @At("TAIL"),
            method = "<init>"
    )
    private void loadStuffAfterInit(RunArgs args, CallbackInfo ci) {
        ItemInfo.addTooltipTypes();
    }
}
