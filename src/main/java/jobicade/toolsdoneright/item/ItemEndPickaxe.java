package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.BlinkProperty;
import jobicade.toolsdoneright.capability.BlinkProvider;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemEndPickaxe extends ItemPickaxe {
    public ItemEndPickaxe(ToolMaterial material) {
        super(material);
        addPropertyOverride(BlinkProperty.PROP_KEY, BlinkProperty.getInstance());
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BlinkProvider();
    }
}
