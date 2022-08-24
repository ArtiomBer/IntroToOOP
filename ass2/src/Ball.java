// Artiom Berengard
import biuoop.DrawSurface;
/**
 * This class has the radius, center point, color, velocity,
 * frame and frame start values of a ball.
 * This class is capable of returning all of those variables and drawing
 * the ball to the screen.
 * This class also has option to move the ball one step to its velocity.
 */
public class Ball {
    // Announcing the ball's variables.
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity ballVelocity = new Velocity(0, 0);
    private Point frame = new Point(200, 200);
    private Point frameStart = new Point(0, 0);
    /**
     * The method will set the ball's frame of movement.
     * @param width is the width of the drawing surface.
     * @param height is the height of the drawing surface.
     */
    public void setFrame(double width, double height) {
        this.frame = new Point(width, height);
    }
    /**
     * The method will set the ball's starting point of the frame.
     * @param startX is the x value of the starting frame point.
     * @param startY is the y value of the starting frame point.
     */
    public void setFrameStart(double startX, double startY) {
        this.frameStart = new Point(startX, startY);
    }
    /**
     * The method will set the ball's velocity.
     * @param velocity is the ball's velocity to be set.
     */
    public void setVelocity(Velocity velocity) {
        ballVelocity = velocity;
    }
    /**
     * The method will set the ball's velocity.
     * @param dx is the ball's x movement.
     * @param dy is the ball's y movement.
     */
    public void setVelocity(double dx, double dy) {
        ballVelocity = new Velocity(dx, dy);
    }
    /**
     * The method will give the ball's velocity.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return ballVelocity;
    }
    /**
     * The method is capable of making the ball move one step
     * in regard to its velocity setting.
     */
    public void moveOneStep() {
        // Announcing the variables with casting.
        double maxWidth = frame.getX();
        double maxHeight = frame.getY();
        this.center = this.getVelocity().applyToPoint(this.center);
        int ballSize = this.getSize();
        double centerX = this.center.getX();
        double centerY = this.center.getY();
        double velocityX = this.getVelocity().getDx();
        double velocityY = this.getVelocity().getDy();
        /*
         * We will divide into cases depending on the values
         * of the ball's velocity.
         * in case both dx and dy values are positive:
         */

        if ((velocityX > 0) && (velocityY > 0)) {
            // If the ball hits the right edge:
            if ((centerX + ballSize) > maxWidth) {
                ballVelocity.setDx(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the bottom edge:
            if ((centerY + ballSize) > maxHeight) {
                ballVelocity.setDy(-1 * (ballVelocity.getDy()));
            }
        }
        // For positive X and negative Y.
        if ((velocityX > 0) && (velocityY < 0)) {
            // If the ball hits the right edge:
            if ((centerX + ballSize)  > maxWidth) {
                ballVelocity.setDx(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the upper edge:
            if ((centerY - ballSize) < frameStart.getY()) {
                ballVelocity.setDy(-1 * (ballVelocity.getDy()));
            }
        }

        // For negative X and positive Y.
        if ((velocityX < 0) && (velocityY > 0)) {
            // If the ball hits the left edge:
            if ((centerX - ballSize)  < frameStart.getX()) {
                ballVelocity.setDx(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the bottom edge:
            if ((centerY + ballSize) > maxHeight) {
                ballVelocity.setDy(-1 * (ballVelocity.getDy()));
            }
        }
        // For negative X and Y.
        if ((velocityX < 0) && (velocityY < 0)) {
            // If the ball hits the left edge:
            if ((centerX - ballSize) < frameStart.getX()) {
                ballVelocity.setDx(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the upper edge:
            if ((centerY - ballSize) < frameStart.getY()) {
                ballVelocity.setDy(-1 * (ballVelocity.getDy()));
            }
        }
        // For positive X and Y equals zero:
        if ((velocityX > 0) && (velocityY == 0)) {
            // If the ball hits the right edge:
            if ((centerX + ballSize) > maxWidth) {
                ballVelocity.setDx(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the upper edge:
            if ((centerY - ballSize) < frameStart.getY()) {
                ballVelocity.setDy(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the bottom edge:
            if ((centerY + ballSize) > maxHeight) {
                ballVelocity.setDy(-1 * (ballVelocity.getDx()));
            }
        }
        // For negative X and Y equals zero:
        if ((velocityX < 0) && (velocityY == 0)) {
            // If the ball hits the left edge:
            if ((centerX - ballSize) < 0) {
                ballVelocity.setDx(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the upper edge:
            if ((centerY - ballSize) < frameStart.getY()) {
                ballVelocity.setDy(-1 * (ballVelocity.getDx()));
            }
            // If the ball hits the bottom edge:
            if ((centerY + ballSize) > maxHeight) {
                ballVelocity.setDy(-1 * (ballVelocity.getDx()));
            }
        }
        // For X equals zero and positive Y.
        if ((velocityX == 0) && (velocityY > 0)) {
            // If the ball hits the bottom edge:
            if ((centerY + ballSize) > maxHeight) {
                ballVelocity.setDy(-1 * (ballVelocity.getDy()));
            }
            // If the ball hits the left edge:
            if ((centerX - ballSize) < frameStart.getX()) {
                ballVelocity.setDx(-1 * (ballVelocity.getDy()));
            }
            // If the ball hits the right edge:
            if ((centerX + ballSize) > maxWidth) {
                ballVelocity.setDx(-1 * (ballVelocity.getDy()));
            }

        }
        // For X equals zero and negative Y.
        if ((velocityX == 0) && (velocityY < 0)) {
            // If the ball hits the upper edge:
            if ((centerY - ballSize) < frameStart.getY()) {
                ballVelocity.setDy(-1 * (ballVelocity.getDy()));
            }
            // If the ball hits the left edge:
            if ((centerX - ballSize) < frameStart.getX()) {
                ballVelocity.setDx(-1 * (ballVelocity.getDy()));
            }
            // If the ball hits the right edge:
            if ((centerX + ballSize) > maxWidth) {
                ballVelocity.setDx(-1 * (ballVelocity.getDy()));
            }
        }
    }
    /**
     * This is the constructor method. it is in charge of setting the
     * ball with given values.
     * @param centerX is the ball's center point x coordinate.
     * @param centerY is the ball's center point y coordinate.
     * @param radius is the ball's radius.
     * @param color is the ball's color.
     */
    public Ball(double centerX, double centerY, int radius, java.awt.Color color) {
        this.center = new Point(centerX, centerY);
        this.radius = radius;
        this.color = color;
    }
    /**
     * This is the getter method. it is in charge of giving the
     * ball's center x value.
     * @return value is the x of the center point.
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * This is the getter method. it is in charge of giving the
     * ball's center y value.
     * @return value is the y of the center point.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * This is the getter method. it is in charge of giving the
     * ball's radius value.
     * @return value is the radius of the ball.
     */
    public int getSize() {
        return radius;
    }
    /**
     * This is the getter method. it is in charge of giving the
     * ball's color.
     * @return value is the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * This method is in charge of drawing the ball to the draw surface.
     * @param surface is the given surface to draw.
     */
    public void drawOn(DrawSurface surface) {
        // Casting to int.
        int centerX = (int) center.getX();
        int centerY = (int) center.getY();
        surface.setColor(color);
        surface.fillCircle(centerX, centerY, radius);
    }
}
