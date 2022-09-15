// Artiom Berengard
import java.util.List;
import java.util.Map;
/**
 * The Expression interface contains all the logic operators
 * and their methods, as described below.
 * The methods are in charge of evaluating, assigning, nandifying,
 * norifying and simplifying the expressions.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in
     * the assignment.
     * @param assignment is the given Mapping values.
     * @return True or false.
     * @throws Exception if the expression contains a variable which is not
     * in the assignment.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;
    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * @return True or false.
     * @throws Exception if the expression contains a variable which is not
     * in the assignment.
     */
    Boolean evaluate() throws Exception;
    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();
    /**
     * @return a nice string representation of the expression.
     */
    String toString();
    /**
     * @param var is the given variable.
     * @param expression is the given expression.
     * @return a new expression in which all occurrences of the variable var are
     * replaced with the provided expression.
     */
    Expression assign(String var, Expression expression);
    /**
     * @return the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     */
    Expression nandify();
    /**
     * @return the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     */
    Expression norify();
    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}