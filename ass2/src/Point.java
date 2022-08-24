// Artiom Berengard
/**
 * This class has an x and y value. This class is capable of measuring
 * the distance to other given point and compare the two points as well.
 */
public class Point {
    // Introduction of the class variables.
    private double x;
    private double y;
    /**
     * This is the constructor method. it is in charge of setting the
     * point with given values.
     * @param x is the x value of the point.
     * @param y is the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * The method will calculate the distance to the other given point.
     * @param other is the other given point.
     * @return the distance to the given point.
     */
    public double distance(Point other) {
        double xDistance = (this.x - other.x);
        double yDistance = (this.y - other.y);
        double distance = xDistance * xDistance + yDistance * yDistance;
        return Math.sqrt(distance);
    }
    /**
     * The method will determine whether the two points are equal.
     * In this method we use the epsilon as a more precise way of comparison
     * dor doubles.
     * @param other is the other given point.
     * @return the boolean value of the comparison.
     */
    public boolean equals(Point other) {
        double epsilon = 0.0000000001d;
        boolean firstComparison = Math.abs(this.x - other.x) < epsilon;
        boolean secondComparison = Math.abs(this.y - other.y) < epsilon;
        return firstComparison && secondComparison;
    }
    /**
     * The method will give the point's x value.
     * @return the x value of the point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * The method will give the point's y value.
     * @return the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
