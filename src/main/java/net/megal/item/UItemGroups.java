package net.megal.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.megal.UAdd;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class UItemGroups {
    public static final RegistryKey<ItemGroup> MAIN = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(UAdd.ID, "uadd"));
    public static final RegistryKey<ItemGroup> SHARDS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(UAdd.ID, "shards"));

    static {
        registerGroup(MAIN, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.uadd"))
                .icon(() -> new ItemStack(UItems.EXOTIC_INGOT)).build());
        registerGroup(SHARDS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.shards"))
                .icon(() -> new ItemStack(UItems.BLAZE_SHARD)).build());
    }

    private static void registerGroup(RegistryKey<ItemGroup> key, ItemGroup builder) {
        Registry.register(Registries.ITEM_GROUP, key, builder);
    }

    public static void groupLoad() {}
}
