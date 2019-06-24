package jobicade.toolsdoneright.event;

import jobicade.toolsdoneright.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class HeavyToolEvents {
    private static final float BASE_HARDNESS = 2.0f;
    private static final float MAX_HARDNESS = 50.0f;
    private static final float MAX_SPEED = 3.0f;

    /**
     * Used internally for calculating heavy speed factors.
     */
    private static final float COEFF = (MAX_SPEED - 1.0f) / pow8(MAX_HARDNESS - BASE_HARDNESS);

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();

        if(Items.HEAVY_TOOLS.getItems().contains(stack.getItem())
                && stack.getItem().getDestroySpeed(stack, event.getState()) > 1.0f) {
            float hardness = event.getState().getBlockHardness(
                    player.getEntityWorld(), event.getPos());

            float heavyFactor = getHeavyFactor(hardness);
            event.setNewSpeed(event.getNewSpeed() * heavyFactor);
        }
    }

    /**
     * Raises a number to the fixed power of 8.
     * @return {@code x} to the power of 8.
     */
    private static float pow8(float x) {
        x *= x; // ^2
        x *= x; // ^4
        x *= x; // ^8
        return x;
    }

    /**
     * Calculates the multiplier for heavy tools from the block hardness.
     * @return <ul>
     * <li>1 at {@code hardness == BASE_HARDNESS}
     * <li>{@code MAX_SPEED} at {@code hardness >= MAX_HARDNESS}
     * <li>A negative quadratic function between the two fixed points.
     * </ul>
     */
    private static float getHeavyFactor(float hardness) {
        float heavyFactor = MAX_SPEED;

        // Capped at turning point
        if(hardness <= MAX_HARDNESS) {
            heavyFactor -= COEFF * pow8(hardness - MAX_HARDNESS);
        }
        return heavyFactor;
    }
}
