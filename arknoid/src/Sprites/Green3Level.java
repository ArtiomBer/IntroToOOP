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
 * This class is in charge of the third level.
 */
public class Green3Level implements LevelInformation {
    private int numOfBalls = 2;
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity ball1Velocity = new Velocity(-2, -2);
        velocities.add(ball1Velocity);
        Velocity ball2Velocity = new Velocity(2, -2);
        velocities.add(ball2Velocity);
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 10;
    }
    @Override
    public int paddleWidth() {
        return 100;
    }
    @Override
    public String levelName() {
        return "Green 3";
    }
    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                Color darkGreen = new Color(12, 128, 8);
                Block backGround = new Block(new Rectangle(new Point(25, 25),
                        800 - 25, 600 - 25));
                backGround.setColor(darkGreen);
                d.setColor(darkGreen);
                backGround.drawOn(d);
                Color darkerGray = new Color(43, 43, 43);
                Color lessDarkerGray = new Color(53, 53, 53);
                Color extraLightGray = new Color(75, 75, 75);
                Color lightYellow = new Color(200, 180, 90);
                Color lightRed = new Color(250, 60, 90);
                d.setColor(darkerGray);
                d.fillRectangle(65, 400, 125, 200);
                d.setColor(Color.white);
                int xVal = 82;
                int yVal = 590;
                int xAdd = 20;
                int yAdd = 44;
                for (int i = 1; i <= 25; i++) {
                    d.fillRectangle(xVal, yVal, 12, 30);
                    xVal += xAdd;
                    if (i % 5 == 0) {
                        xVal = 82;
                        yVal -= yAdd;
                    }
                }
                d.setColor(lessDarkerGray);
                d.fillRectangle(108, 320, 40, 80);
                d.setColor(extraLightGray);
                d.fillRectangle(123, 120, 10, 200);
                d.setColor(lightYellow);
                d.fillCircle(128, 115, 15);
                d.setColor(lightRed);
                d.fillCircle(128, 115, 10);
                d.setColor(Color.white);
                d.fillCircle(128, 115, 5);
            }
            @Override
            public void timePassed() {
            }
        };
        return sprite;
    }
    @Override
    public Color getColorBackGround() {
        Color darkGreen = new Color(12, 128, 8);
        return darkGreen;
    }
    @Override
    public List<Block> blocks() {
        Block[] blocks = new Block[40];
        // Creating the row of blocks.
        double yIndex = 250;
        double xIndex = 725;
        double widthAddition = 50;
        double heightAddition = 20;
        for (int i = 0; i < 10; i++) {
            blocks[i] = new Block(new Rectangle(new Point(xIndex, yIndex),
                    widthAddition, heightAddition));
            xIndex = xIndex - widthAddition;
            blocks[i].setColor(Color.gray);
        }
        // Creating second row of blocks.
        yIndex += heightAddition;
        xIndex = 725;
        for (int i = 10; i < 19; i++) {
            blocks[i] = new Block(new Rectangle(new Point(xIndex, yIndex),
                    widthAddition, heightAddition));
            xIndex = xIndex - widthAddition;
            blocks[i].setColor(Color.red);
        }
        // Creating the third row of blocks.
        yIndex += heightAddition;
        xIndex = 725;
        for (int i = 19; i < 27; i++) {
            blocks[i] = new Block(new Rectangle(new Point(xIndex, yIndex),
                    widthAddition, heightAddition));
            xIndex = xIndex - widthAddition;
            blocks[i].setColor(Color.orange);
        }
        // Creating the forth row of blocks.
        yIndex += heightAddition;
        xIndex = 725;
        for (int i = 27; i < 34; i++) {
            blocks[i] = new Block(new Rectangle(new Point(xIndex, yIndex),
                    widthAddition, heightAddition));
            xIndex = xIndex - widthAddition;
            blocks[i].setColor(Color.blue);
        }
        // Creating the fifth row of blocks.
        yIndex += heightAddition;
        xIndex = 725;
        for (int i = 34; i < 40; i++) {
            blocks[i] = new Block(new Rectangle(new Point(xIndex, yIndex),
                    widthAddition, heightAddition));
            xIndex = xIndex - widthAddition;
            blocks[i].setColor(Color.white);
        }
        List<Block> blockList = new ArrayList<>();
        Collections.addAll(blockList, blocks);
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
