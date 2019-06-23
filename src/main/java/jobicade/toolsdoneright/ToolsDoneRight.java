package jobicade.toolsdoneright;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
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
    public static void onWorldLoad(WorldEvent.Load event) {
        if(!event.getWorld().isRemote) return;

        event.getWorld().addEventListener(new WorldEventListenerStub() {
            @Override
            public void playEvent(EntityPlayer player, int type, BlockPos pos, int data) {
                if(player == null) {
                    player = Minecraft.getMinecraft().player;
                }

                if(type == 2001 && shouldUseEnderChest(player)) {
                    particleEffect(player.getEntityWorld(), player.getRNG(),
                            pos.getX(), pos.getY(), pos.getZ(), 1.0, 1.0, 1.0,
                            EnumParticleTypes.PORTAL, 5);
                }
            }
        });
    }

    @SubscribeEvent
    public static void onHarvestDrops(HarvestDropsEvent event) {
        EntityPlayer player = event.getHarvester();

        if(player != null && shouldUseEnderChest(player)) {
            for(int i = 0; i < event.getDrops().size(); i++) {
                ItemStack stack = event.getDrops().get(i);
                stack = player.getInventoryEnderChest().addItem(stack);
                event.getDrops().set(i, stack);
            }
        }
    }

    private static boolean shouldUseEnderChest(EntityPlayer player) {
        return player != null && !player.getHeldItemMainhand().isEmpty()
                && Items.END_TOOLS.getItems().contains(player.getHeldItemMainhand().getItem());
    }

    public static void particleEffect(EntityLivingBase entity, EnumParticleTypes particleType, int count) {
        particleEffect(entity.world, entity.getRNG(),
                entity.posX - entity.width / 2, entity.posY, entity.posZ - entity.width / 2,
                entity.width, entity.height, entity.width, particleType, count);
    }

    public static void particleEffect(World world, Random random,
            double minX, double minY, double minZ, double width, double height, double depth,
            EnumParticleTypes particleType, int count) {
        for(int i = 0; i < count; i++) {
            double x = minX + random.nextDouble() * width;
            double y = minY + random.nextDouble() * height;
            double z = minZ + random.nextDouble() * depth;

            double dx = (random.nextDouble() - 0.5) * 0.2;
            double dy = (random.nextDouble() - 0.5) * 0.2;
            double dz = (random.nextDouble() - 0.5) * 0.2;

            world.spawnParticle(particleType, x, y, z, dx, dy, dz);
        }
    }
}
