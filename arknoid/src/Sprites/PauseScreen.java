// Artiom Berengard

package Sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * This class is in charge of the pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection gameScreen;
    private ScoreIndicator scoreIndicator;
    private LevelIndicator levelIndicator;
    private LevelInformation levelInformation;

    /**
     * A constractor method.
     * @param k is the given keyboard.
     * @param sprites are the given sprites to draw.
     * @param scoreIndicator is the given score indicator.
     * @param levelInformation is the given level information.
     * @param levelIndicator is the given level indocator.
     */
    public PauseScreen(KeyboardSensor k,
                       SpriteCollection sprites,
                       ScoreIndicator scoreIndicator,
                       LevelInformation levelInformation,
                       LevelIndicator levelIndicator) {
        this.keyboard = k;
        this.stop = false;
        this.gameScreen = sprites;
        this.scoreIndicator = scoreIndicator;
        this.levelInformation = levelInformation;
        this.levelIndicator = levelIndicator;
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
        d.drawText(325, d.getHeight() / 2, "paused!", 50);
        d.drawText(250, d.getHeight() / 2 + 80, "Press space to continue", 30);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
