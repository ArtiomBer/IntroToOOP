// Artiom Berengard

package Sprites;
import biuoop.DrawSurface;
/**
 * This interface is in charge of the animations.
 * Each animation can run with the doOneFrame method and can be
 * stopped with the shouldStop method.
 */
public interface Animation {
    /**
     * This method is in charge of showing a frame of the animation.
     * @param d is the given draw surface so that the animation will
     *          be drawn onto it.
     */
    void doOneFrame(DrawSurface d);
    /**
     * This method is in charge of stopping the animation.
     * @return value is a boolean value, true or false.
     */
    boolean shouldStop();
}
