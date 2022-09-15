// Artiom Berengard

package Sprites;

/**
 * This interface is in charge of observing the game and wait until
 * updated of a hit. Whenever a hit occurs, the hitEvent method is
 * being activated.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param hitter is the hitting ball.
     * @param beingHit is the object being hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

