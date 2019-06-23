package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.BlinkProperty;
import jobicade.toolsdoneright.capability.BlinkProvider;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemEndAxe extends ItemAxe {
    public ItemEndAxe(ToolMaterial material) {
        super(material, 8.0f, -3.2f);
        addPropertyOverride(BlinkProperty.PROP_KEY, BlinkProperty.getInstance());
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BlinkProvider();
    }
}
