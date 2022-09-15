// Artiom Berengard
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * This class is in charge of setting a new game, initialize it
 * and after all run it.
 */
public class Game {
    // Introduction of the class variables.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    /**
     * The method is the constructor method.
     * We create a new sprite collection, game environment and set
     * a new gui with the default sizes.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        // The defined sizes of the gui size.
        int boardWidth = 800;
        int boardHeight = 600;
        GUI gui = new GUI("Arkanoid", boardWidth, boardHeight);
        this.gui = gui;
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
        // Default gui sizes.
        int boardWidth = 800;
        int boardHeight = 600;
        // Adding a ball.
        Ball ball = new Ball(50, 550, 5, Color.black);
        ball.setVelocity(4, 2);
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);
        // Adding a second ball.
        Ball ball2 = new Ball(150, 400, 5, Color.black);
        ball2.setVelocity(-2, 4);
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);
        // Adding the paddle.
        Paddle paddle = new Paddle(gui);
        paddle.addToGame(this);
        // Adding the margin blocks.
        Rectangle upperBoundRect = new Rectangle(new Point(0, 0), boardWidth, 25);
        Block upperBound = new Block(upperBoundRect);
        upperBound.setColor(Color.darkGray);
        upperBound.addToGame(this);
        Rectangle bottomBoundRect = new Rectangle(new Point(25, boardHeight - 25),
                boardWidth - 25, 25);
        Block bottomBound = new Block(bottomBoundRect);
        bottomBound.setColor(Color.darkGray);
        bottomBound.addToGame(this);
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
     * This method is in charge of running the game.
     * We start the animation loop and run it as long as the game runs.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            biuoop.Sleeper sleeper = new biuoop.Sleeper();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
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
        return block;
    }
}