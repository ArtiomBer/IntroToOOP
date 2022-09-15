// artiom Berengard

package Sprites;

import Collision_Detection.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is in charge of the second level.
 */
public class WideEasyLevel implements LevelInformation {
    private int numOfBalls = 10;
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity ball1Velocity = new Velocity(-1, -2);
        velocities.add(ball1Velocity);
        Velocity ball2Velocity = new Velocity(-1, -2);
        velocities.add(ball2Velocity);
        Velocity ball3Velocity = new Velocity(-1, -2);
        velocities.add(ball3Velocity);
        Velocity ball4Velocity = new Velocity(-1, -2);
        velocities.add(ball4Velocity);
        Velocity ball5Velocity = new Velocity(0, -2);
        velocities.add(ball5Velocity);
        Velocity ball6Velocity = new Velocity(0, -2);
        velocities.add(ball6Velocity);
        Velocity ball7Velocity = new Velocity(1, -2);
        velocities.add(ball7Velocity);
        Velocity ball8Velocity = new Velocity(1, -2);
        velocities.add(ball8Velocity);
        Velocity ball9Velocity = new Velocity(1, -2);
        velocities.add(ball9Velocity);
        Velocity ball10Velocity = new Velocity(1, -2);
        velocities.add(ball10Velocity);
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }
    @Override
    public int paddleWidth() {
        return 500;
    }
    @Override
    public String levelName() {
        return "Wide Easy";
    }
    @Override
    public Sprite getBackground() {

        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                Color sunSet = new Color(255, 226, 190);
                Block backGround = new Block(new Rectangle(new Point(25, 25),
                        800 - 25, 600 - 25));
                backGround.setColor(sunSet);
                d.setColor(sunSet);
                backGround.drawOn(d);
                Color yellow1 = new Color(242, 221, 12);
                Color yellow2 = new Color(246, 153, 50);
                Color yellow3 = new Color(247, 224, 80);
                d.setColor(yellow3);
                //Drawing the lines:
                double xVal = 700;
                for (int i = 0; i < 500; i++) {
                    d.drawLine(125, 125, (int) xVal, 250);
                    xVal *= 0.99;
                }
                d.setColor(yellow3);
                d.fillCircle(125, 125, 70);
                d.setColor(yellow2);
                d.fillCircle(125, 125, 60);
                d.setColor(yellow1);
                d.fillCircle(125, 125, 50);
            }

            @Override
            public void timePassed() {
            }
        };
        return sprite;
    }
    @Override
    public Color getColorBackGround() {
        return Color.white;
    }
    @Override
    public List<Block> blocks() {
        Block[] blocks = new Block[15];
        // Creating the row of blocks.
        double yIndex = 250;
        double xIndex = 25;
        double widthAddition = 50;
        double heightAddition = 20;
        for (int i = 0; i < 15; i++) {
            blocks[i] = new Block(new Rectangle(new Point(xIndex, yIndex),
                    widthAddition, heightAddition));
            xIndex = xIndex + widthAddition;
            if (i == 0 || i == 1) {
                blocks[i].setColor(Color.red);
            } else if (i == 2 || i == 3) {
                blocks[i].setColor(Color.orange);
            } else if (i == 4 || i == 5) {
                blocks[i].setColor(Color.yellow);
            } else if (i == 6 || i == 7 || i == 8) {
                blocks[i].setColor(Color.green);
            } else if (i == 9 || i == 10) {
                blocks[i].setColor(Color.blue);
            } else if (i == 11 || i == 12) {
                blocks[i].setColor(Color.pink);
            } else {
                blocks[i].setColor(Color.cyan);
            }
        }
        List<Block> blockList = new ArrayList<>();
        Collections.addAll(blockList, blocks);
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
