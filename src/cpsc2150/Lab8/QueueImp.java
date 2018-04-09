package cpsc2150.Lab8;

/**
 * QueueImp.java
 *
 * @author Rafael
 * @version 1.0 10/31/2017
 * <p>
 * Correspondence: this = queue[0...depth-1]
 * Correspondence: size = depth
 * @invariant: 0 <= depth <= MAX_DEPTH
 */

public class QueueImp<T> extends AbstractQueueWPeek<T> {
    private T[] queue;
    private int depth;

    private int start = 0;

    /**
     * @ensures [ArrayQueueImp is created and stable to use]
     */
    @SuppressWarnings("unchecked")
    public QueueImp() {
        this.queue = (T[]) new Object[MAX_DEPTH];
        this.depth = 0;
    }

    @Override
    public void add(T x) {
        queue[(start + depth++) % MAX_DEPTH] = x;
    }

    @Override
    public T pop() {
        T frontVal = queue[start++];
        start %= MAX_DEPTH;
        depth--;
        return frontVal;
    }

    @Override
    public int size() {
        return depth;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size() - 1; i++)
            builder.append(toStringVal(queue[start + i])).append(", ");
        if (size() - 1 >= 0)
            builder.append(toStringVal(queue[start + size() - 1]));
        return builder.append("]").toString();
    }

    private String toStringVal(T val) {
        return (val == null) ? "null" : val.toString();
    }
}
