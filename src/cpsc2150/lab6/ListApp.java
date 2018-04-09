package cpsc2150.lab6;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * CPSC 2150 Lab 6
 * Author: Rafael Dejesus
 */

public class ListApp {
    public static void main(String[] args) {
        new ListApp();
    }

    public ListApp() {
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        do {
            System.out.println("Enter number (d to stop)");
            String input = sc.nextLine();
            if (input.contains("d"))
                run = false;
            else
                list.add(Integer.parseInt(input));
        } while (run);
        System.out.println(resultsMessage(list));
    }

    /**
     * @ensures [returns message appropriate for list]
     * @requires list != null
     */
    private String resultsMessage(List<Integer> list) {
        if (list.size() == 0)
            return "Empty List";
        return "Max: " + myUtil.max(list) + "\n" +
                "Min: " + myUtil.min(list) + "\n" +
                "Odd Count: " + myUtil.odd(list) + "\n" +
                "Even Count: " + myUtil.even(list) + "\n" +
                "Average: " + myUtil.average(list) + "\n";
    }
}
