package net.megal.uselessadditions.item;

import net.minecraft.item.Item;

public class Mesh extends Item {
    public final MeshType meshType;
    public Mesh(MeshType meshType, Settings settings) {
        super(settings);
        this.meshType = meshType;
    }
}
