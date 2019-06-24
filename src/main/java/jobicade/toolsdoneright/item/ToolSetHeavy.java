package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Identifier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ToolSetHeavy extends ToolSet {
    public ToolSetHeavy(ToolMaterial material, Identifier baseName) {
        super(material, baseName);
    }

    @Override
    protected Item createItem(ToolType type) {
        switch(type) {
            case SWORD: return new ItemHeavySword(material);
            case PICKAXE: return new ItemHeavyPickaxe(material);
            case AXE: return new ItemHeavyAxe(material);
            case SHOVEL: return new ItemHeavyShovel(material);
            default: return super.createItem(type);
        }
    }
}
