package cpsc2150.hw4;

import java.util.Arrays;

/**
 * GameBoardFast.java
 *
 * @author Rafael
 * @version 1.0 9/10/2017
 */
public class GameBoardFast implements IGameBoard {
    /**
     * @invariant [positions on board cannot be null]
     */
    private final char[][] board;
    private final int numOfRows, numOfColumns, winLength, rowHeaderWidth, columnHeaderWidth;

    /**
     * Creates a game board based on a 2D array
     *
     * @param numOfColumns the number of columns on the board
     * @param numOfRows    the number of rows on the board
     * @param winLength    the streak required to win
     */
    public GameBoardFast(int numOfColumns, int numOfRows, int winLength) {
        this.winLength = winLength;
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.rowHeaderWidth = String.valueOf(numOfRows - 1).length();
        this.columnHeaderWidth = String.valueOf(numOfColumns - 1).length();
        board = new char[MAX_SIZE][MAX_SIZE];
        for (char[] aBoard : board) Arrays.fill(aBoard, ' ');
    }

    @Override
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
     * @ensures ! (0 <= row < BOARD_SIZE) and
     * ! (0 <= column < BOARD_SIZE)
     */
    private boolean isPositionOutOfBounds(int row, int column) {
        if (row < 0 || column < 0)
            return true;
        if (row >= numOfRows || column >= numOfColumns)
            return true;
        return false;
    }

    @Override
    public void placeMarker(BoardPosition marker) {
        board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    @Override
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
        return count >= winLength;
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
                && ++count < winLength);
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
     * Returns the character at the given column and row
     *
     * @param col the column of the char
     * @param row the row of the char
     * @return the value at the given position
     * @requires [the position must be in bounds]
     */
    private char getCharAt(int col, int row) {
        return board[row][col];
    }

    /**
     * Returns the friendly representation of the board terminated with new line
     *
     * @return the string representation the board
     */
    @Override
    public String toString() {
        StringBuilder boardPrintout = new StringBuilder(getHeaderPrintout());
        for (int i = 0; i < numOfRows; i++)
            boardPrintout.append(getRowPrintout(i));
        return boardPrintout.toString();
    }

    /**
     * Returns the header of the board terminated with a new line
     *
     * @return the header of the board
     */
    private String getHeaderPrintout() {
        StringBuilder stringBuilder = new StringBuilder(formatWidth(rowHeaderWidth, ""));
        for (int i = 0; i < numOfColumns; i++)
            stringBuilder
                    .append(" ")
                    .append(String.format("%" + columnHeaderWidth + "s", i));
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
    private String getRowPrintout(int rowNumber) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(formatWidth(rowHeaderWidth, rowNumber + ""))
                .append('|');
        for (int i = 0; i < numOfColumns; i++)
            stringBuilder
                    .append(formatWidthAlignL(columnHeaderWidth, getCharAt(i, rowNumber)))
                    .append('|');
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /**
     * Formats a string into a give size with right align
     *
     * @param size the size to format the string into
     * @param text the text to format
     * @return the formatted string
     * @requires text.size() <= size
     */
    private String formatWidth(int size, String text) {
        return String.format("%" + size + "s", text);
    }

    /**
     * Formats a string into a give size with left align
     *
     * @param size the size to format the string into
     * @param text the text to format
     * @return the formatted string
     * @requires text.size() <= size
     */
    private String formatWidthAlignL(int size, char text) {
        return String.format("%-" + size + "s", text);
    }
}