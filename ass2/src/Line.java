// Artiom Berengard 321114704
/**
 * This class is connecting two points into a line. This class is capable
 * of measuring the distance to other given point and compare the two points as well.
 * In this class you can also test if two lines intersect and find the intersection
 * point. This class is also capable of finding the middle point of the line.
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * This is the constructor method. it is in charge of setting the
     * line with given values of starting and ending points.
     * @param end  is the ending value of the line.
     * @param start is the starting value of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * This is another constructor method. it is in charge of setting the
     * line with given x and y values of the points.
     * @param x1 is the x value of the starting point of the line.
     * @param y1 is the y value of the starting point of the line.
     * @param x2 is the x value of the ending point of the line.
     * @param y2 is the y value of the ending point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * This method is in charge of calculating the length of the line
     * via the distance method from the Point class.
     * @return value is the length.
     */
    public double length() {
        return this.start.distance(end);
    }
    /**
     * This method is in charge of calculating and returning the
     * middle of the line.
     * @return value is the middle point.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }
    /**
     * This method is returning the starting point of the line.
     * @return value is the start point.
     */
    public Point start() {
        return this.start;
    }
    /**
     * This method is returning the ending point of the line.
     * @return value is the end point.
     */
    public Point end() {
        return this.end;
    }
    /**
     * This method is checking if two lines are intersecting.
     * The logic behind the code is written below.
     * @param other is the other line to be compared.
     * @return values are true or false, respectively if there is an
     * intersection point or not.
     */
    public boolean isIntersecting(Line other) {
        Point thisStart = this.start;
        Point thisEnd = this.end;
        Point otherStart = other.start;
        Point otherEnd = other.end;
        // Check the base case in which at least two points are equal.
        if ((thisStart.equals(otherStart))
                 || (thisStart.equals(otherEnd))
                 || (thisEnd.equals(otherStart))
                 || (thisEnd.equals(otherEnd))) {
            return true;
        }
        // Calculating the slope or vector orientations for all the possible cases.
        double slopeOfThisStart = checkSlope(otherStart, otherEnd, thisStart);
        double slopeOfThisEnd = checkSlope(otherStart, otherEnd, thisEnd);
        double slopeOfOtherStart = checkSlope(thisStart, thisEnd, otherStart);
        double slopeOfOtherEnd = checkSlope(thisStart, thisEnd, otherEnd);
        /*
         * The segments intersect if and only if both of the lines have different
         * vector orientation or slope. Thus, we return True.
         */
        if ((slopeOfThisStart != slopeOfThisEnd) && (slopeOfOtherStart != slopeOfOtherEnd)) {
            return true;
        }
        /*
         * Check if the start and end of the other line are collinear with
         * this line's start point and check if this point is in the segment.
         */
        if ((slopeOfThisStart == 0) && isInSegment(otherStart, thisStart, otherEnd)) {
            return true;
        }
        /*
         * Check if the start and end of the other line are collinear with
         * this line's end point and check if this point is in the segment.
         */
        if ((slopeOfThisEnd == 0) && isInSegment(otherStart, thisEnd, otherEnd)) {
            return true;
        }
        /*
         * Check if the start and end of this line are collinear with the
         * other line's start point and check if this point is in the segment.
         */
        if ((slopeOfOtherStart == 0) && isInSegment(thisStart, otherStart, thisEnd)) {
            return true;
        }
        /*
         * Check if the start and end of this line are collinear with the
         * other line's end point and check if this point is in the segment.
         * In case none of the above are matched, the lines do not intersect.
         */
        return (slopeOfOtherEnd == 0) && isInSegment(thisStart, otherEnd, thisEnd);
    }
    /**
     * This method determines whether the slope or the vector's orientation
     * is clockwise, counterclockwise or identical.
     * @param start is the starting point of the vector.
     * @param end is the ending point of the vector.
     * @param test is the given point to be tested for its orientation
     *             compared to the other two points.
     * @return values are 0,1 and 2 as followed:
     * Return 0 if the test point is collinear with the given points.
     * Return 1 if the vectors originated are oriented clockwise.
     * Return 2 if the vectors originated are oriented counterclockwise.
     */
    public double checkSlope(Point start, Point end, Point test) {
        // Casting the x and y values of the points for aesthetics.
        double startX = start.getX();
        double startY = start.getY();
        double endX = end.getX();
        double endY = end.getY();
        double testX = test.getX();
        double testY = test.getY();
        double slope = (((endY - startY) * (testX - endX))
                 - ((testY - endY) * (endX - startX)));
        // This method is returning exact integers for later comparison.
        if (slope == 0) {
            return 0;
        }
        if (slope  > 0) {
            return 1;
        } else {
            return 2;
        }
    }
    /**
     * This method determines whether there is an intersection point
     * between two line or not. The logic is written below.
     * @param other is the given line to be compared with, in order
     *              to find the intersection point.
     * @return the intersection point if exists and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // Introduction of the variables.
        Line line1 = this;
        Point thisStart = this.start;
        Point thisEnd = this.end;
        Point otherStart = other.start;
        Point otherEnd = other.end;
        double epsilon = 0.0000000001d;
        double thisStartX = line1.start.getX();
        double thisStartY = line1.start.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        // If there is an intersection, we divide into cases.
        if (this.isIntersecting(other)) {
            // If the lines are equal, no intersection point is returned.
            if (this.equals(other)) {
                return null;
            }
            /*
             * If the slope equals to zero, the line is parallel to the axes,
             * and it's calculation differentiating from the calculations below.
             * The first case is two vertical lines.
             */
            if (isVertical(line1) && isVertical(other)) {
                // Casting the variables.
                Point line1Start = line1.start;
                Point line1End = line1.end;
                double distanceStartToStart = line1Start.distance(other.start);
                double distanceStartToEnd = line1Start.distance(other.end);
                double distanceEndToStart = line1End.distance(other.start);
                double distanceEndToEnd = line1End.distance(other.end);
                double totalLength = line1.length() + other.length();
                /*
                 * Calculating and comparing the lengths in order to determine
                 * whether there is only one intersection point.
                 * If the lengths are smaller than the total length, we return null.
                 */
                if ((totalLength - distanceStartToStart >= epsilon)
                        && (totalLength - distanceStartToEnd >= epsilon)
                        && (totalLength - distanceEndToStart >= epsilon)
                        && (totalLength - distanceEndToEnd >= epsilon)) {
                    return null;
                } else {
                    if (thisStart.equals(otherStart)) {
                        return thisStart;
                    }
                    if (thisStart.equals(otherEnd)) {
                        return thisStart;
                    }
                    if (otherStart.equals(thisEnd)) {
                        return otherStart;
                    }
                    if (otherEnd.equals(thisEnd)) {
                        return thisEnd;
                    }
                }
            }
            /*
             * The next two cases are if only one line is vertical.
             * In those cases we calculate only the y value.
             */
            if (isVertical(line1) && !isVertical(other)) {
                double intersectionX = line1.start.getX();
                double otherSlope = calculateSlope(other);
                double otherB = (otherStartY + ((-1) * (otherSlope) * otherStartX));
                double intersectionY = (otherSlope * thisStartX) + otherB;
                return new Point(intersectionX, intersectionY);
            }
            if ((!isVertical(line1)) && isVertical(other)) {
                double intersectionX = other.start.getX();
                double otherSlope = calculateSlope(line1);
                double otherB = (thisStartY + ((-1) * (otherSlope) * thisStartX));
                double intersectionY = (otherSlope * intersectionX) + otherB;
                return new Point(intersectionX, intersectionY);
            }
            // The third case is if the lines are not parallel.
            if (calculateSlope(this) != calculateSlope(other)) {
                return calculateIntersectionPoint(this, other);
            } else {
                /*
                 * If the lines are parallel, there will be an intersection
                 * point if and only if, the segment start or end with the same
                 * point and intersect in that point only.
                 */
                // Casting for aesthetics of the code.
                double thisEndX = this.end.getX();
                double thisEndY = this.end.getY();
                double otherEndX = other.end.getX();
                double otherEndY = other.end.getY();
                // Testing if there is only one intersection point.
                if ((thisStartX > otherStartX) && (thisStartX < otherEndX)
                        && (thisStartY > otherStartY) && (thisStartY < otherEndY)) {
                    return null;
                }
                if ((thisEndX > otherStartX) && (thisEndX < otherEndX)
                        && (thisEndY > otherStartY) && (thisEndY < otherEndY)) {
                    return null;
                }
                if ((otherStartX > thisStartX) && (otherStartX < thisEndX)
                        && (otherStartY > thisStartY) && (otherStartY < thisEndY)) {
                    return null;
                }
                if ((otherEndX > thisStartX) && (otherEndX < thisEndX)
                        && (otherEndY > thisStartY) && (otherEndY < thisEndY)) {
                    return null;
                    /*
                     * If we haven't returned null yet, and the lines are parallel
                     * with no intersections in between the start and end points
                     * of each line, we check if one of the points are equal
                     * and return thr point.
                     */
                } else {
                    if (thisStart.equals(otherStart)) {
                        return thisStart;
                    }
                    if (thisStart.equals(otherEnd)) {
                        return thisStart;
                    }
                    if (otherStart.equals(thisEnd)) {
                        return otherStart;
                    }
                    if (otherEnd.equals(thisEnd)) {
                        return thisEnd;
                    }
                }
            }
        }
        // In case there is no intersection point, we return null.
        return null;
    }
    /**
     * This method determines whether two lines are equal.
     * @param other is the given line to be compared with, in order
     * to determine if the lines identical.
     * @return the boolean value if the lines are equal.
     */
    public boolean equals(Line other) {
        // Casting the start and end values of the lines for aesthetics.
        Point thisStart = this.start;
        Point thisEnd = this.end;
        Point otherStart = other.start;
        Point otherEnd = other.end;
        return (thisStart.equals(otherStart) && thisEnd.equals(otherEnd))
                || (thisStart.equals(otherEnd) && thisEnd.equals(otherStart));
    }
    /**
     * This method determines whether one point lies in a segment
     * of two other points.
     * @param start is the given starting point of the segment.
     * @param end is the given ending point of the segment.
     * @param test is the point tested for being in the segment.
     * @return the boolean value if the point is part of the segment.
     */
    public boolean isInSegment(Point start, Point test, Point end) {
        /*
         * Comparing the maximal and minimal values to determine if
         * the tested point is in between.
         */
        return (test.getX() >= Math.min(start.getX(), end.getX())
                && test.getX() <= Math.max(start.getX(), end.getX())
                && test.getY() >= Math.min(start.getY(), end.getY())
                && test.getY() <= Math.max(start.getY(), end.getY()));
    }
    /**
     * This method calculates the intersection point using the
     * algebraic equation of a line, and it's slope.
     * @param line1 is the first given line to be compared.
     * @param line2 is the second given line to be compared.
     * @return the intersection point.
     */
    public Point calculateIntersectionPoint(Line line1, Line line2) {
        // Casting for the lines' points for aesthetics.
        double thisStartX = line1.start.getX();
        double thisStartY = line1.start.getY();
        double otherStartX = line2.start.getX();
        double otherStartY = line2.start.getY();
        // In case the slopes are not zero, we will calculate them.
        double thisSlope = calculateSlope(line1);
        double otherSlope = calculateSlope(line2);
        // calculating the y intercept of the lines, the 'b' in the equation.
        double thisB = (thisStartY + ((-1) * (thisSlope) * thisStartX));
        double otherB = (otherStartY + ((-1) * (otherSlope) * otherStartX));
        // calculate the intersection x and y values.
        double intersectionX = (thisB - otherB) / (otherSlope - thisSlope);
        double intersectionY = (otherSlope * intersectionX) + otherB;
        return new Point(intersectionX, intersectionY);
    }
    /**
     * This method calculates the slope of two points using the
     * algebraic equation.
     * @param line is the given line, with its start and ending points.
     * @return the slope of the given line.
     */
    public double calculateSlope(Line line) {
        double lineStartX = line.start.getX();
        double lineStartY = line.start.getY();
        double lineEndX = line.end.getX();
        double lineEndY = line.end.getY();
        return ((lineStartY - lineEndY) / (lineStartX - lineEndX));
    }
    /**
     * This method checks if a line is vertical.
     * @param line is the given line, with its start and ending points.
     * @return the boolean value of the test.
     */
    public boolean isVertical(Line line) {
        double startX = line.start.getX();
        double endX = line.end.getX();
        return startX == endX;
    }
}