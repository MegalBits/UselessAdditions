package net.megal.player;

import net.megal.item.HealthIncreaseItem;

import java.util.HashMap;

public interface PlayerNbt {
    default boolean UAdd$healthIncrease(HealthIncreaseItem item) {
        return true;
    }

    default HashMap<String, Integer> UAdd$getHealthIncreases() {
        return new HashMap<>();
    }

    default void UAdd$setHealthIncreases(HashMap<String, Integer> map) {}
}
