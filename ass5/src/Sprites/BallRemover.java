// Artiom Berengard

package Sprites;

/**
 * This class is in charge of removing a ball from the game.
 * This class holds onto a reference to the game and the number of
 * the remaining balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    /**
     * This method is removing a ball from the game.
     * @param game is the given game to remove the ball from.
     * @param removedBlocks is the number of the blocks that has been removed.
     */
    public BallRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = new Counter(0);
    }
    /**
     * This is a getter method.
     * @return value is the number of remaining balls.
     */
    public int getRemainingBlocks() {
        return this.remainingBalls.getValue();
    }
    /**
     * This method is in charge of responding to a hit, this means to remove
     * the ball from the game, from the listeners and decrease the number of
     * balls in the game.
     * @param beingHit is the block that has been hit.
     * @param hitter is the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
    }
}
