// Artiom Berengard

package Sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * This class is in charge of the game ending screen.
 * With this class, a winning/losing message will be shown.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection gameScreen;
    private int totalScore;
    private ScoreIndicator scoreIndicator;
    private LevelInformation levelInformation;
    private LevelIndicator levelIndicator;
    private boolean won;

    /**
     * This is a constractor method, all the parameters are being
     * used in order to continue streaming the gaming screen.
     * @param k is the given keyboard sensor.
     * @param sprites is the given sprite collection.
     * @param totalScore is the given score.
     * @param levelInformation is the needed level information.
     * @param levelIndicator is the given level indicator.
     * @param won is the boolean value for the game status.
     * @param scoreIndicator is the given score indicator.
     */
    public EndScreen(KeyboardSensor k,
                       SpriteCollection sprites,
                       int totalScore,
                       LevelInformation levelInformation,
                       LevelIndicator levelIndicator,
                       boolean won,
                       ScoreIndicator scoreIndicator) {
        this.keyboard = k;
        this.stop = false;
        this.gameScreen = sprites;
        this.totalScore = totalScore;
        this.levelInformation = levelInformation;
        this.levelIndicator = levelIndicator;
        this.won = won;
        this.scoreIndicator = scoreIndicator;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Sprite backGround = this.levelInformation.getBackground();
        backGround.drawOn(d);
        this.gameScreen.drawAllOn(d);
        this.scoreIndicator.drawOn(d);
        this.levelIndicator.drawOn(d);
        if (levelInformation.getColorBackGround() == Color.black
                || levelInformation.getColorBackGround() == Color.darkGray) {
            d.setColor(Color.white);
        } else {
            d.setColor(Color.black);
        }
        if (won) {
            d.drawText(330, d.getHeight() / 2 + 50, "You Win!", 35);
            d.drawText(280, d.getHeight() / 2 + 100,
                    "Your score is " + this.totalScore, 35);
        } else {
            d.drawText(315, d.getHeight() / 2 + 50, "Game Over.", 30);
            d.drawText(280, d.getHeight() / 2 + 100,
                    "Your score is " + this.totalScore, 35);
        }
        d.drawText(275, d.getHeight() / 2 + 225, "Press space to exit", 30);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
