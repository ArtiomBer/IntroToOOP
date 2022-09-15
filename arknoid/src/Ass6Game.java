// Artiom Berengard

import Sprites.AnimationRunner;
import Sprites.DirectHitLevel;
import Sprites.FinalFourLevel;
import Sprites.GameFlow;
import Sprites.Green3Level;
import Sprites.LevelInformation;
import Sprites.WideEasyLevel;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has the main method, it creates, initializes and runs
 * the game.
 */
public class Ass6Game {
    /**
     * The method will start a new game, initialize and run it.
     * @param args are the default arguments, currently they are
     *             not used.
     */
    public static void main(String[] args) {
        DirectHitLevel directHitLevel = new DirectHitLevel();
        WideEasyLevel wideEasyLevel = new WideEasyLevel();
        Green3Level green3Level = new Green3Level();
        FinalFourLevel finalFourLevel = new FinalFourLevel();
        List<LevelInformation> levels = new ArrayList<>();
        for (String str : args) {
            int strToInt = 0;
            try {
                strToInt = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                continue;
            }
            if (strToInt >= 1 && strToInt <= 4) {
                switch (strToInt) {
                    case 1:
                        levels.add(new DirectHitLevel());
                        break;
                    case 2:
                        levels.add(new WideEasyLevel());
                        break;
                    case 3:
                        levels.add(new Green3Level());
                        break;
                    case 4:
                        levels.add(new FinalFourLevel());
                        break;
                    default:
                        break;
                    }
                }
            }
        if (levels.size() == 0) {
            levels.add(directHitLevel);
            levels.add(wideEasyLevel);
            levels.add(green3Level);
            levels.add(finalFourLevel);
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(runner, keyboardSensor, gui);
        gameFlow.runLevels(levels);
    }
}
