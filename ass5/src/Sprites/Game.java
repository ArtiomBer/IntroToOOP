// Artiom Berengard

package Sprites;
import Collision_Detection.Collidable;
import Geometry.Rectangle;
import Geometry.Point;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of setting a new game, initialize it
 * and after all run it.
 */
public class Game {
    // Introduction of the class variables.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter numOfBlocks;
    private BlockRemover blockRemover;
    private Counter numOfBalls;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    // Default gui sizes.
    private int boardWidth = 800;
    private int boardHeight = 600;
    /**
     * The method is the constructor method.
     * We create a new sprite collection, game environment and set
     * a new gui with the default sizes.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        GUI gui = new GUI("Arkanoid", boardWidth, boardHeight);
        this.gui = gui;
        this.numOfBlocks = new Counter(0);
        this.blockRemover = new BlockRemover(this, new Counter(0));
        this.numOfBalls = new Counter(0);
        this.ballRemover = new BallRemover(this, new Counter(0));
        this.score = new Counter(0);
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(this, this.score);
    }
    /**
     * This a setter method for the number of blocks in the game.
     * @param numOfBlocks is the given number to be set.
     */
    public void setNumOfBlocks(int numOfBlocks) {
        this.numOfBlocks = new Counter(numOfBlocks);
    }
    /**
     * This a getter method for the number of blocks in the game.
     * @return value is the number of blocks in the game.
     */
    public Counter getNumOfBlocks() {
        return numOfBlocks;
    }
    /**
     * This a setter method for the number of balls in the game.
     * @param numOfBalls is the number of balls to be set.
     */
    public void setNumOfBalls(int numOfBalls) {
        this.numOfBalls = new Counter(numOfBalls);
    }
    /**
     * This a getter method for the number of balls in the game.
     * @return value is the number of balls in the game.
     */
    public Counter getNumOfBalls() {
        return numOfBalls;
    }
    /**
     * This a setter method for the gui.
     * We can set the gui through this method.
     * @param gui is the given gui to be set.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }
    /**
     * This method is in charge of adding a collidable to
     * the collidables list.
     * @param c is the given collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * This method is in charge of adding a sprite to
     * the sprites list.
     * @param s is the given sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * This method is in charge of initializing the game.
     * We create a ball, the blocks, the paddle and add them all to the
     * game environment, each with its own addToGame method.
     */
    public void initialize() {
        // Adding a ball.
        Ball ball = new Ball(50, 550, 5, Color.black);
        ballConfiguration(ball, 2, -2);
        // Adding a second ball.
        Ball ball2 = new Ball(150, 400, 5, Color.black);
        ballConfiguration(ball2, -2, -2);
        // Adding a third ball.
        Ball ball3 = new Ball(300, 250, 5, Color.black);
        ballConfiguration(ball3, -2, -2);
        this.ballRemover = new BallRemover(this, new Counter(this.numOfBalls.getValue()));
        // Adding the paddle.
        Paddle paddle = new Paddle(gui);
        paddle.addToGame(this);
        // Adding the margin blocks.
        Rectangle upperBoundRect = new Rectangle(new Point(0, 0), boardWidth, 25);
        Block upperBound = new Block(upperBoundRect);
        upperBound.setColor(Color.darkGray);
        upperBound.addToGame(this);
        Rectangle bottomBoundRect = new Rectangle(new Point(25, boardHeight + 20),
        boardWidth - 25, 25);
        Block bottomBound = new Block(bottomBoundRect);
        bottomBound.setColor(Color.red);
        bottomBound.addToGame(this);
        bottomBound.addHitListener(ballRemover);
        Rectangle leftBoundRect = new Rectangle(new Point(0, 25), 25, boardHeight - 25);
        Block leftBound = new Block(leftBoundRect);
        leftBound.setColor(Color.darkGray);
        leftBound.addToGame(this);
        Rectangle rightBoundRect = new Rectangle(new Point(boardWidth - 25, 25),
                25, boardHeight - 25);
        Block rightBound = new Block(rightBoundRect);
        rightBound.setColor(Color.darkGray);
        rightBound.addToGame(this);
        // Adding the regular blocks.
        Block[] blocks = new Block[59];
        // Creating the first row.
        double yIndex = 125;
        double xIndex = 175;
        double widthAddition = 50;
        double heightAddition = 20;
        for (int i = 0; i < 12; i++) {
            blocks[i] = this.blockGenerator(xIndex, yIndex, widthAddition, heightAddition, Color.gray);
            xIndex = xIndex + widthAddition;
            blocks[i].addToGame(this);
        }
        // Creating the second row.
        yIndex = 145;
        xIndex = 225;
        for (int i = 13; i <= 23; i++) {
            blocks[i] = this.blockGenerator(xIndex, yIndex, widthAddition, heightAddition, Color.red);
            xIndex = xIndex + widthAddition;
            blocks[i].addToGame(this);
        }
        // Creating the third row.
        yIndex = 165;
        xIndex = 275;
        for (int i = 24; i <= 33; i++) {
            blocks[i] = this.blockGenerator(xIndex, yIndex, widthAddition, heightAddition, Color.yellow);
            xIndex = xIndex + widthAddition;
            blocks[i].addToGame(this);
        }
        // Creating the forth row.
        yIndex = 185;
        xIndex = 325;
        for (int i = 34; i <= 42; i++) {
            blocks[i] = this.blockGenerator(xIndex, yIndex, widthAddition, heightAddition, Color.blue);
            xIndex = xIndex + widthAddition;
            blocks[i].addToGame(this);
        }
        // Creating the fifth row.
        yIndex = 205;
        xIndex = 375;
        for (int i = 43; i < 51; i++) {
            blocks[i] = this.blockGenerator(xIndex, yIndex, widthAddition, heightAddition, Color.pink);
            xIndex = xIndex + widthAddition;
            blocks[i].addToGame(this);
        }
        // Creating the sixth row.
        yIndex = 225;
        xIndex = 425;
        for (int i = 52; i <= 58; i++) {
            blocks[i] = this.blockGenerator(xIndex, yIndex, widthAddition, heightAddition, Color.green);
            xIndex = xIndex + widthAddition;
            blocks[i].addToGame(this);
        }
    }
    /**
     * This is a helper method that is in charge of adding a ball to the game
     * and set the balls velocity.
     * @param ball is the given ball to be added to the game.
     * @param dx is the x value of the velocity.
     * @param dy is the y value of the velocity.
     */
    private void ballConfiguration(Ball ball, double dx, double dy) {
        ball.setVelocity(dx, dy);
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);
        ball.addHitListener(ballRemover);
        this.numOfBalls.increase(1);
    }
    /**
     * This method is in charge of running the game.
     * We start the animation loop and run it as long as the game runs.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            Rectangle backGround = new Rectangle(new Point(25, 25),
                    800 - 25, 600 - 25);
            Block backGroundBlock = new Block(backGround);
            backGroundBlock.setColor(Color.cyan);
            backGroundBlock.drawOn(d);
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            // timing
            biuoop.Sleeper sleeper = new biuoop.Sleeper();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (numOfBlocks.getValue() == 0) {
                addScore(100);
                this.scoreIndicator.drawOn(d);
                d.setColor(Color.black);
                d.drawText(285, 400, "WINNER!", 50);
                this.gui.show(d);
                sleeper.sleepFor(2000);
                gui.close();
                return;
            }
            if (numOfBalls.getValue() == 0) {
                this.scoreIndicator.drawOn(d);
                d.setColor(Color.black);
                d.drawText(250, 400, "GAME OVER!", 50);
                this.gui.show(d);
                sleeper.sleepFor(2000);
                gui.close();
                return;
            }
            this.scoreIndicator.drawOn(d);
            this.gui.show(d);
        }
    }
    /**
     * This is a helper method to set a block.
     * @param color is the color we want to set to the block.
     * @param x is the x center value we want to set to the block.
     * @param y is the y center value we want to set to the block.
     * @param height is the height value we want to set to the block.
     * @param width is the width value we want to set to the block.
     * @return the new generated block.
     */
    Block blockGenerator(double x, double y, double width, double height, Color color) {
        Block block = new Block(new Rectangle(new Point(x, y), width, height));
        block.setColor(color);
        this.numOfBlocks.increase(1);
        this.blockRemover = new BlockRemover(this, new Counter(this.numOfBlocks.getValue()));
        block.addHitListener(blockRemover);
        block.addHitListener(scoreTrackingListener);
        return block;
    }
    /**
     * This method is in charge of removing a collidable from the game.
     * @param c is the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * This method is in charge of removing a sprite from the game.
     * @param s is the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        // Make a copy of the hitListeners before iterating over them.
        List<Sprite> currentSprites = new ArrayList<>(this.sprites.getSprites());
        currentSprites.remove(s);
        this.sprites = new SpriteCollection(currentSprites);
    }
    /**
     * This method is in charge of adding score to the player.
     * @param addedScore is the given gui to be set.
     */
    public void addScore(int addedScore) {
        this.score.increase(addedScore);
    }
}