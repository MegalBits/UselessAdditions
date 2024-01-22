package net.megal.uselessadditions.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.UAdd;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

@Environment(value= EnvType.CLIENT)
public class EnhancementScreen extends ForgingScreen<EnhancementScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(UAdd.MOD_ID, "textures/gui/container/enhancement.png");
    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE = new Identifier(UAdd.MOD_ID, "item/hammer_template");
    private static final Text MISSING_TEMPLATE_TOOLTIP = Text.translatable((String)"container.uselessadditions.no_hammer_tooltip");
    private static final Text ERROR_TOOLTIP = Text.translatable((String)"container.uselessadditions.upgrade.maxed_tooltip");
    private static final List<Identifier> EMPTY_SLOT_TEXTURES = List.of(EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE);
    public static final Quaternionf ARMOR_STAND_ROTATION = new Quaternionf().rotationXYZ(0.43633232f, 0.0f, (float)Math.PI);
    private final CyclingSlotIcon templateSlotIcon = new CyclingSlotIcon(0);
    private final CyclingSlotIcon baseSlotIcon = new CyclingSlotIcon(1);
    private final CyclingSlotIcon additionsSlotIcon = new CyclingSlotIcon(2);
    @Nullable
    private ArmorStandEntity armorStand;

    public EnhancementScreen(EnhancementScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleX = 44;
        this.titleY = 15;
    }

    @Override
    protected void setup() {
        this.armorStand = new ArmorStandEntity((World)this.client.world, 0.0, 0.0, 0.0);
        this.armorStand.setHideBasePlate(true);
        this.armorStand.setShowArms(true);
        this.armorStand.bodyYaw = 210.0f;
        this.armorStand.setPitch(25.0f);
        this.armorStand.headYaw = this.armorStand.getYaw();
        this.armorStand.prevHeadYaw = this.armorStand.getYaw();
        this.equipArmorStand(((EnhancementScreenHandler)this.handler).getSlot(3).getStack());
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        Optional<SmithingTemplateItem> optional = this.getSmithingTemplate();
        this.templateSlotIcon.updateTexture(EMPTY_SLOT_TEXTURES);
        this.baseSlotIcon.updateTexture(optional.map(SmithingTemplateItem::getEmptyBaseSlotTextures).orElse(List.of()));
        this.additionsSlotIcon.updateTexture(optional.map(SmithingTemplateItem::getEmptyAdditionsSlotTextures).orElse(List.of()));
    }

    private Optional<SmithingTemplateItem> getSmithingTemplate() {
        Item item;
        ItemStack itemStack = ((EnhancementScreenHandler)this.handler).getSlot(0).getStack();
        if (!itemStack.isEmpty() && (item = itemStack.getItem()) instanceof SmithingTemplateItem) {
            SmithingTemplateItem smithingTemplateItem = (SmithingTemplateItem)item;
            return Optional.of(smithingTemplateItem);
        }
        return Optional.empty();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderSlotTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);
        this.templateSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.baseSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.additionsSlotIcon.render(this.handler, context, delta, this.x, this.y);
        InventoryScreen.drawEntity(context, (float)(this.x + 141), (float)(this.y + 75), 25, new Vector3f(), ARMOR_STAND_ROTATION, null, this.armorStand);
    }

    @Override
    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
        if (slotId == 3) {
            this.equipArmorStand(stack);
        }
    }

    private void equipArmorStand(ItemStack stack) {
        if (this.armorStand == null) {
            return;
        }
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            this.armorStand.equipStack(equipmentSlot, ItemStack.EMPTY);
        }
        if (!stack.isEmpty()) {
            ItemStack itemStack = stack.copy();
            Item item = stack.getItem();
            if (item instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem)item;
                this.armorStand.equipStack(armorItem.getSlotType(), itemStack);
            } else {
                this.armorStand.equipStack(EquipmentSlot.OFFHAND, itemStack);
            }
        }
    }

    @Override
    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if (this.hasInvalidRecipe()) {
            context.drawTexture(TEXTURE, x + 65, y + 46, this.backgroundWidth, 0, 28, 21);
        }
    }

    private void renderSlotTooltip(DrawContext context, int mouseX, int mouseY) {
        Optional<Text> optional = Optional.empty();
        if (this.hasInvalidRecipe() && this.isPointWithinBounds(65, 46, 28, 21, mouseX, mouseY)) {
            optional = Optional.of(ERROR_TOOLTIP);
        }
        if (this.focusedSlot != null) {
            ItemStack itemStack = ((EnhancementScreenHandler)this.handler).getSlot(0).getStack();
            ItemStack itemStack2 = this.focusedSlot.getStack();
            if (itemStack.isEmpty()) {
                if (this.focusedSlot.id == 0) {
                    optional = Optional.of(MISSING_TEMPLATE_TOOLTIP);
                }
            } else {
                Item item = itemStack.getItem();
                if (item instanceof SmithingTemplateItem) {
                    SmithingTemplateItem smithingTemplateItem = (SmithingTemplateItem)item;
                    if (itemStack2.isEmpty()) {
                        if (this.focusedSlot.id == 1) {
                            optional = Optional.of(smithingTemplateItem.getBaseSlotDescription());
                        } else if (this.focusedSlot.id == 2) {
                            optional = Optional.of(smithingTemplateItem.getAdditionsSlotDescription());
                        }
                    }
                }
            }
        }
        optional.ifPresent(text -> context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines((StringVisitable)text, 115), mouseX, mouseY));
    }

    private boolean hasInvalidRecipe() {
        return ((EnhancementScreenHandler)this.handler).getSlot(0).hasStack() && ((EnhancementScreenHandler)this.handler).getSlot(1).hasStack() && ((EnhancementScreenHandler)this.handler).getSlot(2).hasStack() && !((EnhancementScreenHandler)this.handler).getSlot(((EnhancementScreenHandler)this.handler).getResultSlotIndex()).hasStack();
    }
}

