package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemPickaxeEmerald extends ItemPickaxe {
    public ItemPickaxeEmerald(ToolMaterial material) {
        super(material);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(isInCreativeTab(tab)) {
            items.add(Items.getStackWithEnchantment(this, Enchantments.FORTUNE, 1));
        }
    }
}
