// Artiom Berengard
import java.util.Map;
import java.util.TreeMap;
/**
 * The ExpressionsTest is the class that in charge of running the main
 * method and testing the methods that are in charge of evaluating, assigning,
 * nandifying, norifying and simplifying the expressions.
 */
public class ExpressionsTest {
    /**
     * The main method that tests all the methods in the project.
     * @param args are the given args, which are irrelevant.
     * in the assignment.
     */
    public static void main(String[] args) {
        Expression testExpression = new And(new Not(new Or(new Var("x"), new Var("y"))),
                new Or(new Var("z"), new Val(false)));
        Map<String, Boolean> assign = new TreeMap<>();
        assign.put("x", false);
        assign.put("y", false);
        assign.put("z", true);
        try {
            System.out.println(testExpression);
            System.out.println(testExpression.evaluate(assign));
            System.out.println(testExpression.nandify());
            System.out.println(testExpression.norify());
            System.out.println(testExpression.simplify());
        } catch (Exception e) {
            System.out.println("Process interrupted");
        }
    }
}
