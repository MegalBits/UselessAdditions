package net.megal.mixin.entity.player.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.UAdd;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class ChangeStatusBarRendering {
    @Shadow @Nullable protected abstract PlayerEntity getCameraPlayer();

    @Shadow private long heartJumpEndTick;

    @Shadow private int ticks;

    @Shadow private int lastHealthValue;

    @Shadow private long lastHealthCheckTime;

    @Shadow private int renderHealthValue;

    @Shadow @Final private Random random;

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow protected abstract int getHeartCount(@Nullable LivingEntity entity);

    @Shadow @Final private MinecraftClient client;

    @Shadow @Nullable protected abstract LivingEntity getRiddenEntity();

    @Shadow protected abstract int getHeartRows(int heartCount);

    @Unique
    private static final Identifier foodTextureId = new Identifier(UAdd.ID, "hud/food/food");

    @Unique
    private static final Identifier emptyFoodTextureId = new Identifier(UAdd.ID, "hud/food/food_empty");

    @Unique
    private static final Identifier saturationTextureId = new Identifier(UAdd.ID, "hud/food/saturation");

    @Unique
    private static final String hungerSuffix = "_hunger";

    @Unique
    private static final Identifier blinkingHeartOverlayTextureId = new Identifier(UAdd.ID, "hud/heart/blink");

    @Unique
    private static final Identifier heartTextureId = new Identifier(UAdd.ID, "hud/heart/heart");

    @Unique
    private static final Identifier absorptionHeartTextureId = new Identifier(UAdd.ID, "hud/heart/absorption");

    @Unique
    private static final Identifier emptyHeartTextureId = new Identifier(UAdd.ID, "hud/heart/heart_empty");

    @Unique
    private static final Identifier emptyBlinkingHeartTextureId = new Identifier(UAdd.ID, "hud/heart/heart_empty_blinking");

    @Unique
    private static final Identifier armorTextureId = new Identifier(UAdd.ID, "hud/armor/armor");

    @Unique
    private static final Identifier airTextureId = new Identifier(UAdd.ID, "hud/air/air");

    @Unique
    private static final String hardcoreSuffix = "_hardcore";

    //Todo: Have mount health be in quarters instead of halves
    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"
            ),
            method = "render"
    )
    private void renderStatusBars(InGameHud instance, DrawContext context) {
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity != null) {
            int health = MathHelper.ceil(playerEntity.getHealth());
            int absorption = MathHelper.ceil(playerEntity.getAbsorptionAmount());
            float maxHealth = Math.max((float)playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH), (float)Math.max(renderHealthValue, health));
            boolean isBlinking = this.heartJumpEndTick > (long)this.ticks && (this.heartJumpEndTick - (long)this.ticks) / 3L % 2L == 1L;

            long ms = Util.getMeasuringTimeMs();
            if (health < this.lastHealthValue && playerEntity.timeUntilRegen > 0) {
                this.lastHealthCheckTime = ms;
                this.heartJumpEndTick = this.ticks + 20;
            } else if (health > this.lastHealthValue && playerEntity.timeUntilRegen > 0) {
                this.lastHealthCheckTime = ms;
                this.heartJumpEndTick = this.ticks + 10;
            }

            if (ms - this.lastHealthCheckTime > 1000L) {
                this.renderHealthValue = health;
                this.lastHealthCheckTime = ms;
            }

            this.lastHealthValue = health;
            int renderHealthValue = this.renderHealthValue;
            this.random.setSeed(this.ticks * 312871L);
            int healthRows = MathHelper.ceil((maxHealth + (float)absorption) / 4f / 10f);
            int lines = Math.max(10 - (healthRows - 2), 3);
            HungerManager hungerManager = playerEntity.getHungerManager();
            int foodLevel = hungerManager.getFoodLevel();
            float saturationLevel = playerEntity.getHungerManager().getSaturationLevel();
            int column1 = this.scaledWidth / 2 - 91;
            int column2 = this.scaledWidth / 2 + 91;
            int row1 = this.scaledHeight - 39;
            int row2 = row1 - 10;
            int column2Offset = 0;
            int armorRow = row1 - (healthRows - 1) * lines - 10;
            int armor = playerEntity.getArmor();
            int armorIcons = MathHelper.ceil(armor / 4f);
            int regeneratingHeartIndex = -1;
            if (playerEntity.hasStatusEffect(StatusEffects.REGENERATION)) {
                regeneratingHeartIndex = this.ticks % MathHelper.ceil(maxHealth + 5f);
            }

            this.client.getProfiler().push("armor");

            List<Pair<String, Integer>> armorTypes = new ArrayList<>();
            HashMap<String, Integer> tempArmorTypes = new HashMap<>();
            for (ItemStack stack : playerEntity.getArmorItems()) {
                if (!(stack.getItem() instanceof ArmorItem armorItem)) continue;
                String name = armorItem.getMaterial().getName();
                int value = armorItem.getProtection();
                if (tempArmorTypes.containsKey(name)) value += tempArmorTypes.get(name);

                tempArmorTypes.put(name, value);
            }

            Sprite missing = client.getGuiAtlasManager().getSprite(MissingSprite.getMissingSpriteId());
            for (Map.Entry<String, Integer> entry : tempArmorTypes.entrySet()) {
                if (!client.getGuiAtlasManager().getSprite(armorTextureId.withSuffixedPath("_" + entry.getKey() + "_4")).equals(missing)) {
                    armorTypes.add(new Pair<>(entry.getKey(), entry.getValue()));
                }
            }

            int armorType = -1;
            int currentValue = 0;
            for (int chestplate = 0; chestplate < armorIcons; chestplate++) {
                int currentRow = chestplate / 10;
                int currentXOffset = chestplate % 10;
                int xPos = column1 + currentXOffset * 8;
                int yPos = armorRow - currentRow * lines;

                int stage = MathHelper.clamp(armor - (chestplate * 4), 0,  4);

                List<String> ids = new ArrayList<>();

                int stageLeft = stage;
                while (stageLeft > 0) {
                    if (currentValue == 0) {
                        armorType++;

                        if (armorType + 1 > armorTypes.size()) break;
                        currentValue = armorTypes.get(armorType).getRight();
                    }
                    int decrement = Math.min(stageLeft, currentValue);
                    for (int i = 0; i < decrement; i++) {
                        ids.add(armorTypes.get(armorType).getLeft());
                    }

                    currentValue -= decrement;
                    stageLeft -= decrement;
                }

                for (int i = 0; i < stage; i++) {
                    int stg = i + 1;

                    String id = "default";
                    if (i < ids.size()) id = ids.get(i);
                    context.drawGuiTexture(armorTextureId.withSuffixedPath("_" + id + "_" + stg), xPos, yPos, 9, 9);
                }
            }

            this.client.getProfiler().swap("health");
            boolean isHardcore = playerEntity.getWorld().getLevelProperties().isHardcore();
            int heartCount = MathHelper.ceil((double)maxHealth / 4d);
            int absorptionHeartCount = MathHelper.ceil((double)absorption / 4d);

            Identifier heartTexture = heartTextureId;
            if (isHardcore) {
                heartTexture = heartTexture.withSuffixedPath(hardcoreSuffix);
            }

            for(int heart = heartCount + absorptionHeartCount - 1; heart >= 0; --heart) {
                int currentRow = heart / 10;
                int currentXOffset = heart % 10;
                int xPos = column1 + currentXOffset * 8;
                int yPos = row1 - currentRow * lines;
                if (health + absorption <= 4) {
                    yPos += this.random.nextInt(2);
                }

                if (heart < heartCount && heart == regeneratingHeartIndex) {
                    yPos -= 2;
                }

                boolean isAbsorption = heart >= heartCount;

                Identifier container = emptyHeartTextureId;
                Identifier texture = isAbsorption ? absorptionHeartTextureId : heartTexture;
                Identifier blinking = blinkingHeartOverlayTextureId;

                String effect = "";
                if (!isAbsorption) {
                    InGameHud.HeartType heartType = InGameHud.HeartType.fromPlayerState(playerEntity);
                    if (heartType != InGameHud.HeartType.NORMAL) {
                        Identifier heartTypeId = heartType.getTexture(false, false, false);
                        String untestedEffect = heartTypeId.getPath().replaceFirst("hud/heart/", "").replaceFirst("_full", "");

                        if (!client.getGuiAtlasManager().getSprite(texture.withSuffixedPath("_" + untestedEffect + "_4")).equals(missing)) {
                            effect = untestedEffect;
                        }
                        else {
                            UAdd.LOGGER.warn("Found unsupported heart type: " + heartTypeId.getPath() + " with namespace: " + heartTypeId.getNamespace());
                        }
                    }

                    if (!effect.isEmpty()) texture = texture.withSuffixedPath("_" + effect);
                }


                int heart2 = isAbsorption ? heart - heartCount : heart;
                int health2 = isAbsorption ? absorption : health;
                int stage = MathHelper.clamp(health2 - (heart2 * 4), 0,  4);

                texture = texture.withSuffixedPath("_" + stage);
                blinking = blinking.withSuffixedPath("_" + stage);

                if (isBlinking && !isAbsorption) container = emptyBlinkingHeartTextureId;

                if (!isAbsorption) context.drawGuiTexture(container, xPos, yPos, 9, 9);
                if (stage != 0) {
                    context.drawGuiTexture(texture, xPos, yPos, 9, 9);
                    if (isBlinking && !isAbsorption) context.drawGuiTexture(blinking, xPos, yPos, 9, 9);
                }
            }

            this.client.getProfiler().swap("food");
            for(int food = 0; food < 10; ++food) {
                int xPos = column2 - food * 8 - 9;
                int yPos = row1;

                int stage = MathHelper.clamp(foodLevel - (food * 4), 0,  4);
                int saturationStage = MathHelper.ceil(MathHelper.clamp((saturationLevel * 2) - (food * 8), 0f,  8f));

                Identifier emptyTexture = emptyFoodTextureId;
                Identifier texture = foodTextureId;
                Identifier saturationTexture = saturationTextureId;

                texture = texture.withSuffixedPath("_" + stage);
                saturationTexture = saturationTexture.withSuffixedPath("_" + saturationStage);

                if (playerEntity.hasStatusEffect(StatusEffects.HUNGER)) {
                    emptyTexture = emptyTexture.withSuffixedPath(hungerSuffix);
                    texture = texture.withSuffixedPath(hungerSuffix);
                }

                if (saturationLevel <= 0f && this.ticks % (foodLevel * 3 + 1) == 0) {
                    yPos = row1 + (this.random.nextInt(3) - 1);
                }

                context.drawGuiTexture(emptyTexture, xPos, yPos, 9, 9);
                if (stage > 0) {
                    context.drawGuiTexture(texture, xPos, yPos, 9, 9);
                }
                if (saturationStage > 0) {
                    context.drawGuiTexture(saturationTexture, xPos, yPos, 9, 9);
                }
            }

            this.client.getProfiler().swap("air");
            int maxAir = playerEntity.getMaxAir();
            int air = Math.min(playerEntity.getAir(), maxAir);
            if (playerEntity.isSubmergedIn(FluidTags.WATER) || air < maxAir) {
                for(int bubble = 0; bubble < 10; ++bubble) {
                    int stage = MathHelper.clamp((air * 80 / maxAir) - (bubble * 8), 0,  8);

                    Identifier texture = airTextureId;
                    texture = texture.withSuffixedPath("_" + stage);

                    if (stage > 0) {
                        context.drawGuiTexture(texture, column2 - bubble * 8 - 9, row2 - column2Offset, 9, 9);
                    }
                }
            }

            this.client.getProfiler().pop();
        }
    }

    @ModifyVariable(
            at = @At("STORE"),
            method = "renderMountHealth",
            ordinal = 4
    )
    private int offsetMountHealthY(int value) {
        PlayerEntity playerEntity = this.getCameraPlayer();
        int move = 10;
        if (playerEntity.getAir() < playerEntity.getMaxAir()) move += 10;

        return value - move;
    }
}
