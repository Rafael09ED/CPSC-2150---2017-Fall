package cpsc2150.hw4;

/**
 * BoardPosition.java
 *
 * @author Rafael
 * @version 1.0 9/10/2017 6:40pm
 */

public class BoardPosition {
    private final int row, column;
    private final char character;

    public BoardPosition(int column, int row, char character) {
        this.column = column;
        this.row = row;
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoardPosition && equals((BoardPosition) obj);
    }

    /**
     * Checks if two BoardPositions are equal
     *
     * @param other the other BoardPosition
     * @return true iff their fields are equal
     */
    public boolean equals(BoardPosition other) {
        return row == other.row && column == other.column && character == other.character;
    }

    /**
     * Creates a friendly string representing the BoardPosition
     *
     * @return a string representing the BoardPosition
     */
    @Override
    public String toString() {
        return "Pos: [" + column + ", " + row + ", " + character + "]";
    }
}
