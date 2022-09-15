// Artiom Berengard

package Sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.List;

/**
 * This class is in charge of running the game itself, combining the
 * different levels, opening the relevant screens and timers, etc.
 */
public class GameFlow {
    private int totalScore;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;

    /**
     * This is a constractor method.
     * @param animationRunner is the given animation runner to use.
     * @param keyboardSensor is the given keyboard sensor to use.
     * @param gui is the given gui to draw onto.
     */
    public GameFlow(AnimationRunner animationRunner,
                    KeyboardSensor keyboardSensor,
                    GUI gui) {
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.gui = gui;
        this.totalScore = 0;
    }

    /**
     * This method is in charge of running all the levels.
     * @param levels is a given list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int numOfLevels = levels.size();
        int counter = 0;
        for (LevelInformation levelInfo : levels) {
            counter++;
            GameLevel level = new GameLevel(levelInfo,
                    this.gui,
                    this.animationRunner,
                    this.keyboardSensor);
            level.initialize();
            while (level.getNumOfBlocks().getValue() > 0 && level.getNumOfBalls().getValue() > 0) {
                level.addScore(totalScore);
                level.run();
            }
            if (level.getNumOfBlocks().getValue() == 0) {
                level.addScore(100);
                if (counter == numOfLevels) {
                    if (!(level.getKeyboard().isPressed(KeyboardSensor.SPACE_KEY))) {
                        level.getRunner().run(new KeyPressStoppableAnimation(level.getKeyboard(),
                                "space", new EndScreen(level.getKeyboard(), level.getSprites(),
                                level.getScore(), level.getLevelInformation(), level.getLevelIndicator(),
                                true, level.getScoreIndicator())));
                        level.getGui().close();
                        break;
                    }
                }
                this.totalScore = level.getScore();
                GUI currGui = level.getGui();
                DrawSurface d = level.getGui().getDrawSurface();
                level.doOneFrame(d);
                d.setColor(Color.red);
                d.drawText(285, 400, "Level Won!", 50);
                currGui.show(d);
                new Sleeper().sleepFor(2000);
            }
            if (level.getNumOfBalls().getValue() == 0) {
                if (!(level.getKeyboard().isPressed(KeyboardSensor.SPACE_KEY))) {
                    level.getRunner().run(new KeyPressStoppableAnimation(level.getKeyboard(), "space",
                            new EndScreen(level.getKeyboard(), level.getSprites(),
                            level.getScore(), level.getLevelInformation(), level.getLevelIndicator(),
                            false, level.getScoreIndicator())));
                    level.getGui().close();
                    break;
                }
            }
        }
    }
}

