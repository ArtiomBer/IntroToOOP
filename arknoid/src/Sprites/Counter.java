// Artiom Berengard

package Sprites;

/**
 * The counter class is in charge of doing regular counting tasks.
 * It has a value that can be increased, decreased and being returned.
 */
public class Counter {
    private int value;
    /**
     * The setter method sets the value.
     * @param value is the value we want to set.
     */
    public void setValue(int value) {
        this.value = value;
    }
    /**
     * The constractor method sets the value to zero.
     */
    public Counter() {
        this.value = 0;
    }
    /**
     * The constractor method sets the counter with the given value.
     * @param value is the given value.
     */
    public Counter(int value) {
        this.value = value;
    }
    /**
     * This method is in charge of increasing the value of the counter.
     * @param number is the given value we want to add.
     */
    void increase(int number) {
        value = getValue() + number;
    }
    /**
     * This method is in charge of decreasing the value of the counter.
     * @param number is the given value we want to subtract.
     */
    void decrease(int number) {
        value = getValue() - number;
    }
    /**
     * This method is in charge of returning the value of the counter.
     * @return is the value of the counter.
     */
    int getValue() {
        return value;
    }
}
