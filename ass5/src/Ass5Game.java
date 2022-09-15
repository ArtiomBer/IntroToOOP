// Artiom Berengard
import Sprites.Game;

/**
 * This class has the main method, it creates, initializes and runs
 * the game.
 */
public class Ass5Game {
    /**
     * The method will start a new game, initialize and run it.
     * @param args are the default arguments, currently they are
     *             not used.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
