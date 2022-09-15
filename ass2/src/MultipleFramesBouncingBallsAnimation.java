// Artiom Berengard
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * This class generates an animation of multiple balls bouncing
 * in two different frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static GUI gui = new GUI("MultipleFramesBouncingBallsAnimation",
            1000, 1000);
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
            drawing.setColor(Color.gray);
            drawing.fillRectangle(50, 50, 450, 450);
            drawing.setColor(Color.yellow);
            drawing.fillRectangle(450, 450, 150, 150);
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
        int half;
        // If the users enters uneven number of balls.
        if (ballSizes.length % 2 == 1) {
            half = (ballSizes.length / 2) + 1;
        } else {
            half = ballSizes.length / 2;
        }
        // Generating the needed values for the first half of balls in the ballList.
        for (int i = 0; i <= half; i++) {
            double centerX = rand.nextInt(450) + 51;
            double centerY = rand.nextInt(450) + 51;
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color randomColor = new Color(r, g, b);
            int ballSize;
            int boardStartX = 51;
            int boardStartY = 51;
            int boardHeightSize = 500;
            int boardWidthSize = 500;
            // In case the user enters an invalid size, we prevent a crush.
            if (ballSizes[i] <= 0) {
                System.out.println("Please advise, the inserted size of the ball ("
                        + (int) ballSizes[i] + ") needs to be bigger than zero. "
                        + "the size has been increased.");
                ballSize = (int) ((0.15 * (boardWidthSize - boardStartX
                        + boardHeightSize - boardStartY) / 2));
            } else if ((ballSizes[i] > 0.5 * (boardHeightSize - boardStartY))
                    || (ballSizes[i] > 0.5 * (boardWidthSize - boardStartX))) {
                System.out.println("Please advise, the height of the drawing board is "
                        + (boardHeightSize - boardStartY) + " and the width is "
                        + (boardWidthSize - boardStartX) + '.');
                System.out.println("The size of " + (int) (2 * ballSizes[i]) + " is too big for"
                        + " the drawing board. the size has been reduced.");
                ballSize = (int) (0.25 * ((boardWidthSize - boardStartX
                        + boardHeightSize - boardStartY) / 2));
            } else {
                ballSize = (int) ballSizes[i];
            }
            Ball ball = new Ball(centerX, centerY, ballSize, randomColor);
            ball.setFrame(500, 500);
            ball.setFrameStart(boardStartX, boardStartY);
            ballsList[i] = ball;
        }
        // Generating the needed values for the second half of balls in the ballList.
        for (int i = half; i < ballSizes.length; i++) {
            double centerX = rand.nextInt(150) + 451;
            double centerY = rand.nextInt(150) + 451;
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color randomColor = new Color(r, g, b);
            int ballSize;
            int boardStartX = 450;
            int boardStartY = 451;
            int boardHeightSize = 600;
            int boardWidthSize = 600;
            int middleSize = (boardWidthSize - boardStartX + boardHeightSize - boardStartY) / 2;
            // In case the user enters an invalid size, we prevent a crush.
            if (ballSizes[i] <= 0) {
                System.out.println("Please advise, the inserted size of the ball ("
                        + (int) ballSizes[i] + ") needs to be bigger than zero. "
                        + "the size has been increased.");
                ballSize = (int) (0.15 * middleSize);
            } else if ((ballSizes[i] > 0.5 * (boardHeightSize - boardStartY))
                    || (ballSizes[i] > 0.5 * (boardWidthSize - boardStartX))) {
                System.out.println("Please advise, the height of the drawing board is "
                        + (boardHeightSize - boardStartY) + " and the width is "
                        + (boardWidthSize - boardStartX) + '.');
                System.out.println("The size of " + (int) (2 * ballSizes[i]) + " is too big for"
                        + " the drawing board. the size has been reduced.");
                ballSize = (int) (0.25 * middleSize);
            } else {
                ballSize = (int) ballSizes[i];
            }
            Ball ball = new Ball(centerX, centerY, ballSize, randomColor);
            ball.setFrame(600, 600);
            ball.setFrameStart(boardStartX, boardStartY);
            ballsList[i] = ball;
        }
        // Generating random starting velocity.
        double randomDx = 15;
        double randomDy = 15;
        for (Ball ball : ballsList) {
            double ballSize = ball.getSize();
            ball.setVelocity(randomDx / ballSize, randomDy / ballSize);
            if (ballSize > 50) {
                ball.setVelocity(randomDx / 50, randomDy / 50);
            }
        }
        return ballsList;
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
     * sort them to two frames, set the balls' values and
     * start the animation using the described methods above.
     * @param args are the arguments received in the main function.
     */
    public static void main(String[] args) {
        double[] ballSizes;
        Ball[] ballList;
        ballSizes =  stringsToDoubles(args);
        ballList = generateBallList(ballSizes);
        drawAnimation(gui, ballList);
    }
}
