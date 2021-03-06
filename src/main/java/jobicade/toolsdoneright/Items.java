package jobicade.toolsdoneright;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import jobicade.toolsdoneright.item.ToolSet;
import jobicade.toolsdoneright.item.ToolSetEmerald;
import jobicade.toolsdoneright.item.ToolSetEnd;
import jobicade.toolsdoneright.item.ToolSetHeavy;

import static jobicade.toolsdoneright.Identifier.Format.*;

@EventBusSubscriber
public class Items {
    public static final ToolMaterial GEM      = EnumHelper.addToolMaterial("gem", 2, 200, 10.0f, 2.5f, 30);
    public static final ToolMaterial HEAVY    = EnumHelper.addToolMaterial("heavy", 3, 2000, 5.5f, 2.0f, 5);
    public static final ToolMaterial END      = EnumHelper.addToolMaterial("end", 3, 1000, 8.0f, 3.0f, 20);

    public static final ToolSet EMERALD_TOOLS  = new ToolSetEmerald(GEM, new Identifier("emerald"));
    public static final ToolSet RUBY_TOOLS     = new ToolSet(GEM, new Identifier("ruby"));
    public static final ToolSet TOPAZ_TOOLS    = new ToolSet(GEM, new Identifier("topaz"));
    public static final ToolSet SAPPHIRE_TOOLS = new ToolSet(GEM, new Identifier("sapphire"));
    public static final ToolSet HEAVY_TOOLS    = new ToolSetHeavy(HEAVY, new Identifier("heavy"));
    public static final ToolSet END_TOOLS      = new ToolSetEnd(END, new Identifier("end"));
    private static final Set<ToolSet> TOOL_SETS = ImmutableSet.of(EMERALD_TOOLS, RUBY_TOOLS, TOPAZ_TOOLS, SAPPHIRE_TOOLS, HEAVY_TOOLS, END_TOOLS);

    public static final Item RUBY = setNames(new Item().setCreativeTab(CreativeTabs.MATERIALS), new Identifier("ruby"));
    public static final Item TOPAZ = setNames(new Item().setCreativeTab(CreativeTabs.MATERIALS), new Identifier("topaz"));
    public static final Item SAPPHIRE = setNames(new Item().setCreativeTab(CreativeTabs.MATERIALS), new Identifier("sapphire"));
    private static final Set<Item> ITEMS = ImmutableSet.of(RUBY, TOPAZ, SAPPHIRE);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Collection<Item> items = new ArrayList<>();
        for(ToolSet set : TOOL_SETS) {
            items.addAll(set.getItems());
        }
        items.addAll(ITEMS);

        for(Block block : Blocks.BLOCKS) {
            items.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
        event.getRegistry().registerAll(items.toArray(new Item[items.size()]));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for(ToolSet set : TOOL_SETS) {
            set.registerModels();
        }

        for(Item item : ITEMS) {
            registerDefaultModel(item);
        }

        for(Block block : Blocks.BLOCKS) {
            registerDefaultModel(Item.getItemFromBlock(block));
        }
    }

    /**
     * Helper method for items with one enchantment.
     */
    public static ItemStack getStackWithEnchantment(Item item, Enchantment enchantment, int level) {
        ItemStack stack = new ItemStack(item);
        stack.addEnchantment(enchantment, level);
        return stack;
    }

    /**
     * Sets common names for all items with correct formatting.
     */
    public static Item setNames(Item item, Identifier identifier) {
        item.setRegistryName(identifier.format(SNAKE));
        item.setTranslationKey(identifier.format(HEADLESS));
        return item;
    }

    /**
     * Registers a model with the same name as the item for meta 0.
     */
    public static Item registerDefaultModel(Item item) {
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), null);
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
        return item;
    }
}
