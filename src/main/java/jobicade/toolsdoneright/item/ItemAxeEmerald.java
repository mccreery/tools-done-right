package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemAxeEmerald extends ItemAxe {
    public ItemAxeEmerald(ToolMaterial material) {
        super(material, 8.0f, -3.2f);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(isInCreativeTab(tab)) {
            items.add(Items.getStackWithEnchantment(this, Enchantments.FORTUNE, 1));
        }
    }
}
