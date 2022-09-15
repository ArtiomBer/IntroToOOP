//Artiom Berengard
import java.util.List;
import java.util.Map;
/**
 * The BaseExpression class is main expression, the given undivided
 * expression, that implements the expression interface.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the basic given expression.
 */
public abstract class BaseExpression implements Expression {
    private Expression baseExpression;
    /**
     * This is a getter method.
     * @return value is the base expression of the class.
     */
    public Expression getBaseExpression() {
        return this.baseExpression;
    }
    /**
     * This is a constractor method.
     * @param baseExpression is the value of the given expression.
     */
    public BaseExpression(Expression baseExpression) {
        this.baseExpression = baseExpression;
    }
    /**
     * This is a constractor method.
     */
    public BaseExpression() {
        this.baseExpression = getBaseExpression();
    }
    /**
     * This method is in charge of evaluating the expression, recursively.
     * @param assignment is the given Mapping values.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return this.baseExpression.evaluate(assignment);
        } catch (Exception e) {
            throw new Exception("Exception: base expression not found");
        }
    }
    /**
     * This method is in charge of evaluating the expression, recursively.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return this.baseExpression.evaluate();
        } catch (Exception e) {
            throw new Exception("Exception: base expression not found");
        }
    }
    /**
     * This method is in charge of returning the expression's
     * variables list, recursively.
     * @return a list of strings, the variables of the expression.
     */
    @Override
    public List<String> getVariables() {
        return this.baseExpression.getVariables();
    }
    /**
     * This method is in charge of assigning an expression to a specific
     * variable, recursively.
     * @return the expression after assigning the variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this.baseExpression.assign(var, expression);
    }
}
