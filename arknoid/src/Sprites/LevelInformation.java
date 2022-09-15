// Artiom Berengard

package Sprites;

import Collision_Detection.Velocity;
import java.awt.Color;
import java.util.List;

/**
 * This interface is in charge of holding the level information.
 */
public interface LevelInformation {
    /**
     * A getter for the number of balls in a level.
     * @return the number of balls in the level.
     */
    int numberOfBalls();
    /**
     * A getter method for the initial ball velocities.
     * @return the initial ball velocity values.
     */
    List<Velocity> initialBallVelocities();
    /**
     * A getter method.
     * @return the paddle speed.
     */
    int paddleSpeed();
    /**
     * A getter method.
     * @return the paddle width.
     */
    int paddleWidth();
    /**
     * A getter method.
     * @return the level name that will be displayed at the top of the screen.
     */
    String levelName();
    /**
     * A getter method.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * A getter method.
     * @return the color of the background.
     */
    Color getColorBackGround();
    /**
     * A getter method.
     * @return the blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();
    /**
     * A getter method.
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
