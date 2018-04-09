package cpsc2150.lab7;

/**
 * QueueApp.java
 *
 * @author Rafael
 * @version 1.0 10/24/2017
 */

public class QueueApp {
    public static void main(String[] args) {
        IntegerQueueI q = new ArrayQueueImp();
        Integer x = 42;
        q.add(x);
        x = 17;
        q.add(x);
        x = 37;
        q.add(x);
        x = 36;
        q.add(x);
        x = 12;
        q.add(x);

        while (q.size() > 0)
            System.out.println(q.pop());
    }
}
