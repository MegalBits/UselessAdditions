package net.megal.uselessadditions.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.megal.uselessadditions.UAdd;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class UGroups {
    public static ItemGroup UAddTab;
    public static void registerItemGroups() {
        UAddTab = FabricItemGroup.builder(new Identifier(UAdd.MOD_ID, "uadd"))
                .displayName(Text.translatable("itemgroup.uadd"))
                .icon(() -> new ItemStack(UItems.EMERALD_SWORD)).build();
    }
}
