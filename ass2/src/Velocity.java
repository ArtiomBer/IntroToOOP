// Artiom Berengard
/**
 * This class is in charge of specifying the change in position,
 * on the `x` and the `y` axes.
 */
public class Velocity {
    // Introduction of the class variables.
    private double dx = 0;
    private double dy = 0;
    /**
     * This is the constructor method. it is in charge of setting the
     * velocity with given values.
     * @param dx is the x value of the point's movement.
     * @param dy is the y value of the point's movement.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * The method will give the point's velocity x value.
     * @return the dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * The method will give the point's velocity y value.
     * @return the dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * The method will set the point's velocity x value.
     * @param dx is the dx value of the velocity we want to set.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * The method will set the point's velocity y value.
     * @param dy is the dx value of the velocity we want to set.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * The method will set the point's new point after one move.
     * @param p is the point we want to move
     * @return the new point with it's updated location.
     */
    public Point applyToPoint(Point p) {
        double startPositionX = p.getX();
        double startPositionY = p.getY();
        double movePositionX = (startPositionX + dx);
        double movePositionY = (startPositionY + dy);
        return new Point(movePositionX, movePositionY);
    }
    /**
     * This is the constructor method. it is in charge of setting the
     * velocity with given values.
     * @param angle is the angle value of the movement.
     * @param speed is the speed value of the point's movement.
     * @return The method returns the velocity we have calculated.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double epsilon = 0.0000000001d;
        double dyNumber;
        double dxNumber;
        // In case the cos value is zero, we set the dy to zero.
        if (Math.abs(Math.cos(Math.toRadians(angle))) < epsilon) {
            dyNumber = 0;
        } else {
            dyNumber = (Math.cos(Math.toRadians(angle)));
        }
        // In case the sin value is zero, we set the dx to zero.
        if (Math.abs(Math.sin(Math.toRadians(angle))) < epsilon) {
            dxNumber = 0;
        } else {
            dxNumber = (Math.sin(Math.toRadians(angle)));
        }
        // Applying the speed.
        double dx = dxNumber * speed;
        double dy = dyNumber * speed * (-1);
        return new Velocity(dx, dy);
    }
}
