package jobicade.toolsdoneright;

import jobicade.toolsdoneright.block.BlockOreInject;
import jobicade.toolsdoneright.block.BlockResource;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static jobicade.toolsdoneright.Identifier.Format.*;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

@EventBusSubscriber
public class Blocks {
    public static final Block RUBY_ORE = setNames(new BlockOreInject(Items.RUBY, 2, 5), new Identifier("rubyOre"));
    public static final Block TOPAZ_ORE = setNames(new BlockOreInject(Items.TOPAZ, 2, 5), new Identifier("topazOre"));
    public static final Block SAPPHIRE_ORE = setNames(new BlockOreInject(Items.SAPPHIRE, 2, 5), new Identifier("sapphireOre"));

    public static final Block RUBY_BLOCK = setNames(new BlockResource(Material.IRON, MapColor.RED), new Identifier("rubyBlock"));
    public static final Block TOPAZ_BLOCK = setNames(new BlockResource(Material.IRON, MapColor.ADOBE), new Identifier("topazBlock"));
    public static final Block SAPPHIRE_BLOCK = setNames(new BlockResource(Material.IRON, MapColor.BLUE), new Identifier("sapphireBlock"));

    public static final Set<Block> BLOCKS = ImmutableSet.of(RUBY_ORE, TOPAZ_ORE, SAPPHIRE_ORE, RUBY_BLOCK, TOPAZ_BLOCK, SAPPHIRE_BLOCK);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BLOCKS.toArray(new Block[BLOCKS.size()]));
    }

    /**
     * Sets common names for all blocks with correct formatting.
     */
    public static Block setNames(Block block, Identifier identifier) {
        block.setRegistryName(identifier.format(SNAKE));
        block.setTranslationKey(identifier.format(HEADLESS));
        return block;
    }
}
