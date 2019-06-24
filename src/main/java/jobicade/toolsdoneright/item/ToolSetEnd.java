package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Identifier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ToolSetEnd extends ToolSet {
    public ToolSetEnd(ToolMaterial material, Identifier baseName) {
        super(material, baseName);
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
