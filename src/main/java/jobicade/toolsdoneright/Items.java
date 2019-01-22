package jobicade.toolsdoneright;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

@EventBusSubscriber
public class Items {
    public static final ToolMaterial GEM      = EnumHelper.addToolMaterial("gem", 2, 200, 10.0f, 2.5f, 30);
    public static final ToolMaterial OBSIDIAN = EnumHelper.addToolMaterial("obsidian", 3, 4000, 3.5f, 2.0f, 5);
    public static final ToolMaterial END      = EnumHelper.addToolMaterial("end", 3, 1000, 8.0f, 3.0f, 20);

    public static final ToolSet EMERALD_TOOLS  = new ToolSet(GEM, new Identifier("emerald"));
    public static final ToolSet RUBY_TOOLS     = new ToolSet(GEM, new Identifier("ruby"));
    public static final ToolSet SAPPHIRE_TOOLS = new ToolSet(GEM, new Identifier("sapphire"));
    public static final ToolSet OBSIDIAN_TOOLS = new ToolSet(GEM, new Identifier("obsidian"));
    public static final ToolSet END_TOOLS      = new ToolSet(GEM, new Identifier("end"));
    private static final Set<ToolSet> TOOL_SETS = ImmutableSet.of(EMERALD_TOOLS, RUBY_TOOLS, SAPPHIRE_TOOLS, OBSIDIAN_TOOLS, END_TOOLS);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(ToolSet set : TOOL_SETS) {
            Collection<Item> items = set.getItems();
            event.getRegistry().registerAll(items.toArray(new Item[items.size()]));
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for(ToolSet set : TOOL_SETS) {
            set.registerModels();
        }
    }
}
