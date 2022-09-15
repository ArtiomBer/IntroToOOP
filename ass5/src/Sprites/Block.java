// Artiom Berengard

package Sprites;
import Collision_Detection.Collidable;
import Collision_Detection.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Line;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has all the needed values to set a block and
 * add is to our game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private java.awt.Color color;
    private Rectangle rectangle;
    private List<HitListener> hitListeners;
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
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * The method notifies the object that we collided at the collision
     * point, we calculate the new velocity and return it.
     * @param collisionPoint is the ball's collision point with the block.
     * @param currentVelocity is the velocity before the collision.
     * @param hitter is the hitting ball.
     * @return the new velocity, after collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
            this.notifyHit(hitter);
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
            this.notifyHit(hitter);
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
            this.notifyHit(hitter);
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
            this.notifyHit(hitter);
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
            this.notifyHit(hitter);
            currentVelocity.setDy(-1 * dy);
            return currentVelocity;
        }
        // Collision point is part of the bottom line.
        if ((Math.abs(collisionPointY - bottomLeftPoint.getY()) <= epsilon)
                && (collisionPointX >= bottomLeftPoint.getX())
                && (collisionPointX <= bottomRightPoint.getX())) {
            this.notifyHit(hitter);
            currentVelocity.setDy(-1 * dy);
            return currentVelocity;
        }
        // Collision point is part of the left line.
        if ((Math.abs(collisionPointX - bottomLeftPoint.getX()) <= epsilon)
                && (collisionPointY >= upperLeftPoint.getY())
                && (collisionPointY <= bottomLeftPoint.getY())) {
            this.notifyHit(hitter);
            currentVelocity.setDx(-1 * dx);
            return currentVelocity;
        }
        // Collision point is part of the right line.
        if ((Math.abs(collisionPointX - bottomRightPoint.getX()) <= epsilon)
                && (collisionPointY >= upperRightPoint.getY())
                && (collisionPointY <= bottomRightPoint.getY())) {
            this.notifyHit(hitter);
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
    /**
     * The method is in charge of removing a block from the game.
     * @param game is the given game.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
        int currNumOfBlocks = game.getNumOfBlocks().getValue();
        game.setNumOfBlocks(currNumOfBlocks - 1);
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * The method is in charge of updating all the listeners that
     * a hit has occurred.
     * @param hitter is the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
