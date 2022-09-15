// Artiom Berengard
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * The Var class is part of the expressions, thus,
 * this class implements the Expression interface.
 * Val stands for the value of the specific expression, True or False.
 * This class contains all of "Var" logic operator methods.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the Var expressions.
 */
public class Var implements Expression {
    private String var;
    /**
     * This is a constractor method.
     * @param var is the value of the expression.
     */
    public Var(String var) {
        this.var = var;
    }
    /**
     * This method is in charge of evaluating the expression.
     * Because the Var is the final stop of the recursion, we just return the
     * value of Var.
     * @param assignment is the given Mapping values.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return assignment.get(this.var);
        } catch (Exception e) {
            throw new Exception("Exception: variable not found");
        }
    }
    /**
     * This method is in charge of evaluating the expression.
     * Because the Var is the final stop of the recursion, we just return the
     * value of Var.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return this.evaluate(null);
        } catch (Exception e) {
            throw new Exception("Exception: variable not found");
        }
    }
    /**
     * In this method we return a list with one variable - this one.
     * @return value is a list of all the variables.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.add(this.var);
        return variables;
    }
    /**
     * This method is in charge of assigning an expression to a specific
     * variable, in this case, we check if our variable is the given one,
     * if it does, we change it. Else, we continue by returning the value.
     * @return the expression after assigning the variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        }
        return this;
    }
    /**
     * This method is in charge of converting the expression to a nand version,
     * but because the Var is the final stop, we just return the value.
     * @return the nand version of the expression.
     */
    @Override
    public Expression nandify() {
        return this;
    }
    /**
     * This method is in charge of converting the expression to a nor version,
     * but because the Var is the final stop, we just return the value.
     * @return the nor version of the expression.
     */
    @Override
    public Expression norify() {
        return this;
    }
    /**
     * This method is in charge of returning the expression in a simplified
     * way, a more user-friendly writing.
     * Because the Var is the final stop, we just return the value.
     * @return the simplification of the expression.
     */
    @Override
    public Expression simplify() {
        return this;
    }
    /**
     * This method is in charge of returning the expression in its user-friendly
     * way.
     * @return the string of the expression.
     */
    @Override
    public String toString() {
        return this.var;
    }
}
