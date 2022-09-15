//Artiom Berengard
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * The UnaryExpression class is part of the base expression, thus,
 * this class extends the BaseExpression class.
 * This class contains all the logic operators that contain
 * one expression.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;
    /**
     * This is a constractor method.
     * @param expression is the value of the expression.
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;

    }
    /**
     * This is a constractor method, without arguments.
     */
    public UnaryExpression() {
        this.expression = getExpression();
    }
    /**
     * This is a getter method.
     * @return returns the expression of the class.
     */
    public Expression getExpression() {
        return expression;
    }
    /**
     * In this method we get rid of repeating variables by converting
     * the list to a set and vise versa.
     * @return value is a list of all the variables.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = this.getExpression().getVariables();
        Set<String> variablesSet = new LinkedHashSet<>(variables);
        variables.clear();
        variables.addAll(variablesSet);
        return variables;
    }
}
