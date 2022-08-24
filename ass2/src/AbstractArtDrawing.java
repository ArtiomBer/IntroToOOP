// Artiom Berengard
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * This class is in charge of generating random lines and drawing
 * them into the screen.
 */
public class AbstractArtDrawing {
    private static GUI gui = new GUI("Random Lines Example", 700, 700);
    /**
     * The method will generate lines and draw them into the screen.
     */
    public void generateAndDrawLines() {
        DrawSurface drawBoard = gui.getDrawSurface();
        // Generating new lines and saving them into an array.
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            lines[i] = generateRandomLine();
        }
        // Drawing the lines.
        for (Line line : lines) {
            drawLine(line, drawBoard);
        }
        // Coloring the intersection points with the color red.
        for (Line line : lines) {
            for (int i = 1; i < lines.length; i++) {
                if (line.isIntersecting(lines[i])) {
                    if (line.intersectionWith(lines[i]) != null) {
                        drawBoard.setColor(Color.RED);
                        Point intersectionPoint = line.intersectionWith(lines[i]);
                        // Casting from double to int.
                        int intersectionPointX = (int) intersectionPoint.getX();
                        int intersectionPointY = (int) intersectionPoint.getY();
                        drawBoard.fillCircle(intersectionPointX, intersectionPointY, 3);
                    }
                }
            }
        }
        gui.show(drawBoard);
    }
    /**
     * The method will add the line to the drawing surface.
     * The method will also draw the middle of each line in blue.
     * @param draw is the draw surface.
     * @param line is the given line to add.
     */
    static void drawLine(Line line, DrawSurface draw) {
        // Casting from double to int.
        int startX = (int) line.start().getX();
        int startY = (int) line.start().getY();
        int endX = (int) line.end().getX();
        int endY = (int) line.end().getY();
        // Drawing the lines in black.
        draw.setColor(Color.BLACK);
        draw.drawLine(startX, startY, endX, endY);
        // Drawing the middle point in blue.
        Point middlePoint = line.middle();
        int middlePointX = (int) middlePoint.getX();
        int middlePointY = (int) middlePoint.getY();
        draw.setColor(Color.BLUE);
        draw.fillCircle(middlePointX, middlePointY, 3);
    }
    /**
     * The method will generate random line in the given drawing
     * surface sizes.
     * @return value is the generated line.
     */
    Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        double startPointX = rand.nextInt(gui.getDrawSurface().getWidth() - 1) + 1; // get double in range 1-399
        double startPointY = rand.nextInt(gui.getDrawSurface().getHeight() - 1) + 1; // get double in range 1-299
        double endPointX = rand.nextInt(gui.getDrawSurface().getWidth() - 1) + 1; // get double in range 1-399
        double endPointY = rand.nextInt(gui.getDrawSurface().getHeight() - 1) + 1; // get double in range 1-299
        Point start = new Point(startPointX, startPointY); // generate random start point
        Point end = new Point(endPointX, endPointY); // generate random end point
        return new Line(start, end);
    }
    /**
     * This is the main function.
     * @param args are the arguments received in the main function.
     */
    public static void main(String[] args) {
        AbstractArtDrawing linesDrawing = new AbstractArtDrawing();
        linesDrawing.generateAndDrawLines();
    }
}
