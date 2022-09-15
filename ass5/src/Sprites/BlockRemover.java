// Artiom Berengard

package Sprites;

/**
 * A BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    /**
     * A BlockRemover is in charge of removing blocks from the game.
     * @param removedBlocks is the counter of the removed blocks.
     * @param game is the given game to remove the block from.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = new Counter(0);
    }
    /**
     * This is a getter method.
     * @return the number of remaining balls.
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }
    /**
     * This method is in charge of responding to a hit, this means to remove
     * the block from the game, from the listeners and decrease the number of
     * blocks in the game.
     * @param beingHit is the block that has been hit.
     * @param hitter is the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
