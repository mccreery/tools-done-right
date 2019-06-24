package jobicade.toolsdoneright.item;

import net.minecraft.item.ItemSpade;

public class ItemHeavyShovel extends ItemSpade {
    public ItemHeavyShovel(ToolMaterial material) {
        super(material);
        this.attackDamage += 2.0f;
        this.attackSpeed -= 0.2f;
    }
}
