// Artiom Berengard

package Sprites;

import Collision_Detection.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of the firs level of the game.
 */
public class DirectHitLevel implements LevelInformation {
    private int numOfBalls = 1;

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity ball1Velocity = new Velocity(0, -2);
        velocities.add(ball1Velocity);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {

        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                Block backGround = new Block(new Rectangle(new Point(25, 25),
                        800 - 25, 600 - 25));
                backGround.setColor(Color.black);
                d.setColor(Color.black);
                backGround.drawOn(d);
                d.setColor(Color.blue);
                d.drawLine(400, 140, 400, 35);
                d.drawLine(400, 190, 400, 295);
                d.drawLine(375, 165, 270, 165);
                d.drawLine(425, 165, 530, 165);
                d.drawCircle(400, 165, 100);
                d.drawCircle(400, 165, 75);
                d.drawCircle(400, 165, 50);
            }

            @Override
            public void timePassed() {

            }
        };
        return sprite;
    }

    @Override
    public Color getColorBackGround() {
        return Color.black;
    }

    @Override
    public List<Block> blocks() {
        Block block1 = new Block(new Rectangle(new Point(385, 150),
                30, 30));
        block1.setColor(Color.red);
        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
