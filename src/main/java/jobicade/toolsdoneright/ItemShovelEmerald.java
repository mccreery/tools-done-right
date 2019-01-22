package jobicade.toolsdoneright;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemShovelEmerald extends ItemSpade {
    public ItemShovelEmerald(ToolMaterial material) {
        super(material);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(isInCreativeTab(tab)) {
            items.add(Items.getStackWithEnchantment(this, Enchantments.FORTUNE, 1));
        }
    }
}
