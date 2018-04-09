package cpsc2150.lab6;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * CPSC 2150 Lab 6
 * Author: Rafael Dejesus
 */

public class myUtil {
    /**
     * Gets the min integer from a list
     *
     * @param list the list to get the min from
     * @return the min value
     * @ensures the value returned is the lowest in the list
     * @requires list != null and
     * list.size() > 0
     */
    public static int min(List<Integer> list) {
        int min = list.get(0);
        for (Integer integer : list)
            if (integer < min)
                min = integer;
        return min;
    }

    /**
     * Gets the max integer from a list
     *
     * @param list the list to get the max from
     * @return the max value
     * @ensures the value returned is the highest
     * @requires list != null and
     * list.size() > 0
     */
    public static int max(List<Integer> list) {
        int max = list.get(0);
        for (Integer integer : list)
            if (integer > max)
                max = integer;
        return max;
    }

    /**
     * Counts the odd values in a list
     *
     * @param list the list to count odd values from
     * @return the number of odd values
     * @ensures the number returned is the number of odd values in the list
     * @requires list != null and
     * list.size() > 0
     */
    public static int odd(List<Integer> list) {
        int oddCount = 0;
        for (Integer integer : list)
            if (integer % 2 == 1)
                oddCount++;
        return oddCount;
    }

    /**
     * Counts the even values in a list
     *
     * @param list the list to count even values from
     * @return the number of even values
     * @ensures the number returned is the number of even values in the list
     * @requires list != null and
     * list.size() > 0
     */
    public static int even(List<Integer> list) {
        int evenCount = 0;
        for (Integer integer : list)
            if (integer % 2 == 0)
                evenCount++;
        return evenCount;
    }

    /**
     * Finds the average of a list
     *
     * @param list the list to calculate the average from
     * @return the average value of the list
     * @ensures the value returned is the average of all the integers in the list
     * @requires list != null and
     * list.size() > 0 and
     * [The sum of all the integers in the list is less than the max value of double]
     */
    public static double average(List<Integer> list) {
        double sum = 0;
        for (Integer integer : list)
            sum += integer;
        return sum / list.size();
    }
}
