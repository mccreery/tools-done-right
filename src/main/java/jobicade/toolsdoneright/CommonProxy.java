package jobicade.toolsdoneright;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class CommonProxy {
    public void onClient(Runnable runnable) {}

    public final void registerDefaultItemModel(Item item) {
        onClient(() -> {
            ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), null);
            ModelLoader.setCustomModelResourceLocation(item, 0, location);
        });
    }
}
