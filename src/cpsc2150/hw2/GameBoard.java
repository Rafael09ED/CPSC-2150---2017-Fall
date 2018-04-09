package cpsc2150.hw2;

import java.util.Arrays;

/**
 * GameBoard.java
 *
 * @author Rafael
 * @version 1.0 9/10/2017
 */
public class GameBoard {
    public final static int
            BOARD_SIZE = 8,
            WIN_LENGTH = 5;

    /**
     * @invariant [positions on board cannot be null]
     */
    private final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    /**
     * Creates empty game board
     */
    public GameBoard() {
        for (char[] aBoard : board) Arrays.fill(aBoard, ' ');
    }

    /**
     * Checks if pos is a valid place for a player to mark
     *
     * @param pos the position to check
     * @return true iff pos is a valid place
     * @requires pos != null
     * @ensures [true is returned iff pos is inbounds & corresponding position on board is empty]
     */
    public boolean checkSpace(BoardPosition pos) {
        if (isPositionOutOfBounds(pos.getRow(), pos.getColumn()))
            return false;
        if (board[pos.getRow()][pos.getColumn()] != ' ')
            return false;
        return true;
    }

    /**
     * Checks if position is out of bounds
     *
     * @param row    the row
     * @param column the column
     * @return true iff position is out of bounds
     * @ensures 0 <= row < BOARD_SIZE and
     * 0 <= column < BOARD_SIZE
     */
    private boolean isPositionOutOfBounds(int row, int column) {
        if (row < 0 || column < 0)
            return true;
        if (row >= BOARD_SIZE || column >= BOARD_SIZE)
            return true;
        return false;
    }

    /**
     * Places player marker on board
     *
     * @param marker the board position to add
     * @requires [marker to be a valid position on the board] and
     * marker.getPlayer() != null
     * @ensures [the character in marker is added in the corresponding place on the board]
     */
    public void placeMarker(BoardPosition marker) {
        board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    /**
     * Checks for win
     *
     * @param lastPos the last position placed on the board
     * @return true if that move triggers a win condition
     * @requires [lastPos to be present on the board] and
     * [lastPos to be the last move played]
     * @ensures [returns true iff the lastPos triggered a win]
     */
    public boolean checkForWinner(BoardPosition lastPos) {
        return checkHorizontalWin(lastPos)
                || checkVerticalWin(lastPos)
                || checkDiagonalWin(lastPos);
    }

    /**
     * Checks for win in diagonal directions
     *
     * @param lastPos the last position placed on the board
     * @return true if that move triggers a win condition diagonally
     * @requires [lastPos to be present on the board] and
     * [lastPos to be the last move played]
     * @ensures [returns true iff the lastPos triggered a win diagonally]
     */
    private boolean checkDiagonalWin(BoardPosition lastPos) {
        return checkDirectionWin(lastPos, 1, 1)
                || checkDirectionWin(lastPos, 1, -1);
    }

    /**
     * Checks for win in horizontal direction
     *
     * @param lastPos the last position placed on the board
     * @return true if that move triggers a win condition horizontally
     * @requires [lastPos to be present on the board] and
     * [lastPos to be the last move played]
     * @ensures [returns true iff the lastPos triggered a win horizontally]
     */
    private boolean checkHorizontalWin(BoardPosition lastPos) {
        return checkDirectionWin(lastPos, 1, 0);
    }

    /**
     * Checks for win in vertical direction
     *
     * @param lastPos the last position placed on the board
     * @return true if that move triggers a win condition vertically
     * @requires [lastPos to be present on the board] and
     * [lastPos to be the last move played]
     * @ensures [returns true iff the lastPos triggered a win vertically]
     */
    private boolean checkVerticalWin(BoardPosition lastPos) {
        return checkDirectionWin(lastPos, 0, 1);
    }

    /**
     * Checks a direction for a win
     *
     * @param startingPos     the starting position with player character to search for
     * @param rowIncrement    horizontal step distance
     * @param columnIncrement vertical step distance
     * @return true if that direction results in a win
     * @requires [startingPosition contains the character on the board at the given position]
     * @ensures [returns true iff number of matching characters found in search path > WIN_LENGTH]
     */
    private boolean checkDirectionWin(BoardPosition startingPos, int rowIncrement, int columnIncrement) {
        int count = 1; // because startingPos is the first one counted
        count += checkValueByIncrement(startingPos, rowIncrement, columnIncrement);
        count += checkValueByIncrement(startingPos, -rowIncrement, -columnIncrement);
        return count >= WIN_LENGTH;
    }

    /**
     * Counts consecutive matching characters up to win condition in direction
     *
     * @param startingPos     the board position position and character to start at and match
     * @param rowIncrement    how far to increment horizontally each tic
     * @param columnIncrement how far to increment vertically each tic
     * @return the number of consecutive matches up to the win condition
     * @requires startingPos != null
     */
    private int checkValueByIncrement(BoardPosition startingPos, int rowIncrement, int columnIncrement) {
        int count = 0;
        int row = startingPos.getRow(), column = startingPos.getColumn();
        do {
            row += rowIncrement;
            column += columnIncrement;
        } while (!isPositionOutOfBounds(row, column)
                && isPresentOnBoard(row, column, startingPos.getPlayer())
                && ++count < WIN_LENGTH);
        return count;
    }

    /**
     * Checks if character is present on board at passed position
     *
     * @param row       the row
     * @param column    the column
     * @param character the char to check for
     * @return true iff char is present at given position
     * @requires [row must be inbounds] and
     * [column must be inbounds]
     */
    private boolean isPresentOnBoard(int row, int column, char character) {
        return board[row][column] == character;
    }

    /**
     * Returns the friendly representation of the board terminated with new line
     *
     * @return the string representation the board
     */
    @Override
    public String toString() {
        StringBuilder boardPrintout = new StringBuilder(getPrintoutHeader());
        for (int i = 0; i < BOARD_SIZE; i++)
            boardPrintout.append(getPrintoutRow(i));
        return boardPrintout.toString();
    }

    /**
     * Returns the header of the board terminated with a new line
     *
     * @return the header of the board
     */
    private String getPrintoutHeader() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (int i = 0; i < BOARD_SIZE; i++)
            stringBuilder.append(" ").append(i);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /**
     * Returns the string representation of the passed row of the board terminated with a new line
     *
     * @param rowNumber the row
     * @return the friendly string representation of the row
     * @requires 0 <= rowNumber < BOARD_SIZE
     */
    private String getPrintoutRow(int rowNumber) {
        StringBuilder stringBuilder = new StringBuilder().append(rowNumber).append('|');
        for (int i = 0; i < BOARD_SIZE; i++)
            stringBuilder.append(board[rowNumber][i]).append('|');
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}