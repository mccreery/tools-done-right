package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.BlinkProperty;
import jobicade.toolsdoneright.capability.BlinkProvider;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemEndHoe extends ItemHoe {
    public ItemEndHoe(ToolMaterial material) {
        super(material);
        addPropertyOverride(BlinkProperty.PROP_KEY, BlinkProperty.getInstance());
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BlinkProvider();
    }
}
