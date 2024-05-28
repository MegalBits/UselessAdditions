package net.megal.block;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.megal.UAdd;
import net.megal.sound.USoundGroups;
import net.megal.sound.USounds;
import net.minecraft.block.BlockSetType;
import net.minecraft.util.Identifier;

public class UBlockSetTypes {
    public static BlockSetType register(BlockSetTypeBuilder builder, String id) {
        return builder.register(new Identifier(UAdd.ID, id));
    }
}
