package net.megal.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.megal.UAdd;
import net.megal.block.UBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class UItemGroups {
    public static final RegistryKey<ItemGroup> MAIN = register(createKey("main"), FabricItemGroup.builder()
            .displayName(Text.translatable("uselessadditions.itemgroup.main"))
            .icon(() -> new ItemStack(UItems.AMETHYST_CORE))
            .build()
    );

    public static RegistryKey<ItemGroup> createKey(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(UAdd.ID, id));
    }

    public static RegistryKey<ItemGroup> register(RegistryKey<ItemGroup> registryKey, ItemGroup itemGroup) {
        Registry.register(Registries.ITEM_GROUP, registryKey, itemGroup);
        return registryKey;
    }
}
