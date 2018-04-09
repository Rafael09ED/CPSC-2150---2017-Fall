package cpsc2150.lab7;

/**
 * A queue containing integers.
 * A queue is a data structure where the first item added to the
 * structure is the first item removed from the structure
 * This queue is bounded by MAX_DEPTH
 * <p>
 * Initialization ensures the queue is empty
 * Defines: size:Z
 * Constraints: 0 <= size <= MAX_DEPTH
 */
public interface IntegerQueueI {
    int MAX_DEPTH = 100;

    /**
     * @param x the integer to add to the queue
     * @requires this.size < MAX_DEPTH
     * @ensures this.size = #this.size + 1 and x is at the end of
     * the queue
     */
    public void add(Integer x);

    /**
     * @return the Integer at the front of the queue
     * @requires this.size >= 1
     * @ensures this.size = #this.size - 1 and pop = integer from
     * the front of the queue
     */
    public Integer pop();

    /**
     * @return the number of items in the queue
     * @ensures size = the number of items in the queue
     */
    public int size();
}
