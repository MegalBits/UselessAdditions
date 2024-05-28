package net.megal.mixin;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.megal.UAdd;
import net.megal.item.HealthIncreaseItem;
import net.megal.player.PlayerNbt;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerNbt {
    @Unique
    private HashMap<String, Integer> healthIncreases = new HashMap<>();

    @Override
    public boolean UAdd$healthIncrease(HealthIncreaseItem item) {
        if (healthIncreases.containsKey(item.id) && healthIncreases.get(item.id) >= item.maxUses) return false;
        healthIncreases.put(item.id, healthIncreases.getOrDefault(item.id, 0) + 1);
        updateHealthIncrease();
        return true;
    }

    @Override
    public HashMap<String, Integer> UAdd$getHealthIncreases() {
        return healthIncreases;
    }

    @Override
    public void UAdd$setHealthIncreases(HashMap<String, Integer> map) {
        healthIncreases = map;
        updateHealthIncrease();
    }

    @Unique
    private void updateHealthIncrease() {
        PlayerEntity player = (PlayerEntity)(Object)this;
        int increase = 0;
        for (Map.Entry<String, Integer> entry : healthIncreases.entrySet()) {
            if (!HealthIncreaseItem.healthIncreaseItems.containsKey(entry.getKey())) continue;
            increase += HealthIncreaseItem.healthIncreaseItems.get(entry.getKey()).increase * entry.getValue();
        }

        EntityAttributeInstance entityAttributeInstance = player.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        UUID modifierUUID = UUID.fromString("dffcbc0c-29e3-442c-b064-7b8066a7ec9e");
        if (increase > 0) {
            EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(
                    modifierUUID,
                    "Health Increase",
                    increase,
                    EntityAttributeModifier.Operation.ADD_VALUE
            );

            if (entityAttributeInstance.hasModifier(entityAttributeModifier)) entityAttributeInstance.removeModifier(modifierUUID);

            entityAttributeInstance.addPersistentModifier(entityAttributeModifier);
        }
        else {
            entityAttributeInstance.removeModifier(modifierUUID);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "writeCustomDataToNbt"
    )
    private void writeExtraCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        NbtList nbtList = new NbtList();
        for (Map.Entry<String, Integer> entry : healthIncreases.entrySet()) {
            NbtCompound healthIncrease = new NbtCompound();
            healthIncrease.putString("id", entry.getKey());
            healthIncrease.putInt("amount", entry.getValue());
            nbtList.add(healthIncrease);
        }
        nbt.put("health_increases", nbtList);
    }

    @Inject(
            at = @At("HEAD"),
            method = "readCustomDataFromNbt"
    )
    private void readExtraCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        UAdd.LOG.info("READ");

        NbtList nbtList = nbt.getList("health_increases", NbtElement.COMPOUND_TYPE);
        for (NbtElement nbtElement : nbtList) {
            NbtCompound healthIncrease = (NbtCompound) nbtElement;
            healthIncreases.put(healthIncrease.getString("id"), healthIncrease.getInt("amount"));
        }
        updateHealthIncrease();
    }

    @ModifyReturnValue(
            at = @At("RETURN"),
            method = "createPlayerAttributes"
    )
    private static DefaultAttributeContainer.Builder changeDefaultMaxHealth(DefaultAttributeContainer.Builder original) {
        return original.add(EntityAttributes.GENERIC_MAX_HEALTH, 40);
    }
}
