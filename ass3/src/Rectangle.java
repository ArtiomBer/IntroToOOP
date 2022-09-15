// Artiom Berengard
import java.util.ArrayList;
import java.util.List;
/**
 * This class is setting the rectangle with all of its fields, such
 * as the width and height, the four lines and the upper left point.
 */
public class Rectangle {
    // The rectangle's fields
     private double width;
     private double height;
     private Point upperLeft;
     private Line upperLine;
     private Line bottomLine;
     private Line leftLine;
     private Line rightLine;
    /**
     * This is the constructor method. it is in charge of setting the
     * rectangle with given values.
     * @param upperLeft is the upper left point of the rectangle.
     * @param width is the width value of the rectangle.
     * @param height is the height value of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLine = new Line(upperLeft, upperRight);
        this.bottomLine = new Line(bottomLeft, bottomRight);
        this.leftLine = new Line(bottomLeft, upperLeft);
        this.rightLine = new Line(bottomRight, upperRight);
    }
    /**
     * This method is in charge of searching for intersection points and
     * return a list of those points with the specified line.
     * @param line is the given line to be tested.
     * @return a (possibly empty) list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<>();
        Line upperLine = this.upperLine;
        Line bottomLine = this.bottomLine;
        Line leftLine = this.leftLine;
        Line rightLine = this.rightLine;
        // Checking the intersection of the line with the upper line of the rectangle.
        if (line.isIntersecting(upperLine) && (line.intersectionWith(upperLine) != null)) {
            intersectionList.add(line.intersectionWith(upperLine));
        }
        // Checking the intersection of the line with the bottom line of the rectangle.
        if (line.isIntersecting(bottomLine) && (line.intersectionWith(bottomLine) != null)) {
            intersectionList.add(line.intersectionWith(bottomLine));
        }
        // Checking the intersection of the line with the left line of the rectangle.
        if (line.isIntersecting(leftLine) && (line.intersectionWith(leftLine) != null)) {
            intersectionList.add(line.intersectionWith(leftLine));
        }
        // Checking the intersection of the line with the right line of the rectangle.
        if (line.isIntersecting(rightLine) && (line.intersectionWith(rightLine) != null)) {
            intersectionList.add(line.intersectionWith(rightLine));
        }
        return intersectionList;
    }
    /**
     * This getter method returns the width of the rectangle.
     * @return the width value.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * This getter method returns the height of the rectangle.
     * @return the height value.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * This getter method returns the upper left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * This getter method returns the upper line of the rectangle.
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
    /**
     * This getter method returns the bottom line of the rectangle.
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }
    /**
     * This getter method returns the left line of the rectangle.
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }
    /**
     * This getter method returns the right line of the rectangle.
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return this.rightLine;
    }
}