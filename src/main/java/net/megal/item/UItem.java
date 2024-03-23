package net.megal.item;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Unique;

import java.util.Arrays;
import java.util.List;

public interface UItem {
    default void UAdd$setModifiers(String... modifier) {
        ((Item)this).UAdd$setModifiers(modifier);
    }
    default List<String> UAdd$getModifiers() {
        return ((Item)this).UAdd$getModifiers();
    }
}
