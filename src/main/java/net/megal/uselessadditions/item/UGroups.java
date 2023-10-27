package net.megal.uselessadditions.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.megal.uselessadditions.UAdd;
import net.megal.uselessadditions.block.UBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class UGroups {
    public static final RegistryKey<ItemGroup> UAddTab = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(UAdd.MOD_ID, "uadd"));
    public static final RegistryKey<ItemGroup> SpawnersTab = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(UAdd.MOD_ID, "spawners"));

    static {
        registerGroup(UAddTab, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.uadd"))
                .icon(() -> new ItemStack(UItems.EMERALD_SWORD)).build());
        registerGroup(SpawnersTab, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.spawners"))
                .icon(() -> new ItemStack(UBlocks.SURVIVAL_SPAWNER.asItem())).build());
    }
    private static void registerGroup(RegistryKey<ItemGroup> key, ItemGroup builder) {
        Registry.register(Registries.ITEM_GROUP, key, builder);
    }
    public static void groupLoad() {}
}
