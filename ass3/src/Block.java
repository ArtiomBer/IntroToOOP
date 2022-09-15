// Artiom Berengard
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * This class has all the needed values to set a block and
 * add is to our game.
 */
public class Block implements Collidable, Sprite {
    private java.awt.Color color;
    private Rectangle rectangle;

    /**
     * The method will return the collision shape of the collidable
     * object.
     * @return the  collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * The method will add the block to the game's collidable list
     * and sprite list.
     * @param g is the game we are initializing.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * The method will set the block's color.
     * @param color is the color we are setting to the block.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * The method will return the block's color.
     * @return color is the block's color we are returning.
     */
    public Color getColor() {
        return color;
    }
    /**
     * The method is the constructor method.
     * @param rectangle is the block's shape that we are setting.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    /**
     * The method notifies the object that we collided at the collision
     * point, we calculate the new velocity and return it.
     * @param collisionPoint is the ball's collision point with the block.
     * @param currentVelocity is the velocity before the collision.
     * @return the new velocity, after collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double collisionPointX = collisionPoint.getX();
        double collisionPointY = collisionPoint.getY();
        Line upperLine = this.getCollisionRectangle().getUpperLine();
        Line bottomLine = this.getCollisionRectangle().getBottomLine();
        Point upperLeftPoint = upperLine.start();
        Point upperRightPoint = upperLine.end();
        Point bottomLeftPoint = bottomLine.start();
        Point bottomRightPoint = bottomLine.end();
        // A bit more strict epsilon to avoid game crush.
        double epsilon = 0.0000001d;
        //special case that the collision point is the upper left corner
        if ((Math.abs(collisionPointX - upperLeftPoint.getX()) <= epsilon)
                && (Math.abs(collisionPointY - upperLeftPoint.getY()) <= epsilon)) {
            if (dx > 0 && dy > 0) {
                currentVelocity.setDy(-1 * dy);
                currentVelocity.setDx(-1 * dx);
                return currentVelocity;
            }
            if (dx > 0 && dy < 0) {
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            if (dx < 0 && dy < 0) {
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            return currentVelocity;
        }
        //special case that the collision point is the upper right corner
        if ((Math.abs(collisionPointX - upperRightPoint.getX()) <= epsilon)
                && (Math.abs(collisionPointY - upperRightPoint.getY()) <= epsilon)) {
            if (dx < 0 && dy > 0) {
                currentVelocity.setDx(-1 * dx);
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            if (dx < 0 && dy < 0) {
                currentVelocity.setDx(-1 * dx);
                return currentVelocity;
            }
            if (dx > 0 && dy > 0) {
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            return currentVelocity;
        }
        //special case that the collision point is the bottom left corner
        if ((Math.abs(collisionPointX - bottomLeftPoint.getX()) <= epsilon)
                && (Math.abs(collisionPointY - bottomLeftPoint.getY()) <= epsilon)) {
            if (dx > 0 && dy < 0) {
                currentVelocity.setDx(-1 * dx);
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            if (dx > 0 && dy > 0) {
                currentVelocity.setDx(-1 * dx);
                return currentVelocity;
            }
            if (dx < 0 && dy < 0) {
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            return currentVelocity;
        }
        //special case that the collision point is the bottom right corner
        if ((Math.abs(collisionPointX - bottomRightPoint.getX()) <= epsilon)
                && (Math.abs(collisionPointY - bottomRightPoint.getY()) <= epsilon)) {
            if (dx < 0 && dy < 0) {
                currentVelocity.setDx(-1 * dx);
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            if (dx < 0 && dy > 0) {
                currentVelocity.setDx(-1 * dx);
                return currentVelocity;
            }
            if (dx > 0 && dy < 0) {
                currentVelocity.setDy(-1 * dy);
                return currentVelocity;
            }
            return currentVelocity;
        }
        // Collision point is part of the upper line.
        if ((Math.abs(collisionPointY - upperLeftPoint.getY()) <= epsilon)
                && (collisionPointX >= upperLeftPoint.getX())
                && (collisionPointX <= upperRightPoint.getX())) {
            currentVelocity.setDy(-1 * dy);
            return currentVelocity;
        }
        // Collision point is part of the bottom line.
        if ((Math.abs(collisionPointY - bottomLeftPoint.getY()) <= epsilon)
                && (collisionPointX >= bottomLeftPoint.getX())
                && (collisionPointX <= bottomRightPoint.getX())) {
            currentVelocity.setDy(-1 * dy);
            return currentVelocity;
        }
        // Collision point is part of the left line.
        if ((Math.abs(collisionPointX - bottomLeftPoint.getX()) <= epsilon)
                && (collisionPointY >= upperLeftPoint.getY())
                && (collisionPointY <= bottomLeftPoint.getY())) {
            currentVelocity.setDx(-1 * dx);
            return currentVelocity;
        }
        // Collision point is part of the right line.
        if ((Math.abs(collisionPointX - bottomRightPoint.getX()) <= epsilon)
                && (collisionPointY >= upperRightPoint.getY())
                && (collisionPointY <= bottomRightPoint.getY())) {
            currentVelocity.setDx(-1 * dx);
            return currentVelocity;
        }
        return currentVelocity;
    }
    /**
     * The method is in charge of drawing the block to out board.
     * @param d is the given draw surface that we want to draw
     *          the block onto.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color color = this.getColor();
        d.setColor(color);
        Rectangle rect = this.getCollisionRectangle();
        int startX = (int) rect.getUpperLeft().getX();
        int startY = (int) rect.getUpperLeft().getY();
        d.fillRectangle(startX, startY, (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(startX, startY, (int) rect.getWidth(), (int) rect.getHeight());
    }
    /**
     * The method is not set at the time, as being instructed.
     */
    @Override
    public void timePassed() {
        //currently, we do nothing
    }
}
