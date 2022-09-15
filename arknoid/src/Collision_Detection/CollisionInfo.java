// Artiom Berengard
package Collision_Detection;
import Geometry.Point;
/**
 * This class has all the needed fields to inform about the collision.
 * fields such as the collision point or the collision object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * The method is the constructor method.
     * @param collisionPoint is the  collision point we are setting.
     * @param collisionObject is the  collision object we are setting.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * This is the setter method for the collision point.
     * @return is the collision point, the location where the
     * collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * This is the setter method for the collision object.
     * @return is the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}