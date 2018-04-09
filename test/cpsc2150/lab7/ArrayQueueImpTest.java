package cpsc2150.lab7;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayQueueImpTest {

    @Test
    public void testQueue() {
        ArrayQueueImp queue = new ArrayQueueImp();
        for (int i = 0; i < 200; i++) {
            System.out.println(queue.toString());
            if (queue.size() >= 100) {
                System.out.print(queue.pop() +  " ");
            }
            if (i > 100 && i % 2 == 0)
                continue;
            queue.add(i);
        }
        while (queue.size() > 0)
            System.out.println(queue.pop());
    }

}