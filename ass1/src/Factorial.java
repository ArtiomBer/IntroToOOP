// Artiom Berengard

/**
 * The Factorial class provides the factorial of a number - n with two methods.
 * Once with recursion and then using an iterative method.
 */
public class Factorial {
    /**
     * The Factorial method will calculate the factorial of the number - n twice.
     * Once with a recursive definition and then with iterative procedure.
     * @param args The method will take n as the input and print out the result.
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }
    /**
     * The factorialIter method will calculate the factorial in an iterative
     * way via a for loop.
     * @param n The method will take n as the input and use it as the number of multiplications needed.
     * @return the factorial of n.
     */
    public static long factorialIter(long n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = i * factorial;
        }
        return factorial;
    }
    /**
     * The factorialRecursive method will calculate the factorial using recursion.
     * @param factorial The method will take the factorial as input.
     * @return the multiplication of the factorial number with the same factorial decreased
     * by 1 until the number will equal 1. The result is the factorial.
     */
    public static long factorialRecursive(long factorial) {
        if (factorial == 1) {
            return factorial;
        }
        return factorial * factorialRecursive(factorial - 1);
    }
}
