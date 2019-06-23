package jobicade.toolsdoneright;

import org.apache.logging.log4j.Logger;

import jobicade.toolsdoneright.capability.Blink;
import jobicade.toolsdoneright.capability.StorageStub;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ToolsDoneRight.MODID, name = "Tools Done Right", version = "2.0-alpha")
public class ToolsDoneRight {
    public static final String MODID = "toolsdoneright";

    private static Logger logger;
    public static Logger getLogger() { return logger; }

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        CapabilityManager.INSTANCE.register(Blink.class, new StorageStub<Blink>(), Blink::new);
    }
}
