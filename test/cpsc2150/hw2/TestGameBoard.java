package cpsc2150.hw2;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestGameBoard {
    private GameBoard gameBoard;

    @Before
    public void setup() {
        gameBoard = new GameBoard();
    }

    @Test
    public void testEmptyBoard() {
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | | |\n",
                gameBoard.toString());
    }

    @Test
    public void test_placeMarker_Pos_0_0() {
        gameBoard.placeMarker(new BoardPosition(0, 0, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0|X| | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | | |\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_placeMarker_Pos_7_7() {
        gameBoard.placeMarker(new BoardPosition(7, 7, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | |X|\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_placeMarker_Pos_0_7() {
        gameBoard.placeMarker(new BoardPosition(0, 7, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | |X|\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | | |\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_placeMarker_Pos_7_0() {
        gameBoard.placeMarker(new BoardPosition(7, 0, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7|X| | | | | | | |\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_placeMarker_Pos_2_4() {
        gameBoard.placeMarker(new BoardPosition(2, 4, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | |X| | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | | |\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_placeMarker_Pos_5_6() {
        gameBoard.placeMarker(new BoardPosition(5, 6, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | | | | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | |X| |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | | |\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_placeMarker_Pos_3_3() {
        gameBoard.placeMarker(new BoardPosition(3, 3, 'X'));
        assertEquals(
                "  0 1 2 3 4 5 6 7\n" +
                        "0| | | | | | | | |\n" +
                        "1| | | | | | | | |\n" +
                        "2| | | | | | | | |\n" +
                        "3| | | |X| | | | |\n" +
                        "4| | | | | | | | |\n" +
                        "5| | | | | | | | |\n" +
                        "6| | | | | | | | |\n" +
                        "7| | | | | | | | |\n",
                gameBoard.toString()
        );
    }

    @Test
    public void test_checkSpace_Pos_20_0() {
        assertFalse(gameBoard.checkSpace(new BoardPosition(20, 0, 'X')));
    }

    @Test
    public void test_checkSpace_Pos_8_7() {
        assertFalse(gameBoard.checkSpace(new BoardPosition(8, 7, 'X')));
    }

    @Test
    public void test_checkSpace_Pos_0_0() {
        assertTrue(gameBoard.checkSpace(new BoardPosition(0, 0, 'X')));
    }

    @Test
    public void test_checkSpaceFilled_Pos_0_0() {
        gameBoard.placeMarker(new BoardPosition(0, 0, 'X'));
        assertFalse(gameBoard.checkSpace(new BoardPosition(0, 0, 'X')));
    }

    @Test
    public void test_checkSpace_Pos_7_7() {
        assertTrue(gameBoard.checkSpace(new BoardPosition(7, 7, 'X')));
    }

    @Test
    public void test_checkWin_SingleMoveCenter() {
        BoardPosition pos = new BoardPosition(5, 5, 'X');
        gameBoard.placeMarker(pos);
        assertFalse(gameBoard.checkForWinner(pos));
    }

    @Test
    public void test_checkWin_DiagonalCorner_TL() {
        for (int i = 0; i < 5; i++) {
            gameBoard.placeMarker(new BoardPosition(i, i, 'X'));
        }
        assertTrue(gameBoard.checkForWinner(new BoardPosition(4, 4, 'X')));
    }

    @Test
    public void test_checkWin_DiagonalCorner_TR() {
        for (int i = 0; i < 5; i++) {
            gameBoard.placeMarker(new BoardPosition(4 - i, i + 3, 'X'));
        }
        assertTrue(gameBoard.checkForWinner(new BoardPosition(0, 7, 'X')));
    }

    @Test
    public void test_checkWin_DiagonalCenterBlocked() {
        for (int i = 0; i < 8; i++) {
            gameBoard.placeMarker(new BoardPosition(i, i, (i == 4) ? 'X' : 'Y'));
        }
        assertFalse(gameBoard.checkForWinner(new BoardPosition(7, 7, 'X')));
    }

    @Test
    public void test_checkWin_DiagonalCenterBlocked_2() {
        for (int i = 0; i < 8; i++) {
            if (i != 4)
                gameBoard.placeMarker(new BoardPosition(i, i, 'X'));
        }
        gameBoard.placeMarker(new BoardPosition(4, 4, 'Y'));
        assertFalse(gameBoard.checkForWinner(new BoardPosition(4, 4, 'Y')));
    }

    @Test
    public void test_checkWin_Horizontal_3_i() {
        for (int i = 0; i < 5; i++) {
            gameBoard.placeMarker(new BoardPosition(3, i, 'X'));
        }
        assertTrue(gameBoard.checkForWinner(new BoardPosition(3, 4, 'X')));
    }

    @Test
    public void test_checkWin_Horizontal_i_3() {
        for (int i = 0; i < 5; i++) {
            gameBoard.placeMarker(new BoardPosition(i, 3, 'X'));
        }
        assertTrue(gameBoard.checkForWinner(new BoardPosition(4, 3, 'X')));
    }
}