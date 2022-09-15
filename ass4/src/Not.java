//Artiom Berengard
import java.util.Map;
/**
 * The Not class is part of the unary expressions, thus,
 * this class extends the UnaryExpression class.
 * This class contains all of "Not" logic operator methods.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the Nor expressions.
 */
public class Not extends UnaryExpression {
    private Expression expression;
    /**
     * This is a constractor method.
     * @param expression is the value of the expression.
     */
    public Not(Expression expression) {
        this.expression = expression;
    }
    /**
     * This is a getter method.
     * @return value of the first expression.
     */
    public Expression getExpression() {
        return expression;
    }
    /**
     * This method is in charge of evaluating the expression, recursively.
     * @param assignment is the given Mapping values.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return (!this.expression.evaluate(assignment));
        } catch (Exception e) {
            throw new Exception("Exception: the variable was not found");
        }
    }
    /**
     * This method is in charge of evaluating the expression, recursively.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return (!this.expression.evaluate());
        } catch (Exception e) {
            throw new Exception("Exception: the variable was not found");
        }
    }
    /**
     * This method is in charge of assigning an expression to a specific
     * variable, recursively, on the expression.
     * @return the expression after assigning the variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newExpression1 = this.expression.assign(var, expression);
        return new Not(newExpression1);
    }
    /**
     * This method is in charge of converting the expression to a nand version,
     * recursively, on the expression.
     * @return the nand version of the expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(this.expression.nandify(), this.expression.nandify());
    }
    /**
     * This method is in charge of converting the expression to a nor version,
     * recursively, on the expression.
     * @return the nor version of the expression.
     */
    @Override
    public Expression norify() {
        return new Nor(this.expression.norify(), this.expression.norify());
    }
    /**
     * This method is in charge of returning the expression in a simplified
     * way, a more user-friendly writing. recursively, on the expression.
     * @return the simplification of the expression.
     */
    @Override
    public Expression simplify() {
        Expression simpleExpression = this.expression.simplify();

        if (simpleExpression.toString().equals("T")) {
            return new Val(false);
        }
        if (simpleExpression.toString().equals("F")) {
            return new Val(true);
        }
        return new Not(simpleExpression);
    }
    /**
     * This method is in charge of returning the expression in its user-friendly
     * way. recursively, on the expression.
     * @return the string of the expression.
     */
    @Override
    public String toString() {
        return "~(" + expression.toString() + ")";
    }
}
