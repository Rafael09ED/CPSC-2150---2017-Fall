package cpsc2150.hw2;

/**
 * BoardPosition.java
 *
 * @author Rafael
 * @version 1.0 9/10/2017 6:40pm
 */

public class BoardPosition {
    private final int row, column;
    private final char character;

    public BoardPosition(int row, int column, char character) {
        this.row = row;
        this.column = column;
        this.character = character;
    }

    /**
     * Gets the row saved
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column saved
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the character saved
     *
     * @return the character
     */
    public char getPlayer() {
        return character;
    }

}
