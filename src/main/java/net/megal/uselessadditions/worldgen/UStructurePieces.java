package net.megal.uselessadditions.worldgen;

import net.megal.uselessadditions.UAdd;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;

public class UStructurePieces {
    public static final StructurePieceType MS_START = registerSimple(new Identifier(UAdd.MOD_ID, "mineshaft_start"), UMineshaftGenerator.Start::new);
    public static final StructurePieceType MS_CROSSING = registerSimple(new Identifier(UAdd.MOD_ID, "mineshaft_crossing"), UMineshaftGenerator.Crossing::new);
    public static final StructurePieceType MS_CONNECTOR = registerSimple(new Identifier(UAdd.MOD_ID, "mineshaft_connector"), UMineshaftGenerator.Connector::new);
    public static final StructurePieceType MS_STAIRS = registerSimple(new Identifier(UAdd.MOD_ID, "mineshaft_stairs"), UMineshaftGenerator.Stairs::new);
    public static final StructurePieceType MS_TUNNEL = registerSimple(new Identifier(UAdd.MOD_ID, "mineshaft_tunnel"), UMineshaftGenerator.Tunnel::new);
    public static final StructurePieceType MS_END = registerSimple(new Identifier(UAdd.MOD_ID, "mineshaft_end"), UMineshaftGenerator.Terminator::new);

    private static StructurePieceType registerSimple(Identifier id, StructurePieceType.Simple type) {
        return Registry.register(Registries.STRUCTURE_PIECE, id, type);
    }

    public static void loadStuff() {}
}
