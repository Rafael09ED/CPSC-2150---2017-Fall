package cpsc2150.hw4;

/**
 * IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers (X, O). No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x
 * NUM_COLS in size
 * <p>
 * Initialization ensures: the Board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLS: Z
 * Constraints: 0< NUM_ROWS <= MAX_SIZE
 * 0< NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {
    int MAX_SIZE = 100;

    /**
     * Checks if pos is a valid place for a player to mark
     *
     * @param pos the position to check
     * @return true iff pos is a valid place
     * @requires pos != null
     * @ensures [true is returned iff pos is inbounds & corresponding position on board is empty]
     */
    boolean checkSpace(BoardPosition pos);

    /**
     * Places player marker on board
     *
     * @param lastPos the board position to add
     * @requires [marker to be a valid position on the board] and
     * marker.getPlayer() != null
     * @ensures [the character in marker is added in the corresponding place on the board]
     */
    void placeMarker(BoardPosition lastPos);

    /**
     * Checks for win
     *
     * @param lastPos the last position placed on the board
     * @return true if that move triggers a win condition
     * @requires [lastPos to be present on the board] and
     * [lastPos to be the last move played]
     * @ensures [returns true iff the lastPos triggered a win]
     */
    boolean checkForWinner(BoardPosition lastPos);
}