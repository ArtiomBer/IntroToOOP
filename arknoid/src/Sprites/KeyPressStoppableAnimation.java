// Artiom Berengard
package Sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is in charge of animations that using keys.
 * Such as the end screen or the pause screen.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isRunning;
    private boolean isAlreadyPressed;

    /**
     * The constractor method.
     * @param keyboardSensor is the given keyboard sensor.
     * @param key is the given key to listen to.
     * @param animation is the given animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = keyboardSensor;
        this.isRunning = true;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.isRunning = false;
            }
        } else {
            isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return !this.isRunning;
    }
}
