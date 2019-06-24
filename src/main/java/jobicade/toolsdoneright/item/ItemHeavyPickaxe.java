package jobicade.toolsdoneright.item;

import net.minecraft.item.ItemPickaxe;

public class ItemHeavyPickaxe extends ItemPickaxe {
    public ItemHeavyPickaxe(ToolMaterial material) {
        super(material);
        this.attackDamage += 2.0f;
        this.attackSpeed -= 0.2f;
    }
}
