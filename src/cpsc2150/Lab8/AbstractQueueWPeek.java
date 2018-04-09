package cpsc2150.Lab8;

/**
 * AbstractQueueWPeek.java
 *
 * @author Rafael Dejesus
 * @version 1.0 10/31/2017
 */

public abstract class AbstractQueueWPeek<T> implements IQueueWPeek<T> {
    @Override
    public T peek() {
        T temp = pop();
        add(temp);
        for (int i = 1; i < size(); i++) {
            add(pop());
        }
        return temp;
    }
}
