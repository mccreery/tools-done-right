package jobicade.toolsdoneright.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Thin wrapper for getting the blinker for an stack.
 */
public class BlinkProvider implements ICapabilityProvider {
    @CapabilityInject(Blink.class)
    public static final Capability<Blink> CAP_BLINK = null;

    /**
     * The blinker instance, one per stack/provider.
     */
    private final Blink blink;

    public BlinkProvider() {
        this.blink = CAP_BLINK.getDefaultInstance();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CAP_BLINK;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CAP_BLINK ? CAP_BLINK.cast(blink) : null;
    }
}
