// Artiom Berengard

package Sprites;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is in charge of the level display to the gui.
 */
public class LevelIndicator {
    private LevelInformation levelInformation;
    private GameLevel game;
    private String levelName;
    /**
     * This is a constractor method.
     * @param game is the given game.
     * @param levelInformation is the given level information.
     * @param levelName is the given level name.
     */
    LevelIndicator(GameLevel game, LevelInformation levelInformation, String levelName) {
        this.levelInformation = levelInformation;
        this.game = game;
        this.levelName = levelName;
    }
    /**
     * This method is displaying the given level name of a given game to
     * the given draw surface.
     * @param drawSurface is the given draw surface.
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.white);
        drawSurface.drawText(500, 20, "Level Name: " + this.levelName, 15);
    }
}
