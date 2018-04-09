package cpsc2150.Lab1;

import java.util.Arrays;
import java.util.Scanner;

/*
 * CPSC 2150 Lab1
 * Author: Rafael Dejesus
 */

public class Main {

    public Main() {
        promptUserForNumbers();
        int[] values = collectNumbersFromUser();
        EvaluationResult result = evaluateInput(values);
        printResults(result);
    }

    private void promptUserForNumbers() {
        System.out.println("Enter 10 (ten) integers separated by whitespace, then hit enter");
    }

    private int[] collectNumbersFromUser() {
        int[] valuesFromUser = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++)
            valuesFromUser[i] = sc.nextInt();
        sc.close();
        return valuesFromUser;
    }

    private EvaluationResult evaluateInput(int[] numbersToEvaluate) {
        int min = findMin(numbersToEvaluate);
        int numberOfEvenNumbers = findNumberOfEvenNumbers(numbersToEvaluate);
        double average = findAverage(numbersToEvaluate);
        return new EvaluationResult(min, numberOfEvenNumbers, average);
    }

    private int findMin(int[] numbersToEvaluate) {
        Arrays.stream(numbersToEvaluate).min().orElse(0);
        int min = numbersToEvaluate[0];
        for (int numToTest : numbersToEvaluate)
            if (min > numToTest)
                min = numToTest;
        return min;
    }

    private int findNumberOfEvenNumbers(int[] numbersToEvaluate) {
       // return (int) Arrays.stream(numbersToEvaluate).filter(value -> value % 2 == 0).count();
        int evenNumbers = 0;
        for (int numToTest : numbersToEvaluate)
            if (numToTest % 2 == 0)
                evenNumbers++;
        return evenNumbers;
    }

    private double findAverage(int[] numbersToEvaluate) {
        int sum = 0;
        for (int number : numbersToEvaluate)
            sum += number;
        return sum * 1d / numbersToEvaluate.length;
    }

    private void printResults(EvaluationResult result) {
        System.out.println("Min Number: " + result.min);
        System.out.println("Number of Even Numbers: " + result.numberOfEvenNumbers);
        System.out.println("Average: " + result.average);
    }

    public static void main(String[] args) {
        new Main();
    }

    private static class EvaluationResult {
        public final int min, numberOfEvenNumbers;
        public final double average;

        public EvaluationResult(int min, int numberOfEvenNumbers, double average) {
            this.min = min;
            this.numberOfEvenNumbers = numberOfEvenNumbers;
            this.average = average;
        }
    }
}
