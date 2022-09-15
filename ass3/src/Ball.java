// Artiom Berengard
import biuoop.DrawSurface;
/**
 * This class has the radius, center point, color, velocity,
 * and game environment values of a ball.
 * This class is capable of returning all of those variables and drawing
 * the ball to the screen.
 * This class also has option to move the ball one step to its velocity.
 */
public class Ball implements Sprite {
    // Announcing the ball's variables.
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity ballVelocity = new Velocity(0, 0);
    private GameEnvironment collidables;
    /**
     * The method will add the ball to the sprite list of the game.
     * @param g is the game we are initializing.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * The method will set the ball's game environment .
     * @param collidables is the collidables list that the current
     *                    game has.
     */
    public void setGameEnvironment(GameEnvironment collidables) {
        this.collidables = collidables;
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
        Point center = this.getCenter();
        Line trajectory = new Line(center, this.getVelocity().applyToPoint(center));
        GameEnvironment allCollidables = this.collidables;
        if (allCollidables.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            CollisionInfo collision = allCollidables.getClosestCollision(trajectory);
            Point collisionPoint = collision.collisionPoint();
            Collidable collisionObject = collision.collisionObject();
            Rectangle collidingRect = collisionObject.getCollisionRectangle();
            // A fix for special case that the ball gets stuck in the paddle
            if ((center.getX() > collidingRect.getUpperLeft().getX())
                    && (center.getX() < (collidingRect.getUpperLeft().getX() + collidingRect.getWidth()))
                    && (center.getY() > collidingRect.getUpperLeft().getY())
                    && (center.getY() < (collidingRect.getUpperLeft().getY() + collidingRect.getHeight()))) {
                this.center = this.getVelocity().applyToPoint(new Point(center.getX(),
                        center.getY() - 2 * this.getSize()));
            }
            this.setVelocity(collisionObject.hit(collisionPoint, ballVelocity));
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
     * This is the constructor method. it is in charge of setting the
     * ball with given values.
     * @param center is the ball's center point.
     * @param radius is the ball's radius.
     * @param color is the ball's color.
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
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
    @Override
    public void drawOn(DrawSurface surface) {
        // Casting to int.
        int centerX = (int) center.getX();
        int centerY = (int) center.getY();
        surface.setColor(color);
        surface.fillCircle(centerX, centerY, radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * This is a getter method. it is in charge of returning the
     * ball's center point.
     * @return the center point.
     */
    public Point getCenter() {
        return this.center;
    }
}