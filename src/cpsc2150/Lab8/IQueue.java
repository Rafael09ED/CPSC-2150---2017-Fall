package cpsc2150.Lab8;

/**
 *
 * A queue containing generic T.
 * A queue is a data structure where the first item added to the
 * structure is the first item removed from the structure
 * This queue is bounded by MAX_DEPTH
 *
 * @author Rafael Dejesus
 * <p>
 * Initialization ensures the queue is empty
 * Defines: size:Z
 * Constraints: 0 <= size <= MAX_DEPTH
 */

public interface IQueue<T> {
    int MAX_DEPTH = 100;

    /**
     * @param x the T to add to the queue
     * @requires this.size < MAX_DEPTH
     * @ensures this.size = #this.size + 1 and x is at the end of
     * the queue
     */
    public void add(T x);

    /**
     * @return the T at the front of the queue
     * @requires this.size >= 1
     * @ensures this.size = #this.size - 1 and pop = T from
     * the front of the queue
     */
    public T pop();

    /**
     * @return the number of items in the queue
     * @ensures size = the number of items in the queue
     */
    public int size();
}
