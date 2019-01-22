package jobicade.toolsdoneright;

import net.minecraft.item.Item;

public class ToolSetEmerald extends ToolSet {
    public ToolSetEmerald() {
        super(Items.GEM, new Identifier("emerald"));
    }

    @Override
    protected Item createItem(ToolType type) {
        switch(type) {
            case SWORD: return new ItemSwordEmerald(material);
            case PICKAXE: return new ItemPickaxeEmerald(material);
            case AXE: return new ItemAxeEmerald(material);
            case SHOVEL: return new ItemShovelEmerald(material);
            case HOE: return new ItemHoeEmerald(material);
            default: return super.createItem(type);
        }
    }
}
