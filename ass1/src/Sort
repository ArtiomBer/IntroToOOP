// Artiom Berengard

/**
 * The Sort class provides a sorted list and prints it in ascending or descending order.
 * To print the list in ascending order, please type "asc" as your first parameter.
 *  * To print the list in descending order, please type "desc" as your first parameter.
 */
public class Sort {
    /**
     * The Sort method will receive a list of strings with non-integer numbers,
     * convert the list to numbers and sort the list in ascending or descending order.
     * @param args The method will take it's input from the commandline.
     */
    public static void main(String[] args) {
        int[] numbers;
        numbers =  stringsToInts(args);
        int[] sortedNumbers;
        sortedNumbers = bubbleSort(numbers);
        if (args[0].equals(("asc"))) {
            printSortedByAscending(sortedNumbers);
        } else if (args[0].equals(("desc"))) {
            printSortedByDescending(sortedNumbers);
        }
    }
    /**
     * The stringsToInts method will convert a list of strings to a list of integers.
     * @param numbers The method will receive a list of strings from the commandline.
     * @return The method will return the integers list.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] numberList;
        numberList = new int[numbers.length - 1];
        for (int i = 0; i < numbers.length - 1; i++) {
            numberList[i] = Integer.parseInt(numbers[i + 1]);
        }
        return numberList;
    }
    /**
     * The sortByAscending method will sort a list of integers in ascending order.
     * @param numbers The method will receive a list of numbers.
     * @return The method will return the integers list sorted in ascending order.
     */
    public static int[] bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                }
            }
        }
        return numbers;
    }
    /**
     * The swap method will swap integers in the given list.
     * @param numbers The method will receive a list of numbers.
     * @param i The method will receive the first index to swap.
     * @param j The method will receive the second index to swap.
     */
    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
    /**
     * The printSortedByAscending method will print a list of numbers in ascending order.
     * @param sortedNumbers The method will receive a list of numbers.
     */
    public static void printSortedByAscending(int[] sortedNumbers) {
        for (int i = 0; i < sortedNumbers.length; i++) {
            if (i != 0) {
                System.out.print(' ');
            }
            System.out.print(sortedNumbers[i]);
        }
        System.out.print('\n');
    }
    /**
     * The printSortedByDescending method will print a list of numbers in descending order.
     * @param sortedNumbers The method will receive a list of numbers.
     */
    public static void printSortedByDescending(int[] sortedNumbers) {
        for (int i = sortedNumbers.length - 1; i >= 0; i--) {
            if (i != sortedNumbers.length - 1) {
                System.out.print(' ');
            }
            System.out.print(sortedNumbers[i]);
        }
        System.out.print('\n');
    }
}
