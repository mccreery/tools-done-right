package jobicade.toolsdoneright.item;

import jobicade.toolsdoneright.Identifier;
import jobicade.toolsdoneright.Identifier.Format;
import jobicade.toolsdoneright.Items;
import jobicade.toolsdoneright.ToolsDoneRight;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

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

    @Override
    protected void registerModels(ToolType type, Item item) {
        if(type == ToolType.SWORD) {
            ModelResourceLocation openModel = new ModelResourceLocation(item.getRegistryName(), "inventory");

            ResourceLocation closed = new ResourceLocation(ToolsDoneRight.MODID, getIdentifier(type).concat("closed").format(Format.SNAKE));
            ModelResourceLocation closedModel = new ModelResourceLocation(closed, "inventory");

            ModelBakery.registerItemVariants(item, openModel, closedModel);
            ModelLoader.setCustomMeshDefinition(item, stack -> {
                return ItemSwordEnd.hasCapture(stack) ? closedModel : openModel;
            });
        } else {
            super.registerModels(type, item);
        }
    }
}
