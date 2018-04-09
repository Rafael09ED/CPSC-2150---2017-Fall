package cpsc2150.Lab8;

import java.util.Arrays;

/**
 * QueueApp.java
 *
 * @author Rafael Dejesus
 * @version 1.0 10/31/2017
 */

public class QueueApp {
    public static void main(String[] args) {
        QueueImp<Integer> intQueue = new QueueImp<>();
        for (Integer integer : Arrays.asList(42, 17, 37, 36, 12))
            intQueue.add(integer); // this would look better in java 8

        System.out.println(intQueue.peek());
        System.out.println(intQueue.peek());

        while (intQueue.size() > 0)
            System.out.println(intQueue.pop());

        QueueImp<String> stringQueue = new QueueImp<>();
        for (String s : Arrays.asList("Look", "a", "Queue", "of", "Strings"))
            stringQueue.add(s);

        System.out.println(stringQueue.peek());
        System.out.println(stringQueue.peek());

        while (stringQueue.size() > 0)
            System.out.println(stringQueue.pop());
    }
}
