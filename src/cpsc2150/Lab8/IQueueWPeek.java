package cpsc2150.Lab8;

/**
 * IQueueWPeek.java
 *
 * @author Rafael Dejesus
 * @version 1.0 10/31/2017
 */

public interface IQueueWPeek<T> extends IQueue<T> {
    /**
     * @return the first item in the queue
     * @requires this.size > 0
     * @ensures this = #this and peek = first item in the queue
     */
    public T peek();
}
