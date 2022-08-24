// Artiom Berengard 321114704
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * This class generates an animation of multiple balls bouncing
 * in a single frame.
 */
public class MultipleBouncingBallsAnimation {
    private static GUI gui = new GUI("MultipleBouncingBallsAnimation", 500, 500);
    /**
     * This is the drawing animation method. it is in charge of
     * showing the balls' movement.
     * @param gui is user's animation screen.
     * @param ballList is the list of ball sizes we want to show.
     */
    static void drawAnimation(GUI gui, Ball[] ballList) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();
        // This loop is the animation loop.
        while (true) {
            DrawSurface drawing = gui.getDrawSurface();
            for (Ball ball : ballList) {
                ball.moveOneStep();
                ball.drawOn(drawing);
            }
            gui.show(drawing);
            sleeper.sleepFor(50);
        }
    }
    /**
     * This is the method that generates the ball list. it is in
     * charge of creating a list of balls and set their values.
     * @param ballSizes is the list of ball sizes we want to show.
     * @return the generated ball list.
     */
    public static Ball[] generateBallList(double[] ballSizes) {
        Ball[] ballsList;
        ballsList = new Ball[ballSizes.length];
        Random rand = new Random();
        // Generating the needed values for each ball in the ballList.
        for (int i = 0; i < ballSizes.length; i++) {
            double centerX = rand.nextInt(gui.getDrawSurface().getWidth() - 1) + 1;
            double centerY = rand.nextInt(gui.getDrawSurface().getHeight() - 1) + 1;
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color randomColor = new Color(r, g, b);
            int ballSize;
            int boardHeightSize = gui.getDrawSurface().getHeight();
            int boardWidthSize = gui.getDrawSurface().getWidth();
            // In case the user enters an invalid size, we prevent a crush.
            if (ballSizes[i] <= 0) {
                System.out.println("Please advise, the inserted size of the ball ("
                        + (int) ballSizes[i] + ") needs to be bigger than zero. "
                        + "the size has been increased.");
                ballSize = (int) (0.15 * ((boardWidthSize + boardHeightSize) / 2));
            } else if ((ballSizes[i] > 0.5 * boardHeightSize)
                     || (ballSizes[i] > 0.5 * boardWidthSize)) {
                System.out.println("Please advise, the height of the drawing board is "
                + boardHeightSize + " and the width is " + boardWidthSize + '.');
                System.out.println("The size of " + (int) ballSizes[i] + " is too big for"
                         + " the drawing board. the size has been reduced.");
                ballSize = (int) (0.25 * ((boardWidthSize + boardHeightSize) / 2));
            } else {
                ballSize = (int) ballSizes[i];
            }
            Ball ball = new Ball(centerX, centerY, ballSize, randomColor);
            ball.setFrame(500, 500);
            ballsList[i] = ball;
        }
        // Generating random starting velocity.
        double dx = rand.nextInt(5) + 5;
        double dy = rand.nextInt(5) + 5;
        ballsList[0].setVelocity(dx, dy);
        for (int i = 1; i < ballsList.length; i++) {
            if (ballsList[i].getSize() > ballsList[i - 1].getSize()) {
                dx = 0.85 * dx;
                dy = 0.85 * dy;
                ballsList[i].setVelocity(dx, dy);
            } else {
                ballsList[i].setVelocity(dx, dy);
            }
        }
        return ballsList;
    }


    /**
     * The sortSizes method will sort a list of integers in ascending order.
     * @param ballSizes The method will receive a list of sizes.
     * @return The method will return the sizes list sorted in ascending order.
     */
    public static double[] sortSizes(double[] ballSizes) {
        for (int i = 0; i < ballSizes.length - 1; i++) {
            for (int j = 0; j < ballSizes.length - 1 - i; j++) {
                if (ballSizes[j] > ballSizes[j + 1]) {
                    swap(ballSizes, j, j + 1);
                }
            }
        }
        return ballSizes;
    }
    /**
     * The swap method will swap integers in the given list.
     * @param ballSizes The method will receive a list of numbers.
     * @param i The method will receive the first index to swap.
     * @param j The method will receive the second index to swap.
     */
    public static void swap(double[] ballSizes, int i, int j) {
        double temp = ballSizes[i];
        ballSizes[i] = ballSizes[j];
        ballSizes[j] = temp;
    }
    /**
     * This method is in charge of conversing strings to doubles.
     * @param arguments  are the arguments received in the main function.
     * @return value is the numbers list.
     */
    public static double[] stringsToDoubles(String[] arguments) {
        double[] sizeList;
        sizeList = new double[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            sizeList[i] = Double.parseDouble(arguments[i]);
        }
        return sizeList;
    }
    /**
     * This is the main function. We receive the sizes of the balls,
     * sort them in order to make the velocity setting easier and
     * start the animation using the described methods above.
     * @param args  are the arguments received in the main function.
     */
    public static void main(String[] args) {
        double[] ballSizes;
        Ball[] ballList;
        ballSizes =  stringsToDoubles(args);
        sortSizes(ballSizes);
        ballList = generateBallList(ballSizes);
        drawAnimation(gui, ballList);
    }
}
