// Artiom Berengard
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * The Val class is part of the expressions, thus,
 * this class implements the Expression interface.
 * Val stands for the value of the specific expression, True or False.
 * This class contains all of "Val" logic operator methods.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the Val expressions.
 */
public class Val implements Expression {
    private Boolean value;
    /**
     * This is a constractor method.
     * @param value is the value of the expression.
     */
    public Val(Boolean value) {
        this.value = value;
    }
    /**
     * This method is in charge of evaluating the expression.
     * Because the Val is the final stop of the recursion, we just return the
     * value of Val.
     * @param assignment is the given Mapping values.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return this.value;
        } catch (Exception e) {
            throw new Exception("Exception: value not found");
        }
    }
    /**
     * This method is in charge of converting the expression to a nand version,
     * but because the Val is the final stop, we just return the value.
     * @return the nand version of the expression.
     */
    @Override
    public Expression nandify() {
        return this;
    }
    /**
     * This method is in charge of converting the expression to a nor version,
     * but because the Val is the final stop, we just return the value.
     * @return the nor version of the expression.
     */
    @Override
    public Expression norify() {
        return this;
    }
    /**
     * This method is in charge of returning the expression in a simplified
     * way, a more user-friendly writing.
     * Because the Val is the final stop, we just return the value.
     * @return the simplification of the expression.
     */
    @Override
    public Expression simplify() {
        return this;
    }
    /**
     * This method is in charge of evaluating the expression.
     * Because the Val is the final stop of the recursion, we just return the
     * value of Val.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return this.value;
        } catch (Exception e) {
            throw new Exception("Exception: value not found");
        }
    }
    /**
     * In this method we return an empty list, Val doesn't have any variables.
     * @return value is a list of all the variables.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        return variables;
    }
    /**
     * This method is in charge of assigning an expression to a specific
     * variable, in this case, we just change the current value to the
     * given variable.
     * @return the expression after assigning the variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(this.value);
    }
    /**
     * This method is in charge of returning the expression in its user-friendly
     * way.
     * @return the string of the expression.
     */
    @Override
    public String toString() {
        if (this.value) {
            return "T";
        }
        return "F";
    }
}
