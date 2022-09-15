// Artiom Berengard
/**
 * This interface is for all the collidable objects
 * such as a block or the paddle.
 */
public interface Collidable {
    /**
     * The method will return the collision shape of the collidable
     * object.
     * @return the  collision rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * The method notifies the object that we collided at the collision
     * point, we calculate the new velocity and return it.
     * @param collisionPoint is the ball's collision point with the block.
     * @param currentVelocity is the velocity before the collision.
     * @return the new velocity, after collision.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}