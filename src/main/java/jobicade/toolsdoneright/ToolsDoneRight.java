package jobicade.toolsdoneright;

import org.apache.logging.log4j.Logger;

import jobicade.toolsdoneright.item.ToolSet.ToolType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ToolsDoneRight.MODID, name = "Tools Done Right", version = "2.0-alpha")
@EventBusSubscriber
public class ToolsDoneRight {
    public static final String MODID = "toolsdoneright";

    private static Logger logger;
    public static Logger getLogger() { return logger; }

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
    }

    @SubscribeEvent
    public static void onHarvestDrops(HarvestDropsEvent event) {
        EntityPlayer player = event.getHarvester();

        if(player != null) {
            ItemStack heldStack = player.getHeldItemMainhand();

            if(heldStack.getItem() == Items.END_TOOLS.getItem(ToolType.PICKAXE)) {
                for(int i = 0; i < event.getDrops().size(); i++) {
                    ItemStack stack = event.getDrops().get(i);
                    stack = player.getInventoryEnderChest().addItem(stack);
                    event.getDrops().set(i, stack);
                }
            }
        }
    }
}
