// Artiom Berengard

package Sprites;

/**
 * This interface is in charge of observing the game and update all the
 * observers whenever a hit occurs, we can add or remove observers.
 */
public interface HitNotifier {
    /**
     * This method is in charge of adding a listener to hit events.
     * @param hl is the hit listener we want to add.
     */
    void addHitListener(HitListener hl);
    /**
     * This method is in charge of removing a listener from hit events.
     * @param hl is the hit listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}
