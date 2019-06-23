package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Identifier;
import jobicade.toolsdoneright.Items;
import net.minecraft.item.Item;

public class ToolSetEmerald extends ToolSet {
    public ToolSetEmerald() {
        super(Items.GEM, new Identifier("emerald"));
    }

    @Override
    protected Item createItem(ToolType type) {
        switch(type) {
            case SWORD: return new ItemEmeraldSword(material);
            case PICKAXE: return new ItemEmeraldPickaxe(material);
            case AXE: return new ItemEmeraldAxe(material);
            case SHOVEL: return new ItemEmeraldShovel(material);
            case HOE: return new ItemEmeraldHoe(material);
            default: return super.createItem(type);
        }
    }
}
