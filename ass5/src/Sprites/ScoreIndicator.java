// Artiom Berengard

package Sprites;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class is in charge of the score display to the gui.
 * The class has a counter for the score and a reference to the game.
 */
public class ScoreIndicator {
    private Counter score;
    private Game game;
    /**
     * This is a constractor method.
     * @param game is the given game.
     * @param score is the given score value.
     */
    ScoreIndicator(Game game, Counter score) {
        this.score = score;
        this.game = game;
    }
    /**
     * This method is a setter method for the score of the game.
     * @param score is the given score value to be set.
     */
    public void setScore(Counter score) {
        this.score = score;
    }
    /**
     * This method is displaying the given score value of a given game to
     * the given draw surface.
     * @param drawSurface is the given draw surface.
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.white);
        drawSurface.drawText(350, 20, "Score: " + this.score.getValue(), 15);
    }
}
