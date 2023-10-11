package net.megal.uselessadditions.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.megal.uselessadditions.UAdd;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class UScreens {
    public static final ScreenHandlerType<EnhancementScreenHandler> ENHANCEMENT_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(UAdd.MOD_ID, "enhancement_table"), EnhancementScreenHandler::new);
    public static void loadStuff() {}
}
