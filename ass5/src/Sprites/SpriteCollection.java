// Artiom Berengard
package Sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is in charge creating a list of sprites, notify all the
 * sprites that time passed and draw all of them to the draw surface.
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * The constructor method.
     * @param sprites is a given list of sprites which
     *                    we will set.
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }
    /**
     * The constructor method, creates a new sprites list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * This method will add a sprite to the list.
     * @param s is the given sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * This method will return the sprites list.
     * @return is the sprites list.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
    /**
     * This method will call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite: this.sprites) {
            sprite.timePassed();
        }
    }
    /**
     * This method will call drawOn(d) on all sprites.
     * @param d is the given draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: this.sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * This method is in charge of removing a sprite from the list.
     * @param s is the given sprite to remove.
     */
    public void removeSprite(Sprite s) {
        if (sprites.contains(s)) {
            this.sprites.remove(s);
        }
    }
}