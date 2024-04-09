package net.megal.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.megal.UAdd;
import net.megal.block.UBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UBlockEntities {
    private static <T extends BlockEntityType<?>> T register(String id, T blockEntity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(UAdd.ID, id), blockEntity);
    }
    public static void loadStuff() {}
}
