package net.megal.uselessadditions;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.screen.EnhancementScreen;
import net.megal.uselessadditions.screen.UScreens;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class UAddClient implements ClientModInitializer {
	public static final String MOD_ID = "uselessadditions";
	public static final KeyBinding EXPAND_TOOLTIP = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.uselessadditions.description", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "category.uselessadditions.keys"));
	@Override
	public void onInitializeClient() {
		//BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.block, RenderLayer.getCutoutMipped());
		HandledScreens.register(UScreens.ENHANCEMENT_SCREEN_HANDLER, EnhancementScreen::new);

		bundlePredicate(UItems.BUNDLE);
		bundlePredicate(UItems.IRON_BUNDLE);
		bundlePredicate(UItems.DIAMOND_BUNDLE);
		bundlePredicate(UItems.NETHERITE_BUNDLE);
		bundlePredicate(UItems.DRAGON_BUNDLE);

//		ClientTickEvents.END_CLIENT_TICK.register(client -> {
//			if (client.player != null) {
//				client.player.sendMessage(Text.literal(String.valueOf(EXPAND_TOOLTIP.isPressed())));
//				PacketByteBuf buf = PacketByteBufs.create();
//				buf.writeBoolean(EXPAND_TOOLTIP.isPressed());
//				ClientPlayNetworking.send(new Identifier(MOD_ID, "expanded_descriptions"), buf);
//			}
//		});
	}
	private void bundlePredicate(Item item) {
		ModelPredicateProviderRegistry.register(item, new Identifier("filled"), (stack, world, entity, seed) -> BundleItem.getAmountFilled((ItemStack)stack));
	}
}