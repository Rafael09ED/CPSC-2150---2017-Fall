package cpsc2150.hw4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameBoardMem implements IGameBoard {
    private final static char
            X_CHAR = 'X',
            O_CHAR = 'O';
    /**
     * @invariant xList.get(i).getPlayer() == X_CHAR and
     * oList.get(i).getPlayer() == O_CHAR
     */
    private final int numOfColumns, numOfRows, winLength, rowHeaderWidth, columnHeaderWidth;
    private final List<BoardPosition>
            xList = new LinkedList<>(),
            oList = new LinkedList<>();

    /**
     * Creates a game board based on two lists for X and O BoardPositions
     *
     * @param numOfColumns the number of columns on the board
     * @param numOfRows    the number of rows on the board
     * @param winLength    the streak required to win
     */
    public GameBoardMem(int numOfColumns, int numOfRows, int winLength) {
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.winLength = winLength;
        this.rowHeaderWidth = String.valueOf(numOfRows - 1).length();
        this.columnHeaderWidth = String.valueOf(numOfColumns - 1).length();
    }

    @Override
    public boolean checkSpace(BoardPosition pos) {
        return isPositionInBounds(pos.getColumn(), pos.getRow())
                && !isPositionInAnyList(pos);
    }

    /**
     * Checks if a BoardPosition is in any list
     *
     * @param pos the BoardPosition to look for
     * @return true iff the pos is in any list
     * @requires pos != null
     */
    private boolean isPositionInAnyList(BoardPosition pos) {
        return xList.contains(new BoardPosition(pos.getColumn(), pos.getRow(), X_CHAR))
                || oList.contains(new BoardPosition(pos.getColumn(), pos.getRow(), O_CHAR));
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
    private boolean isPositionInBounds(int column, int row) {
        return row >= 0 && column >= 0 && row < numOfRows && column < numOfColumns;
    }

    @Override
    public void placeMarker(BoardPosition lastPos) {
        getListFromChar(lastPos.getPlayer()).add(lastPos);
        System.out.println(Arrays.toString(xList.toArray()));
    }

    /**
     * Gets the list that contains the char
     *
     * @param character the char to find the list for
     * @return the list storing the given char
     * If character is not X_CHAR or O_CHAR a new list is returned
     */
    private List<BoardPosition> getListFromChar(char character) {
        switch (character) {
            case X_CHAR:
                return xList;
            case O_CHAR:
                return oList;
        }
        return new LinkedList<>();
    }

    @Override
    public boolean checkForWinner(BoardPosition lastPos) {
        List<BoardPosition> listToCheck = getListFromChar(lastPos.getPlayer());
        for (WinDirection dir : WinDirection.values()) {
            int count = 1;
            count += countInDirectionCapped(listToCheck, lastPos, dir.xMag, dir.yMag, winLength);
            count += countInDirectionCapped(listToCheck, lastPos, -dir.xMag, -dir.yMag, winLength);
            if (count >= winLength)
                return true;
        }
        return false;
    }

    /**
     * Counts how many BoardPositions match in a direction
     *
     * @param boardPositions the list of positions to check for matches in
     * @param centerPos      the starting position
     * @param xStep          how far to step in the x direction each iteration
     * @param yStep          how far to step in the y direction each iteration
     * @param maxValue       how high to count before stopping
     * @return the count
     * @requires boardPositions != null and
     * centerPos != null
     */
    private static int countInDirectionCapped(List<BoardPosition> boardPositions, BoardPosition centerPos, int xStep, int yStep, int maxValue) {
        for (int i = 1; i <= maxValue; i++) {
            if (!boardPositions.contains(
                    new BoardPosition(
                            centerPos.getColumn() + xStep * i,
                            centerPos.getRow() + yStep * i,
                            centerPos.getPlayer())))
                return i - 1;
        }
        return maxValue;
    }

    /**
     * Returns the character at the given column and row
     *
     * @param col the column of the char
     * @param row the row of the char
     * @return the value set at that the given position or ' ' if none is set
     */
    private char getCharAt(int col, int row) {
        if (xList.contains(new BoardPosition(col, row, X_CHAR)))
            return X_CHAR;
        else if (oList.contains(new BoardPosition(col, row, O_CHAR)))
            return O_CHAR;
        return ' ';
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

    /**
     * Positive possible win directions
     */
    private enum WinDirection {
        VERTICAL(0, 1), DIAGONAL_UP(1, -1), HORIZONTAL(1, 0), DIAGONAL_DOWN(1, 1);

        public final int xMag, yMag;

        WinDirection(int xMag, int yMag) {
            this.xMag = xMag;
            this.yMag = yMag;
        }
    }
}