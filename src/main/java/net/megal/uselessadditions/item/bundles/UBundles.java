package net.megal.uselessadditions.item.bundles;

import net.megal.uselessadditions.UAdd;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class UBundles {
    //Sends back the amount of additional space that a bundle will take up whilst inside another
    public static int getBundleSpace(ItemStack stack, int occupancy) {
        //Would use switch here but idk how to use it with instanceof
        if (stack.getItem() instanceof Bundle) {
            return Bundle.BUNDLE_ITEM_OCCUPANCY + occupancy;
        }
        else if (stack.getItem() instanceof IronBundle) {
            return IronBundle.BUNDLE_ITEM_OCCUPANCY + occupancy;
        }
        else if (stack.getItem() instanceof DiamondBundle) {
            return DiamondBundle.BUNDLE_ITEM_OCCUPANCY + occupancy;
        }
        else if (stack.getItem() instanceof NetheriteBundle) {
            return NetheriteBundle.BUNDLE_ITEM_OCCUPANCY + occupancy;
        }
        else if (stack.getItem() instanceof DragonBundle) {
            return DragonBundle.BUNDLE_ITEM_OCCUPANCY + occupancy;
        }
        else if (stack.isIn(UAdd.BUNDLES)) {
            return 4 + occupancy;
        }
        else return 0;
    }
    public static int getBundleColor(float occupancy, float max) {
        return MathHelper.packRgb(1.0f, 0.0f, Math.max((occupancy / max) * 1.25f, 0.0f));
    }
}
