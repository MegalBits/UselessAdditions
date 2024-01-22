package net.megal.uselessadditions;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class EarlyRiser implements Runnable {
    private String enchantmentTarget;
    public static final String ANY = "ANY";
    public static final String HAMMER = "HAMMER";
    public static final String SCYTHE = "SCYTHE";
    public static final String SWEEPING = "SWEEPING";
    public static final String TOOL = "TOOL";
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
        addEnchantmentTarget(ANY, "AnyEnchantmentTarget");
        addEnchantmentTarget(HAMMER, "HammerEnchantmentTarget");
        addEnchantmentTarget(SCYTHE, "ScytheEnchantmentTarget");
        addEnchantmentTarget(SWEEPING, "SweepingEnchantmentTarget");
        addEnchantmentTarget(TOOL, "ToolEnchantmentTarget");
    }

    private void addEnchantmentTarget(String name, String className) {
        ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass(name, "net.megal.uselessadditions.enchantment.target." + className).build();
    }
}
