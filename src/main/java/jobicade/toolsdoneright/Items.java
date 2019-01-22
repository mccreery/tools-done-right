package jobicade.toolsdoneright;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static jobicade.toolsdoneright.Identifier.Format.*;

@EventBusSubscriber
public class Items {
    public static final ToolMaterial GEM      = EnumHelper.addToolMaterial("gem", 2, 200, 10.0f, 2.5f, 30);
    public static final ToolMaterial OBSIDIAN = EnumHelper.addToolMaterial("obsidian", 3, 4000, 3.5f, 2.0f, 5);
    public static final ToolMaterial END      = EnumHelper.addToolMaterial("end", 3, 1000, 8.0f, 3.0f, 20);

    public static final Item emeraldPickaxe = new ItemEmeraldPickaxe();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Identifier name = new Identifier("emeraldPickaxe");

        emeraldPickaxe.setRegistryName(name.format(SNAKE));
        emeraldPickaxe.setTranslationKey(name.format(HEADLESS));
        ToolsDoneRight.proxy.registerDefaultItemModel(emeraldPickaxe);
        event.getRegistry().register(emeraldPickaxe);
    }
}
