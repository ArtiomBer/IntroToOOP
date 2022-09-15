// Artiom Berengard

package Sprites;
import Collision_Detection.Collidable;
import Collision_Detection.Velocity;
import Geometry.Rectangle;
import Geometry.Point;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of setting a new game, initialize it
 * and after all run it.
 */
public class GameLevel implements Animation {
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
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private LevelIndicator levelIndicator;
    // Default gui sizes.
    private int boardWidth = 800;
    private int boardHeight = 600;

    /**
     * The method is the constructor method.
     * We create a new sprite collection, game environment and set
     * a new gui with the default sizes.
     * @param levelInformation is the given information of the level.
     * @param gui is the given gui to draw onto.
     * @param runner is the given animation runner to use.
     * @param keyboard is the given keyboard sensor that is already running.
     */
    public GameLevel(LevelInformation levelInformation,
                     GUI gui,
                     AnimationRunner runner,
                     KeyboardSensor keyboard) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.numOfBlocks = new Counter(0);
        this.blockRemover = new BlockRemover(this, new Counter(0));
        this.numOfBalls = new Counter(0);
        this.ballRemover = new BallRemover(this, new Counter(0));
        this.score = new Counter(0);
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(this, this.score);
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.levelIndicator = new LevelIndicator(this, levelInformation,
                levelInformation.levelName());
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
     * A getter method.
     * @return the gui.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * A getter method.
     * @return the keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * A getter method.
     * @return the level indicator.
     */
    public LevelIndicator getLevelIndicator() {
        return this.levelIndicator;
    }

    /**
     * A getter method.
     * @return the score indicator.
     */
    public ScoreIndicator getScoreIndicator() {
        return this.scoreIndicator;
    }

    /**
     * A getter method.
     * @return the runner.
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }

    /**
     * A getter method.
     * @return the sprites.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * A getter method.
     * @return the level information.
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
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
        this.createBallsOnTopOfPaddle();
        Paddle paddle = new Paddle(gui,
                this.levelInformation.paddleSpeed(),
                this.levelInformation.paddleWidth());
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
        //Block[] blocks = new Block[this.levelInformation.initialBallVelocities().size()];
        for (Block block : this.levelInformation.blocks()) {
            blockGenerator(block);
        }
    }
    /**
     * This method is in charge of running the game.
     * We start the animation loop and run it as long as the game runs.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(sprites, keyboard, runner, scoreIndicator,
                levelInformation, levelIndicator));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * A method that creates and initialises the balls.
     */
    public void createBallsOnTopOfPaddle() {
        Ball[] balls = new Ball[this.levelInformation.numberOfBalls()];
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        if (this.levelInformation.numberOfBalls() == 1) {
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                Ball ball = new Ball(((int) ((boardWidth - this.levelInformation.paddleWidth()) / 2)
                        + ((int) (levelInformation.paddleWidth() / 2))),
                        550, 5, Color.white);
                ball.setVelocity(velocities.get(i));
                ball.setGameEnvironment(this.environment);
                ball.addToGame(this);
                ball.addHitListener(ballRemover);
                this.numOfBalls.increase(1);
                this.ballRemover = new BallRemover(this, new Counter(this.numOfBalls.getValue()));
            }
        } else if (this.levelInformation.numberOfBalls() == 10) {
            int xVar = 115;
            int yVar = 0;
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                balls[i] = new Ball(50 + xVar, 550 - yVar, 5, Color.white);
                balls[i].setVelocity(velocities.get(i));
                balls[i].setGameEnvironment(this.environment);
                balls[i].addToGame(this);
                balls[i].addHitListener(ballRemover);
                this.numOfBalls.increase(1);
                this.ballRemover = new BallRemover(this, new Counter(this.numOfBalls.getValue()));
                if (i == 0 || i == 1) {
                    xVar += 50;
                    yVar += 40;
                } else if (i == 2 || i == 3) {
                    xVar += 50;
                    yVar += 20;
                } else if (i == 4) {
                    xVar += 70;
                } else if (i == 5) {
                    xVar += 50;
                    yVar -= 20;
                } else if (i == 6 || i == 7) {
                    xVar += 50;
                    yVar -= 30;
                } else if (i == 8 || i == 9) {
                    xVar += 50;
                    yVar -= 40;
                }
            }
        } else if (this.levelInformation.numberOfBalls() == 2) {
            int xVar = 0;
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                balls[i] = new Ball(325 + xVar, 500, 5, Color.white);
                balls[i].setVelocity(velocities.get(i));
                balls[i].setGameEnvironment(this.environment);
                balls[i].addToGame(this);
                balls[i].addHitListener(ballRemover);
                this.numOfBalls.increase(1);
                this.ballRemover = new BallRemover(this, new Counter(this.numOfBalls.getValue()));
                xVar += 150;
            }
        } else if (this.levelInformation.numberOfBalls() == 3) {
            int xVar = 0;
            int yVar = 0;
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                balls[i] = new Ball(325 + xVar, 550 - yVar, 5, Color.white);
                balls[i].setVelocity(velocities.get(i));
                balls[i].setGameEnvironment(this.environment);
                balls[i].addToGame(this);
                balls[i].addHitListener(ballRemover);
                this.numOfBalls.increase(1);
                this.ballRemover = new BallRemover(this, new Counter(this.numOfBalls.getValue()));
                xVar += 75;
                if (i == 0) {
                    yVar += 25;
                } else if (i == 1) {
                    yVar -= 25;
                }
            }
        } else {
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                Ball ball = new Ball(((int) ((boardWidth - this.levelInformation.paddleWidth()) / 2)
                        + ((int) (levelInformation.paddleWidth() / 2))),
                        550, 5, Color.white);
                ball.setVelocity(velocities.get(i));
                ball.setGameEnvironment(this.environment);
                ball.addToGame(this);
                ball.addHitListener(ballRemover);
                this.numOfBalls.increase(1);
                this.ballRemover = new BallRemover(this, new Counter(this.numOfBalls.getValue()));
            }
        }


    }
    /**
     * This is a helper method to set a block.
     * @param block is the given block.
     * @return the new generated block.
     */
    Block blockGenerator(Block block) {
        this.numOfBlocks.increase(1);
        this.blockRemover = new BlockRemover(this, new Counter(this.numOfBlocks.getValue()));
        block.addHitListener(blockRemover);
        block.addHitListener(scoreTrackingListener);
        block.addToGame(this);
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

    /**
     * A getter method.
     * @return the score value.
     */
    public int getScore() {
        return this.score.getValue();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Sprite backGround = this.levelInformation.getBackground();
        backGround.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (numOfBlocks.getValue() == 0) {
            this.running = false;
        }
        if (numOfBalls.getValue() == 0) {
              this.running = false;
        }
        this.scoreIndicator.drawOn(d);
        this.levelIndicator.drawOn(d);

        if ((this.keyboard.isPressed("p")) || (this.keyboard.isPressed("P"))) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen(this.keyboard, sprites,
                            scoreIndicator, levelInformation, levelIndicator)));
        }

//        if ((this.keyboard.isPressed("p")) || (this.keyboard.isPressed("P"))) {
//            this.runner.run(new PauseScreen(this.keyboard, sprites,
//                    scoreIndicator, levelInformation, levelIndicator));
//        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}