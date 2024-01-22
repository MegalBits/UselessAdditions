package net.megal.uselessadditions.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.megal.uselessadditions.screen.EnhancementScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class UScreenRendering {
    public static void registerScreens() {
        HandledScreens.register(net.megal.uselessadditions.screen.UScreens.ENHANCEMENT_SCREEN_HANDLER, EnhancementScreen::new);
    }
}
