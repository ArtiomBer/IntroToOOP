// Artiom Berengard

package Sprites;
import Collision_Detection.Collidable;
import Collision_Detection.CollisionInfo;
import Geometry.Point;
import Geometry.Line;
import Geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is in charge creating the game environment,
 * it has a list of collidables and can return the closest
 * collision point of a ball to each collidable.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * The constructor method.
     * @param collidables is a given list of collidables which
     *                    we will set.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }
    /**
     * The constructor method, creates a new collidables list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }
    /**
     * This method will add a collidable to the list.
     * @param c is the given collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * This method will return the collidables list.
     * @return is the collidables list in this environment.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
    /**
     * This method will calculate the closest collision point
     * of a ball to the collidable.
     * @param trajectory is the line which is going to be part of the
     *                   collision.
     * @return is the collision info of the going to occur collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> closestIntersections = new ArrayList<>();
        List<Collidable> allColliding = new ArrayList<>();
        for (Collidable collidable: this.collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point closestToStart = trajectory.closestIntersectionToStartOfLine(rect);
            if (closestToStart != null) {
                closestIntersections.add(closestToStart);
                allColliding.add(collidable);
            }
        }
        if (!closestIntersections.isEmpty()) {
            Point closestPoint = closestIntersections.get(0);
            Collidable closestObject = allColliding.get(0);
            double minDistance = trajectory.start().distance(closestPoint);
            for (Point p: closestIntersections) {
                if (trajectory.start().distance(p) < minDistance) {
                    minDistance = trajectory.start().distance(p);
                    closestPoint = p;
                    closestObject = allColliding.get(closestIntersections.indexOf(p));
                }
            }
            return new CollisionInfo(closestPoint, closestObject);
        }
        return null;
    }
    /**
     * This method is in charge of removing a collidable from a list
     * of collidables.
     * @param c is the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        if (collidables.contains(c)) {
            this.collidables.remove(c);
        }
    }
}