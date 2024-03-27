package net.megal.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.megal.item.TooltipType;
import net.megal.item.UItem;
import net.megal.item.UItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(Item.class)
public abstract class UItemMixin implements UItem {
    @Unique
    private boolean forcedGlint = false;

    @Unique
    public List<String> modifiers = new ArrayList<>();

    @Unique
    public void UAdd$setModifiers(String... modifier) {
        modifiers = Arrays.stream(modifier).toList();
    }

    @Unique
    public List<String> UAdd$getModifiers() {
        return modifiers;
    }

    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "hasGlint"
    )
    private boolean forceGlint(boolean original) {
        return original || forcedGlint;
    }

    @Inject(
            at = @At("TAIL"),
            method = "<init>"
    )
    private void init(Item.Settings settings, CallbackInfo ci) {
        if (settings instanceof UItemSettings uSettings) {
            forcedGlint = uSettings.glint;
            if (uSettings.modifiers != null) {
                UAdd$setModifiers(uSettings.modifiers);
            }
        }
    }
}
