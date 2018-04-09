package cpsc2150.hw4;

import java.util.*;

public class GameBoardAlt implements IGameBoard {
    private final int rowSize, colSize, winLength;
    private final Map<Character, List<BoardPosition>> positionListCharMap = new TreeMap<>();

    public GameBoardAlt(int rowSize, int colSize, int winLength) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.winLength = winLength;
    }

    @Override
    public boolean checkSpace(BoardPosition pos) {
        return isPositionInBounds(pos.getRow(), pos.getColumn())
                && !isPositionInAnyList(positionListCharMap.entrySet(), pos);
    }

    private boolean isPositionInAnyList(Set<Map.Entry<Character, List<BoardPosition>>> entries, BoardPosition position) {
        for (Map.Entry<Character, List<BoardPosition>> entry : entries) {
            BoardPosition boardPosToCheckFor = new BoardPosition(position.getColumn(), position.getRow(), entry.getKey());
            if (entry.getValue().contains(boardPosToCheckFor))
                return true;
        }
        return false;
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
    private boolean isPositionInBounds(int row, int column) {
        return row >= 0 && column >= 0 && row < rowSize && column < colSize;
    }

    @Override
    public void placeMarker(BoardPosition lastPos) {
        positionListCharMap.computeIfAbsent(
                lastPos.getPlayer(),
                k -> new LinkedList<>()
        ).add(0, lastPos);
    }


    @Override
    public boolean checkForWinner(BoardPosition lastPos) {
        return checkForPlayerWin(lastPos, positionListCharMap.get(lastPos.getPlayer()));
    }

    private boolean checkForPlayerWin(BoardPosition lastPos, List<BoardPosition> playersBoardPositions) {
        Map<GridDirection, SortedSet<BoardPosition>> sortedOffsetDirectionSet = makeGridDirectionSortedSetMap(lastPos);
        populateDirectionSets(sortedOffsetDirectionSet, playersBoardPositions, lastPos);
        return checkDirectionSetsForWin(sortedOffsetDirectionSet);
    }

    private void populateDirectionSets(Map<GridDirection, SortedSet<BoardPosition>> directionSets, List<BoardPosition> positions, BoardPosition lastPos) {
        for (BoardPosition position : positions) {
            final BoardPosition offset = GridDirection.getOffset(lastPos, position);
            if (!isOffsetWithinWinBounds(offset)) continue;
            GridDirection direction = GridDirection.getDirectionLineFromOffset(offset);
            if (direction == null) continue;
            directionSets.get(direction).add(offset);
        }
    }

    private boolean checkDirectionSetsForWin(Map<GridDirection, SortedSet<BoardPosition>> directionSets) {
        for (Map.Entry<GridDirection, SortedSet<BoardPosition>> entry : directionSets.entrySet())
            if (checkForDirectionalWinFromOffsets(entry.getKey(), entry.getValue()))
                return true;
        return false;
    }

    private static Map<GridDirection, SortedSet<BoardPosition>> makeGridDirectionSortedSetMap(BoardPosition lastPos) {
        final Map<GridDirection, SortedSet<BoardPosition>> sortedOffsetDirectionSet = new TreeMap<>();
        for (GridDirection direction : GridDirection.values()) {
            TreeSet<BoardPosition> treeSet = new TreeSet<>(GridDirection.getDirectionComparator());
            treeSet.add(new BoardPosition(0, 0, lastPos.getPlayer()));
            sortedOffsetDirectionSet.put(direction, treeSet);
        }
        return sortedOffsetDirectionSet;
    }


    private boolean isOffsetWithinWinBounds(BoardPosition pos) {
        return Math.abs(pos.getColumn()) <= winLength && Math.abs(pos.getRow()) <= winLength;
    }

    private boolean checkForDirectionalWinFromOffsets(GridDirection dir, SortedSet<BoardPosition> orderedPosList) {
        Iterator<BoardPosition> iterator = orderedPosList.iterator();
        if (!iterator.hasNext()) return false;
        BoardPosition lastPos = iterator.next();
        /*
        * TODO: Make sure that the actual streak of matching chars is in a streak with the center pos (0,0)
        * One possible implementation is to split the Sorted Set at center pos and then count until the streak is broken
        */
        int count = 1;
        while (iterator.hasNext()) {
            BoardPosition pos = iterator.next();
            if (GridDirection.isNextInLine(dir, lastPos, pos)) {
                if (++count >= winLength)
                    return true;
            } else
                count = 1;
            lastPos = pos;
        }
        return false;
    }

    public enum GridDirection {
        VERTICAL(0, 1), DIAGONAL_UP(1, -1), HORIZONTAL(1, 0), DIAGONAL_DOWN(1, 1);

        private final int xMag, yMag;

        GridDirection(int xMag, int yMag) {
            this.xMag = xMag;
            this.yMag = yMag;
        }

        public static Comparator<BoardPosition> getDirectionComparator() {
            return GridDirection::compare;
        }

        public static int compare(BoardPosition o1, BoardPosition o2) {
            BoardPosition correctedOffset = getOffset(o1, o2);
            return calculateGridDirectionMagnitude(
                    correctedOffset.getColumn(),
                    correctedOffset.getRow()
            );
        }

        public static int calculateGridDirectionMagnitude(int xPos, int yPos) {
            return (xPos == 0) ? yPos : xPos;
        }

        public static BoardPosition getOffset(BoardPosition center, BoardPosition pos) {
            return new BoardPosition(center.getColumn() - pos.getColumn(),
                    center.getRow() - pos.getRow(),
                    center.getPlayer());
        }

        public static GridDirection getDirectionLineFromOffset(BoardPosition offset) {
            if (offset.getColumn() == 0)
                return VERTICAL;
            if (offset.getRow() == 0)
                return HORIZONTAL;
            if (offset.getRow() == offset.getColumn())
                return DIAGONAL_DOWN;
            if (offset.getRow() == -offset.getColumn())
                return DIAGONAL_UP;
            return null;
        }

        private static boolean isNextInLine(GridDirection dir, BoardPosition first, BoardPosition second) {
            return (first.getColumn() + dir.xMag) == second.getColumn()
                    && (first.getRow() + dir.yMag) == second.getRow();
        }

    }

    /**
     * Returns the friendly representation of the board terminated with new line
     *
     * @return the string representation the board
     */
    @Override
    public String toString() {
        // TODO: 11/8/2017 fix for size increase
        StringBuilder boardPrintout = new StringBuilder(getPrintoutHeader());
        for (int i = 0; i < colSize; i++)
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
        for (int i = 0; i < rowSize; i++)
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
        for (int i = 0; i < rowSize; i++) {
            // TODO: 11/8/2017 don't do a hack please
            int finalI = i;
            BoardPosition pos = positionListCharMap.values().stream()
                    .flatMap(Collection::stream)
                    .filter(position -> position.getRow() == rowNumber && position.getColumn() == finalI)
                    .findAny()
                    .orElse(new BoardPosition(0, 0, ' '));
            stringBuilder.append(pos.getPlayer()).append('|');
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
