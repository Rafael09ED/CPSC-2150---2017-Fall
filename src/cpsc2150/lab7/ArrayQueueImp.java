package cpsc2150.lab7;

/**
 * QueueApp.java
 *
 * @author Rafael
 * @version 1.0 10/24/2017
 * <p>
 * Correspondence: this = myQ[0...depth-1]
 * Correspondence: size = depth
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ArrayQueueImp implements IntegerQueueI {
    private Integer[] myQ;
    private int depth;

    private int start = 0;

    /**
     * @ensures [ArrayQueueImp is created and stable to use]
     */
    public ArrayQueueImp() {
        this.myQ = new Integer[MAX_DEPTH];
        this.depth = 0;
    }

    @Override
    public void add(Integer x) {
        myQ[(start + depth++) % MAX_DEPTH] = x;
    }

    @Override
    public Integer pop() {
        Integer frontVal = myQ[start++];
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
            builder.append(myQ[start + i]).append(", "); // TODO: check if correct
        if (size() - 1 >= 0)
            builder.append(myQ[start + size() - 1]);
        return builder.append("]").toString();
    }
}