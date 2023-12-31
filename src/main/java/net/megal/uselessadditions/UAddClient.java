package net.megal.uselessadditions;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.megal.uselessadditions.block.SieveEntityRenderer;
import net.megal.uselessadditions.block.SurvivalSpawnerEntityRenderer;
import net.megal.uselessadditions.block.UBlocks;
import net.megal.uselessadditions.item.UItems;
import net.megal.uselessadditions.screen.EnhancementScreen;
import net.megal.uselessadditions.screen.UScreens;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class UAddClient implements ClientModInitializer {
	public static final String MOD_ID = "uselessadditions";
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.SIEVE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.EMPTY_SPAWNER, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(UBlocks.SURVIVAL_SPAWNER, RenderLayer.getCutoutMipped());

		BlockEntityRendererFactories.register(UBlocks.SIEVE_ENTITY, SieveEntityRenderer::new);
		BlockEntityRendererFactories.register(UBlocks.SURVIVAL_SPAWNER_ENTITY, SurvivalSpawnerEntityRenderer::new);

		HandledScreens.register(UScreens.ENHANCEMENT_SCREEN_HANDLER, EnhancementScreen::new);

		bundlePredicate(UItems.BUNDLE);
		bundlePredicate(UItems.IRON_BUNDLE);
		bundlePredicate(UItems.DIAMOND_BUNDLE);
		bundlePredicate(UItems.NETHERITE_BUNDLE);
		bundlePredicate(UItems.DRAGON_BUNDLE);
	}
	private void bundlePredicate(Item item) {
		ModelPredicateProviderRegistry.register(item, new Identifier("filled"), (stack, world, entity, seed) -> BundleItem.getAmountFilled((ItemStack)stack));
	}
}