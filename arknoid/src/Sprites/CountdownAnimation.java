// Artiom Berengard

package Sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 *  The CountdownAnimation will display the given gameScreen,
 *  for numOfSeconds seconds, and on top of them it will show
 *  a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 *  it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private ScoreIndicator scoreIndicator;
    private LevelIndicator levelIndicator;
    private LevelInformation levelInformation;
    /**
     *  This is a constractor method, in order to show the screen
     *  itself during count down - all the relevant parameters are included.
     * @param gameScreen is the given sprites to draw.
     * @param keyboard is the given keyboard sensor to use.
     * @param runner is the given animation runner to use.
     * @param scoreIndicator is the given score indicator to use.
     * @param levelIndicator is the given level indicator to use.
     * @param levelInformation is the given level information to use.
     */
    public CountdownAnimation(SpriteCollection gameScreen,
                              KeyboardSensor keyboard,
                              AnimationRunner runner,
                              ScoreIndicator scoreIndicator,
                              LevelInformation levelInformation,
                              LevelIndicator levelIndicator) {
        this.countFrom = 3;
        this.gameScreen = gameScreen;
        this.numOfSeconds = 3;
        this.stop = false;
        this.keyboard = keyboard;
        this.runner = runner;
        this.scoreIndicator = scoreIndicator;
        this.levelInformation = levelInformation;
        this.levelIndicator = levelIndicator;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Sprite backGround = this.levelInformation.getBackground();
        backGround.drawOn(d);
        this.gameScreen.drawAllOn(d);
        if (levelInformation.getColorBackGround() == Color.black
                || levelInformation.getColorBackGround() == Color.darkGray) {
            d.setColor(Color.white);
        } else {
            d.setColor(Color.black);
        }
        d.drawText(250, d.getHeight() / 2, "Game starts in", 50);
        if ((this.countFrom - 0.9) <= this.numOfSeconds && this.numOfSeconds <= this.countFrom) {
            d.drawText(375, d.getHeight() / 2 + 50, "3...", 50);
        } else if ((this.countFrom - 1.9) <= this.numOfSeconds
                 && this.numOfSeconds < this.countFrom - 0.9) {
            d.drawText(375, d.getHeight() / 2 + 50, "2...", 50);
        } else if (0 <= this.numOfSeconds
                && this.numOfSeconds < this.countFrom - 1.9) {
            d.drawText(375, d.getHeight() / 2 + 50, "1...", 50);
        }
        this.numOfSeconds = numOfSeconds - 0.02;
        if (this.numOfSeconds <= 0) {
            this.stop = true;
        }
        this.scoreIndicator.drawOn(d);
        this.levelIndicator.drawOn(d);
        if ((this.keyboard.isPressed("p")) || (this.keyboard.isPressed("P"))) {
        this.runner.run(new PauseScreen(this.keyboard, gameScreen, scoreIndicator,
                levelInformation, levelIndicator));
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
