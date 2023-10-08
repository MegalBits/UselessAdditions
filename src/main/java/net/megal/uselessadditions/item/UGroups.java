package net.megal.uselessadditions.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.megal.uselessadditions.UAdd;
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

    static {
        registerGroup(UAddTab, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.uadd"))
                .icon(() -> new ItemStack(UItems.EMERALD_SWORD)).build());
    }
    private static void registerGroup(RegistryKey<ItemGroup> key, ItemGroup builder) {
        Registry.register(Registries.ITEM_GROUP, key, builder);
    }
    public static void groupLoad() {}
}
