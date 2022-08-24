// 321114704 Artiom Berengard

/**
 * The DescribeNumbers class provides the minimum, maximum and average of an integer list.
 */
public class DescribeNumbers {
    /**
     * The DescribeNumbers method will receive a list of strings with non-integer
     * numbers, convert the list to numbers and search for the minimum, maximum
     * and average values, and print them in order.
     * @param args The method will take it's input from the commandline.
     */
    public static void main(String[] args) {
        int[] numbers;
        numbers =  stringsToInts(args);
        System.out.println("min: " + min(numbers));
        System.out.println("max: " + max(numbers));
        System.out.println("avg: " + avg(numbers));
    }
    /**
     * The stringsToInts method will convert a list of strings to a list of integers.
     * @param numbers The method will receive a list of strings from the commandline.
     * @return The method will return the integers list.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] numberList;
        numberList = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numberList[i] = Integer.parseInt(numbers[i]);
        }
        return numberList;
    }
    /**
     * The min method will search for the minimal number in the list.
     * @param numbers The method will receive a list of integers.
     * @return The method will return the minimal number.
     */
    public static int min(int[] numbers) {
        //Making sure that the list is not empty.
        if (numbers.length == 0) {
            return 0;
        }
        int minimum = numbers[0];
        //searching for the minimum through the list.
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minimum) {
                minimum = numbers[i];
            }
        }
        return minimum;
    }
    /**
     * The max method will search for the maximal number in the list.
     * @param numbers The method will receive a list of integers.
     * @return The method will return the maximal number.
     */
    public static int max(int[] numbers) {
        //Making sure that the list is not empty.
        if (numbers.length == 0) {
            return 0;
        }
        int maximum = numbers[0];
        //searching for the maximum through the list.
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maximum) {
                maximum = numbers[i];
            }
        }
        return maximum;
    }
    /**
     * The avg method will calculate the average of the list.
     * @param numbers The method will receive a list of integers.
     * @return The method will return the average.
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        // If the list is empty we return 0, to avoid dividing by zero;
        if (numbers.length != 0) {
            return (sum / numbers.length);
        } else {
            return 0;
        }
    }
}