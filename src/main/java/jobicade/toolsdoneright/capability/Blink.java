package jobicade.toolsdoneright.capability;

/**
 * Simple object for blinking animation data.
 */
public class Blink {
    /**
     * The number of ticks until the start of the next blink animation.
     */
    private long nextBlink;
    /**
     * The number of blinks in the next animation.
     */
    private int blinkCount;

    /**
     * Getter for {@code nextBlink}.
     * @return The number of ticks until the start of the next blink animation.
     */
    public long getNextBlink() {
        return nextBlink;
    }

    /**
     * Setter for {@code nextBlink}.
     * @param nextBlink The number of ticks until the start of the next blink animation.
     */
    public void setNextBlink(long nextBlink) {
        this.nextBlink = nextBlink;
    }

    /**
     * Getter for {@code blinkCount}.
     * @return The number of blinks in the next animation.
     */
    public int getBlinkCount() {
        return blinkCount;
    }

    /**
     * Setter for {@code blinkCount}.
     * @param blinkCount The number of blinks in the next animation.
     */
    public void setBlinkCount(int blinkCount) {
        this.blinkCount = blinkCount;
    }
}
