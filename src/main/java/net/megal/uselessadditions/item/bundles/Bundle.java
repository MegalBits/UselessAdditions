package net.megal.uselessadditions.item.bundles;

import net.megal.uselessadditions.UAdd;
import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Bundle extends Item {

    private static final String ITEMS_KEY = "Items";
    public static int MAX_STORAGE = 128;
    public static final int BUNDLE_ITEM_OCCUPANCY = 4;
    public Bundle(Settings settings) {
        super(settings);
    }
    public static float getAmountFilled(ItemStack stack) {
        return (float) Bundle.getBundleOccupancy(stack) / MAX_STORAGE;
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        }
        ItemStack itemStack = slot.getStack();
        if (itemStack.isEmpty()) {
            this.playRemoveOneSound(player);
            Bundle.removeFirstStack(stack).ifPresent(removedStack -> Bundle.addToBundle(stack, slot.insertStack((ItemStack)removedStack)));
        } else if (itemStack.getItem().canBeNested()) {
            int i = (MAX_STORAGE - Bundle.getBundleOccupancy(stack)) / Bundle.getItemOccupancy(itemStack);
            int j = Bundle.addToBundle(stack, slot.takeStackRange(itemStack.getCount(), i, player));
            if (j > 0) {
                this.playInsertSound(player);
            }
        }
        return true;
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType != ClickType.RIGHT || !slot.canTakePartial(player)) {
            return false;
        }
        if (otherStack.isEmpty()) {
            Bundle.removeFirstStack(stack).ifPresent(itemStack -> {
                this.playRemoveOneSound(player);
                cursorStackReference.set((ItemStack)itemStack);
            });
        } else {
            int i = Bundle.addToBundle(stack, otherStack);
            if (i > 0) {
                this.playInsertSound(player);
                otherStack.decrement(i);
            }
        }
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (Bundle.dropAllBundledItems(itemStack, user)) {
            this.playDropContentsSound(user);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return Bundle.getBundleOccupancy(stack) > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.min(1 + 12 * Bundle.getBundleOccupancy(stack) / MAX_STORAGE, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return UBundles.getBundleColor(getBundleOccupancy(stack), MAX_STORAGE);
    }

    private static int addToBundle(ItemStack bundle, ItemStack stack) {
        if (stack.isEmpty() || !stack.getItem().canBeNested()) {
            return 0;
        }
        NbtCompound nbtCompound = bundle.getOrCreateNbt();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            nbtCompound.put(ITEMS_KEY, new NbtList());
        }
        int i = Bundle.getBundleOccupancy(bundle);
        int j = Bundle.getItemOccupancy(stack);
        int k = Math.min(stack.getCount(), (MAX_STORAGE - i) / j);
        if (k == 0) {
            return 0;
        }
        NbtList nbtList = nbtCompound.getList(ITEMS_KEY, NbtElement.COMPOUND_TYPE);
        Optional<NbtCompound> optional = Bundle.canMergeStack(stack, nbtList);
        if (optional.isPresent()) {
            NbtCompound nbtCompound2 = optional.get();
            ItemStack itemStack = ItemStack.fromNbt(nbtCompound2);
            itemStack.increment(k);
            itemStack.writeNbt(nbtCompound2);
            nbtList.remove(nbtCompound2);
            nbtList.add(0, nbtCompound2);
        } else {
            ItemStack itemStack2 = stack.copy();
            itemStack2.setCount(k);
            NbtCompound nbtCompound3 = new NbtCompound();
            itemStack2.writeNbt(nbtCompound3);
            nbtList.add(0, nbtCompound3);
        }
        return k;
    }

    private static Optional<NbtCompound> canMergeStack(ItemStack stack, NbtList items) {
        if (stack.getItem() instanceof BundleItem || stack.getItem() instanceof Bundle) {
            return Optional.empty();
        }
        return items.stream().filter(NbtCompound.class::isInstance).map(NbtCompound.class::cast).filter(item -> canCombine(ItemStack.fromNbt(item), stack)).findFirst();
    }

    public static boolean canCombine(ItemStack stack, ItemStack otherStack) {
        return stack.isOf(otherStack.getItem()) && Objects.equals(stack.getNbt(), otherStack.getNbt()) && stack.getCount() + otherStack.getCount() <= stack.getMaxCount();
    }

    private static int getItemOccupancy(ItemStack stack) {
        NbtCompound nbtCompound;
        int i = UBundles.getBundleSpace(stack, getBundleOccupancy(stack));
        if (i != 0) return i;
        if ((stack.isOf(Items.BEEHIVE) || stack.isOf(Items.BEE_NEST)) && stack.hasNbt() && (nbtCompound = BlockItem.getBlockEntityNbt(stack)) != null && !nbtCompound.getList("Bees", NbtElement.COMPOUND_TYPE).isEmpty()) {
            return 64;
        }
        return 64 / stack.getMaxCount();
    }

    private static int getBundleOccupancy(ItemStack stack) {
        return Bundle.getBundledStacks(stack).mapToInt(itemStack -> Bundle.getItemOccupancy(itemStack) * itemStack.getCount()).sum();
    }

    private static Optional<ItemStack> removeFirstStack(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            return Optional.empty();
        }
        NbtList nbtList = nbtCompound.getList(ITEMS_KEY, NbtElement.COMPOUND_TYPE);
        if (nbtList.isEmpty()) {
            return Optional.empty();
        }
        boolean i = false;
        NbtCompound nbtCompound2 = nbtList.getCompound(0);
        ItemStack itemStack = ItemStack.fromNbt(nbtCompound2);
        nbtList.remove(0);
        if (nbtList.isEmpty()) {
            stack.removeSubNbt(ITEMS_KEY);
        }
        return Optional.of(itemStack);
    }

    private static boolean dropAllBundledItems(ItemStack stack, PlayerEntity player) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            return false;
        }
        if (player instanceof ServerPlayerEntity) {
            NbtList nbtList = nbtCompound.getList(ITEMS_KEY, NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound2 = nbtList.getCompound(i);
                ItemStack itemStack = ItemStack.fromNbt(nbtCompound2);
                player.dropItem(itemStack, true);
            }
        }
        stack.removeSubNbt(ITEMS_KEY);
        return true;
    }

    private static Stream<ItemStack> getBundledStacks(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound == null) {
            return Stream.empty();
        }
        NbtList nbtList = nbtCompound.getList(ITEMS_KEY, NbtElement.COMPOUND_TYPE);
        return nbtList.stream().map(NbtCompound.class::cast).map(ItemStack::fromNbt);
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.of();
        Bundle.getBundledStacks(stack).forEach(defaultedList::add);
        return Optional.of(new BundleTooltipData(defaultedList, Bundle.getBundleOccupancy(stack)));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.minecraft.bundle.fullness", Bundle.getBundleOccupancy(stack), MAX_STORAGE).formatted(Formatting.GRAY));
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        ItemUsage.spawnItemContents(entity, Bundle.getBundledStacks(entity.getStack()));
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8f, 0.8f + entity.getWorld().getRandom().nextFloat() * 0.4f);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8f, 0.8f + entity.getWorld().getRandom().nextFloat() * 0.4f);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, 0.8f, 0.8f + entity.getWorld().getRandom().nextFloat() * 0.4f);
    }
}
