package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemEmeraldShovel extends ItemSpade {
    public ItemEmeraldShovel(ToolMaterial material) {
        super(material);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(isInCreativeTab(tab)) {
            items.add(Items.getStackWithEnchantment(this, Enchantments.FORTUNE, 1));
        }
    }
}
