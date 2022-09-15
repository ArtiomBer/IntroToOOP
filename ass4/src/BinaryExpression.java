//Artiom Berengard
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * The BinaryExpression class is part of the base expression, thus,
 * this class extends the BaseExpression class.
 * This class contains all the logic operators that contain
 * two expressions.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression expression1;
    private Expression expression2;
    /**
     * This is a constractor method.
     * @param expression1 is the value of the first expression.
     * @param expression2 is the value of the second expression.
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    /**
     * This is a constractor method, without arguments.
     */
    public BinaryExpression() {
        this.expression1 = getExpression1();
        this.expression2 = getExpression2();
    }
    /**
     * This is a getter method.
     * @return value is the first expression of the class.
     */
    public Expression getExpression1() {
        return expression1;
    }
    /**
     * This is a getter method.
     * @return value is the second expression of the class.
     */
    public Expression getExpression2() {
        return expression2;
    }
    /**
     * In this method we get rid of repeating variables by converting
     * the list to a set and vise versa.
     * @return value is a list of all the variables.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = this.getExpression1().getVariables();
        variables.addAll(this.getExpression2().getVariables());
        Set<String> variablesSet = new LinkedHashSet<>(variables);
        variables.clear();
        variables.addAll(variablesSet);
        return variables;
    }
}
