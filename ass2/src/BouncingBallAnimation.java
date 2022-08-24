// Artiom Berengard
import biuoop.GUI;
import biuoop.DrawSurface;
/**
 * This class is capable of making a bouncing ball animation.
 * This class takes input to the main function. The input is the
 * center of the ball and its velocity.
 */
public class BouncingBallAnimation {
    /**
     * This method is in charge of drawing the animation itself.
     * @param pointX  is the x value of the center point.
     * @param pointY is the y value of the center point.
     * @param dx  is the x value of the ball's velocity.
     * @param dy is the y value of the ball's velocity.
     */
    static void drawAnimation(double pointX, double pointY,
                                      double dx, double dy) {
        // Announcing the variables.
        GUI gui = new GUI("BouncingBallAnimation", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        int boardHeightSize = gui.getDrawSurface().getHeight();
        int boardWidthSize = gui.getDrawSurface().getWidth();
        int radius = 30;
        // Checking if the center point is valid.
        if (((pointX - radius) < 1) || ((pointX + radius) >= boardWidthSize)) {
            System.out.println("Please advise, the width of the drawing board is "
                    + boardWidthSize + ".\n" + "The X coordinate (" + (int) pointX
                     + ") needs to bigger than the radius (" + radius + ") and smaller"
                    + " than the board width. The coordinate has been changed.");
            pointX = rand.nextInt(boardWidthSize - 2 * radius) + radius;
        }
        if (((pointY - radius) < 1) || ((pointY + radius) >= boardHeightSize)) {
            System.out.println("Please advise, the height of the drawing board is "
                    + boardHeightSize + ".\n" + "The Y coordinate (" + (int) pointY
                    + ") needs to bigger than the radius (" + radius + ") and smaller"
                    + " than the board height. The coordinate has been changed.");
            pointY = rand.nextInt(boardHeightSize - 2 * radius) + radius;
        }
        Ball ball = new Ball(pointX, pointY, radius, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        ball.setFrame(200, 200);
        // The animation loop.
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
    /**
     * This method is in charge of conversing strings to doubles.
     * @param arguments  are the arguments received in the main function.
     * @return value is the numbers list.
     */
    public static double[] stringsToDoubles(String[] arguments) {
        double[] numberList;
        numberList = new double[arguments.length ];
        for (int i = 0; i < arguments.length; i++) {
            numberList[i] = Double.parseDouble(arguments[i]);
        }
        return numberList;
    }
    /**
     * This is the main function.
     * @param args  are the arguments received in the main function.
     */
    public static void main(String[] args) {
        double[] numbers;
        numbers =  stringsToDoubles(args);
        double pointX = numbers[0];
        double pointY = numbers[1];
        double dx = numbers[2];
        double dy = numbers[3];
        drawAnimation(pointX, pointY, dx, dy);
    }
}
