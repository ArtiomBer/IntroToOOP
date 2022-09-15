// Artiom Berengard

package Sprites;

/**
 * This class is in charge of tracking the score and update it with each
 * hit of a block.
 * This method has a score counter that represents the current score in the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * This is the setter method.
     * @param scoreCounter is the given score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This method is in charge of increasing the score by 5 points every
     * time a ball is hitting a block.
     * @param beingHit is the block being hit.
     * @param hitter is the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
