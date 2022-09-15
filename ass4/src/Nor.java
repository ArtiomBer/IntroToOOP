//Artiom Berengard
import java.util.Map;
/**
 * The Nor class is part of the binary expressions, thus,
 * this class extends the BinaryExpression class.
 * This class contains all of "Nor" logic operator methods.
 * It is in charge of evaluating, assigning, nandifying, norifying
 * and simplifying the Nor expressions.
 */
public class Nor extends BinaryExpression {
    private Expression expression1;
    private Expression expression2;
    /**
     * This is a constractor method.
     * @param expression1 is the value of the first expression.
     * @param expression2 is the value of the second expression.
     */
    public Nor(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    /**
     * This is a getter method.
     * @return value of the first expression.
     */
    public Expression getExpression1() {
        return expression1;
    }
    /**
     * This is a getter method.
     * @return value of the second expression.
     */
    public Expression getExpression2() {
        return expression2;
    }
    /**
     * This method is in charge of evaluating the expression, recursively,
     * on both of the expressions.
     * @param assignment is the given Mapping values.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean first = this.expression1.evaluate(assignment);
        Boolean second = this.expression2.evaluate(assignment);
        try {
            return (!first && !second);
        } catch (Exception e) {
            throw new Exception("Exception: the variable was not found");
        }
    }
    /**
     * This method is in charge of evaluating the expression, recursively,
     * on both of the expressions.
     * @return True of False, evaluating the value.
     */
    @Override
    public Boolean evaluate() throws Exception {
        Boolean first = this.expression1.evaluate();
        Boolean second = this.expression2.evaluate();
        try {
            return (!first && !second);
        } catch (Exception e) {
            throw new Exception("Exception: the variable was not found");
        }
    }
    /**
     * This method is in charge of assigning an expression to a specific
     * variable, recursively, on both of the expressions.
     * @return the expression after assigning the variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression newExpression1 = this.expression1.assign(var, expression);
        Expression newExpression2 = this.expression2.assign(var, expression);
        return new Nor(newExpression1, newExpression2);
    }
    /**
     * This method is in charge of converting the expression to a nand version,
     * recursively, on both of the expressions.
     * @return the nand version of the expression.
     */
    @Override
    public Expression nandify() {
        Nand basicNand =  new Nand(this.expression1.nandify(), this.expression1.nandify());
        Nand basicNand2 =  new Nand(this.expression2.nandify(), this.expression2.nandify());
        Nand combinedNand =  new Nand(basicNand, basicNand2);
        return new Nand(combinedNand, combinedNand);
    }
    /**
     * This method is in charge of converting the expression to a nor version,
     * recursively, on both of the expressions.
     * @return the nor version of the expression.
     */
    @Override
    public Expression norify() {
        return new Nor(this.expression1.norify(), this.expression2.norify());
    }
    /**
     * This method is in charge of returning the expression in a simplified
     * way, a more user-friendly writing. recursively, on both of the expressions.
     * @return the simplification of the expression.
     */
    @Override
    public Expression simplify() {
        Expression simpleExpression1 = this.expression1.simplify();
        Expression simpleExpression2 = this.expression2.simplify();
        // Checking the conditions for simplifications.
        if (simpleExpression1.toString().equals("T")) {
            return new Val(false);
        }
        if (simpleExpression2.toString().equals("T")) {
            return new Val(false);
        }
        if (simpleExpression1.toString().equals("F")) {
            return new Not(simpleExpression2).simplify();
        }
        if (simpleExpression2.toString().equals("F")) {
            return new Not(simpleExpression1).simplify();
        }
        if (simpleExpression1.toString().equals(simpleExpression2.toString())) {
            return new Not(simpleExpression1).simplify();
        }
        return new Nor(simpleExpression1, simpleExpression2);
    }
    /**
     * This method is in charge of returning the expression in its user-friendly
     * way. recursively, on both of the expressions.
     * @return the string of the expression.
     */
    @Override
    public String toString() {
        return "(" + expression1.toString() + " V "
                + expression2.toString() + ")";
    }
}
