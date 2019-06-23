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
            case SWORD: return new ItemSwordEnd();
            default: return super.createItem(type);
        }
    }
}
