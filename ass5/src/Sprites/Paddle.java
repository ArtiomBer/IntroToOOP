// Artiom Berengard

package Sprites;
import Collision_Detection.Collidable;
import Collision_Detection.Velocity;
import Geometry.Point;
import Geometry.Line;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * This class is in charge of setting the paddle and its values
 * such as the color, the movement and the shape itself.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final biuoop.GUI gui;
    private Rectangle paddleRectangle;
    private final Color color;
    /**
     * This is the constructor method. it is in charge of setting the
     * paddle with given value of the gui.
     * @param gui is the given gui to be set.
     */
    public Paddle(GUI gui) {
        this.gui = gui;
        this.color = Color.orange;
        this.paddleRectangle = new Rectangle(new Point(400, 585), 100, 10);
        this.keyboard = gui.getKeyboardSensor();
    }
    /**
     * This method is in charge of moving the paddle left as long
     * as the paddle does not touch the borders.
     */
    public void moveLeft() {
            double currentX = this.paddleRectangle.getUpperLeft().getX();
            double currentY = this.paddleRectangle.getUpperLeft().getY();
            double width = this.paddleRectangle.getWidth();
            double height = this.paddleRectangle.getHeight();
            double newX;
            if ((currentX) >= 30) {
                newX = currentX - 5;
                Point newPoint = new Point(newX, currentY);
                this.paddleRectangle = new Rectangle(newPoint, width, height);
            }
    }
    /**
     * This method is in charge of moving the paddle left as long
     * as the paddle does not touch the borders.
     */
    public void moveRight() {
        double currentX = this.paddleRectangle.getUpperLeft().getX();
        double currentY = this.paddleRectangle.getUpperLeft().getY();
        double width = this.paddleRectangle.getWidth();
        double height = this.paddleRectangle.getHeight();
        double newX;
        if ((currentX + width) <= 770) {
            newX = currentX + 5;
            Point newPoint = new Point(newX, currentY);
            this.paddleRectangle = new Rectangle(newPoint, width, height);
        }
    }
    /**
     * This getter method returns the paddle's color.
     * @return the color.
     */
    public Color getColor() {
        return color;
    }
    /**
     * This method checks if the left or right keys are pressed
     * and moves the paddle accordingly.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * This method draws the paddle onto the given draw surface.
     * @param d is the given draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color color = this.getColor();
        d.setColor(color);
        Rectangle rect = this.getCollisionRectangle();
        int startX = (int) rect.getUpperLeft().getX();
        int startY = (int) rect.getUpperLeft().getY();
        d.fillRectangle(startX, startY, (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle(startX, startY, (int) rect.getWidth(), (int) rect.getHeight());
    }
    /**
     * This method returns the shape of the paddle.
     * @return the paddle's rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }
    /**
     * This method is in charge of the hit algorithm of the paddle.
     * @param currentVelocity is the given velocity of the ball.
     * @param collisionPoint is the collision point that going to occur.
     * @return new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line paddleSurface = this.paddleRectangle.getUpperLine();
        int fifthOfSurface = (int) paddleSurface.length() / 5;
        double startX = paddleSurface.start().getX();
        double paddleY = paddleSurface.start().getY();
        double speed = Math.sqrt(dx * dx + dy * dy);
        double epsilon = 0.0000001d;
        Line upperLine = this.getCollisionRectangle().getUpperLine();
        Line bottomLine = this.getCollisionRectangle().getBottomLine();
        Point upperLeftPoint = upperLine.start();
        Point upperRightPoint = upperLine.end();
        Point bottomLeftPoint = bottomLine.start();
        Point bottomRightPoint = bottomLine.end();
        Line segment1 = new Line(startX, paddleY, startX + fifthOfSurface, paddleY);
        Line segment2 = new Line(startX + fifthOfSurface + 1, paddleY,
                startX + 2 * fifthOfSurface, paddleY);
        Line segment3 = new Line(startX + 2 * fifthOfSurface + 1, paddleY,
                startX + 3 * fifthOfSurface, paddleY);
        Line segment4 = new Line(startX + 3 * fifthOfSurface + 1, paddleY,
                startX + 4 * fifthOfSurface, paddleY);
        Line segment5 = new Line(startX + 4 * fifthOfSurface + 1, paddleY,
                startX + 5 * fifthOfSurface, paddleY);
        // The collision point is in the first segment
        if (collisionPoint.getX() >= segment1.start().getX()
                && collisionPoint.getX() <= segment1.end().getX()) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        // The collision point is in the second segment
        if (collisionPoint.getX() >= segment2.start().getX()
                && collisionPoint.getX() <= segment2.end().getX()) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        // The collision point is in the third segment
        if (collisionPoint.getX() >= segment3.start().getX()
                && collisionPoint.getX() <= segment3.end().getX()) {
            double newDy = -1 * dy;
            return new Velocity(dx, newDy);
        }
        // The collision point is in the forth segment
        if (collisionPoint.getX() >= segment4.start().getX()
                && collisionPoint.getX() <= segment4.end().getX()) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        // The collision point is in the fifth segment
        if (collisionPoint.getX() >= segment5.start().getX()
                && collisionPoint.getX() <= segment5.end().getX()) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        // Collision point is part of the left line.
        if ((Math.abs(collisionPoint.getX() - bottomLeftPoint.getX()) <= epsilon)
                && (collisionPoint.getY() >= upperLeftPoint.getY())
                && (collisionPoint.getY() <= bottomLeftPoint.getY())) {
            currentVelocity.setDx(-1 * dx);
            return currentVelocity;
        }
        // Collision point is part of the right line.
        if ((Math.abs(collisionPoint.getX() - bottomRightPoint.getX()) <= epsilon)
                && (collisionPoint.getY() >= upperRightPoint.getY())
                && (collisionPoint.getY() <= bottomRightPoint.getY())) {
            currentVelocity.setDx(-1 * dx);
            return currentVelocity;
        }
        double newDy = -1 * dy;
        return new Velocity(dx, newDy);
    }
    /**
     * This method is in charge of adding the paddle to the game.
     * @param g is the given game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}