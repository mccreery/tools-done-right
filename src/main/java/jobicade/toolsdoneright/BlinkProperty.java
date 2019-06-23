package jobicade.toolsdoneright;

import jobicade.toolsdoneright.capability.Blink;
import jobicade.toolsdoneright.capability.BlinkProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * A singleton item property describing the current state of the blinking animation.
 * Only works for items with the blink capability.
 *
 * <p>The property will increase from 0 to 1 while blinking, then reset to 0.
 * The default models switch to the closed texture starting at 0.5.
 *
 * @see BlinkProvider
 */
public class BlinkProperty implements IItemPropertyGetter {
    private static final BlinkProperty INSTANCE = new BlinkProperty();

    private static final int BLINK_DURATION = 4;
    private static final int BLINK_PERIOD = 6;
    private static final int BLINK_DELAY_MIN = 200;
    private static final int BLINK_DELAY_MAX = 3000;

    public static final ResourceLocation PROP_KEY = new ResourceLocation(ToolsDoneRight.MODID, "blink");

    public static BlinkProperty getInstance() {
        return INSTANCE;
    }

    @Override
    public float apply(ItemStack stack, World world, EntityLivingBase entity) {
        if(!stack.hasCapability(BlinkProvider.CAP_BLINK, null)) return 0;

        Blink blinkData = stack.getCapability(BlinkProvider.CAP_BLINK, null);
        long tick = Minecraft.getMinecraft().world.getTotalWorldTime() - blinkData.getNextBlink();

        // Wait for next blink
        if(tick >= 0) {
            if(tick < blinkData.getBlinkCount() * BLINK_PERIOD) {
                // Animation playing
                float prop = (tick + Minecraft.getMinecraft().getRenderPartialTicks())
                        % BLINK_PERIOD / BLINK_DURATION;

                if(prop < 1) return prop;
            } else {
                // Schedule next blink
                blinkData.setNextBlink(Minecraft.getMinecraft().world.getTotalWorldTime()
                        + BLINK_DELAY_MIN + Minecraft.getMinecraft().world.rand
                                .nextInt(BLINK_DELAY_MAX - BLINK_DELAY_MIN));

                // 1 blink is most common
                int blinks = Minecraft.getMinecraft().world.rand.nextInt(5);
                if(blinks >= 3) blinks = 0;
                blinkData.setBlinkCount(1 + blinks);
            }
        }
        return 0;
    }
}
