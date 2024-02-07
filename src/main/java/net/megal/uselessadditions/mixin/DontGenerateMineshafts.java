package net.megal.uselessadditions.mixin;

import net.minecraft.world.gen.structure.MineshaftStructure;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(MineshaftStructure.class)
public abstract class DontGenerateMineshafts {
    @Inject(at = @At("HEAD"),
            method = "getStructurePosition",
            cancellable = true)
    private void stopGeneration(Structure.Context context, CallbackInfoReturnable<Optional<Structure.StructurePosition>> cir) {
        // Todo: add config to toggle this
        cir.setReturnValue(Optional.empty());
    }
}
