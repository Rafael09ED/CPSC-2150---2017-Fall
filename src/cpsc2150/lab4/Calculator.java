package cpsc2150.lab4;

/**
 * Created by kplis on   9/14/2017.
 */
public class Calculator {
    /**
     * Adds two variables
     *
     * @param x first var
     * @param y second var
     * @return x + y
     * @ensures return = x + y
     */
    public double add(double x, double y) {
        return x + y;
    }

    /**
     * Multiplies two variables
     *
     * @param x first var
     * @param y second var
     * @return x * y
     * @ensures return = x * y
     */
    public double mult(double x, double y) {
        return x * y;
    }

    /**
     * Subtracts second var from first var
     *
     * @param x first variable
     * @param y second variable
     * @return x - y
     * @ensures return = x - y
     */
    public double subtract(double x, double y) {
        return x - y;
    }

    /**
     * Divides two vars
     *
     * @param x first var
     * @param y second var
     * @return x / y
     * @requires y =/= 0
     * @ensures return = x / y
     */
    public double divide(double x, double y) {
        return x / y;
    }
}
