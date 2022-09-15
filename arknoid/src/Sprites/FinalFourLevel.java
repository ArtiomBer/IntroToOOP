// Artiom Berengard

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
 * This class is in charge of the forth level.
 */
public class FinalFourLevel implements LevelInformation {
    private int numOfBalls = 3;

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity ball1Velocity = new Velocity(-2, -2);
        velocities.add(ball1Velocity);
        Velocity ball2Velocity = new Velocity(0.2, -3);
        velocities.add(ball2Velocity);
        Velocity ball3Velocity = new Velocity(2, -2);
        velocities.add(ball3Velocity);
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 10;
    }
    @Override
    public int paddleWidth() {
        return 130;
    }
    @Override
    public String levelName() {
        return "Final Four";
    }
    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                Color lightBlue = new Color(59, 127, 245);
                Block backGround = new Block(new Rectangle(new Point(25, 25),
                        800 - 25, 600 - 25));
                backGround.setColor(lightBlue);
                d.setColor(lightBlue);
                backGround.drawOn(d);
                Color darkerGray = new Color(150, 150, 150);
                Color lessDarkerGray = new Color(193, 193, 193);
                Color extraLightGray = new Color(230, 230, 230);
                d.setColor(Color.white);
                int xVal = 0;
                for (int i = 0; i <= 9; i++) {
                    d.drawLine(110 + xVal, 375, 50 + xVal, 800);
                    xVal += 10;
                }
                xVal = 0;
                for (int i = 0; i <= 9; i++) {
                    d.drawLine(550 + xVal, 475, 490 + xVal, 800);
                    xVal += 10;
                }
                d.setColor(extraLightGray);
                d.fillCircle(100, 375, 25);
                d.fillCircle(125, 385, 28);
                d.fillCircle(200, 385, 33);
                d.setColor(lessDarkerGray);
                d.fillCircle(135, 345, 30);
                d.setColor(darkerGray);
                d.fillCircle(180, 360, 40);
                d.fillCircle(155, 390, 25);
                d.setColor(extraLightGray);
                d.fillCircle(540, 475, 25);
                d.fillCircle(575, 485, 28);
                d.fillCircle(650, 480, 33);
                d.setColor(lessDarkerGray);
                d.fillCircle(575, 455, 30);
                d.setColor(darkerGray);
                d.fillCircle(630, 460, 40);
                d.fillCircle(605, 490, 25);
            }
            @Override
            public void timePassed() {
            }
        };
        return sprite;
    }
    @Override
    public Color getColorBackGround() {
        Color lightBlue = new Color(59, 127, 245);
        return lightBlue;
    }
    @Override
    public List<Block> blocks() {
        Block[] blocks = new Block[105];
        // Creating the row of blocks.
        double yIndex = 75;
        double xIndex = 725;
        double widthAddition = 50;
        double heightAddition = 20;
        Color currentColor = Color.gray;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                blocks[i * 15 + j] = new Block(new Rectangle(new Point(xIndex, yIndex),
                        widthAddition, heightAddition));
                if (i == 1) {
                    currentColor = Color.red;
                } else if (i == 2) {
                    currentColor = Color.yellow;
                } else if (i == 3) {
                    currentColor = Color.green;
                } else if (i == 4) {
                    currentColor = Color.white;
                } else if (i == 5) {
                    currentColor = Color.pink;
                } else if (i == 6) {
                    currentColor = Color.cyan;
                }
                blocks[i * 15 + j].setColor(currentColor);
                xIndex -= widthAddition;
            }
            xIndex = 725;
            yIndex += heightAddition;
        }
        List<Block> blockList = new ArrayList<>();
        Collections.addAll(blockList, blocks);
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
