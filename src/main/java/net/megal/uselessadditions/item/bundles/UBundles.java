package net.megal.uselessadditions.item.bundles;

import net.megal.uselessadditions.UAdd;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class UBundles {
    //Please rework this in future
    //Just make a parent class for all the bundles that extends Item and add a getBundleSpace method for them to each override assuming that the stack is an instance of the parent class

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
        else if (stack.getItem() instanceof TraderSatchel) {
            return TraderSatchel.BUNDLE_ITEM_OCCUPANCY + occupancy;
        }
        else if (stack.isIn(UAdd.BUNDLES)) {
            return 4 + occupancy;
        }
        else return 0;
    }
    public static int getBundleColor(float occupancy, float max) {
        return MathHelper.packRgb(1.0f, 0.0f, 0.25f);
    }
}
