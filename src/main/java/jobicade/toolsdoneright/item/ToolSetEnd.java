package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Identifier;
import jobicade.toolsdoneright.Items;
import net.minecraft.item.Item;

public class ToolSetEnd extends ToolSet {
    public ToolSetEnd() {
        super(Items.END, new Identifier("end"));
    }

    @Override
    protected Item createItem(ToolType type) {
        switch(type) {
            case SWORD: return new ItemEndSword(material);
            case PICKAXE: return new ItemEndPickaxe(material);
            case AXE: return new ItemEndAxe(material);
            case SHOVEL: return new ItemEndShovel(material);
            case HOE: return new ItemEndHoe(material);
            default: return super.createItem(type);
        }
    }
}
