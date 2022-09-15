// Artiom Berengard
package Sprites;
import biuoop.DrawSurface;
/**
 * This interface is in charge of the game objects that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * This method is in charge of drawing the sprite to the draw surface.
     * @param d is the given draw surface.
     */
    void drawOn(DrawSurface d);
    /**
     * This method is in charge of notifying the sprite that time has passed.
     */
    void timePassed();
}