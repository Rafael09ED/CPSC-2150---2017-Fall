package cpsc2150.hw4;

import cpsc2150.hw4.GameBoardAlt.GridDirection;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameBoardAltTest {
    @Test
    public void calculateGridDirectionMagnitudeTest() {
        GridDirection dir = GridDirection.VERTICAL;
        assertTrue(GridDirection.compare(nBPos(1, 1), nBPos(2, 2)) < 0);
        assertTrue(GridDirection.compare(nBPos(1, 1), nBPos(4, 4)) < 0);
        assertTrue(GridDirection.compare(nBPos(1, 1), nBPos(1, 1)) == 0);
        assertTrue(GridDirection.compare(nBPos(1, 1), nBPos(0, 0)) > 0);
        //assertTrue(GridDirection.compare(nBPos(0, 1), nBPos(1, 0)) > 0);
        assertTrue(GridDirection.compare(nBPos(0, 0), nBPos(-1, -1)) > 0);
        assertTrue(GridDirection.compare(nBPos(0, 0), nBPos(0, -1)) > 0);
        assertTrue(GridDirection.compare(nBPos(0, 0), nBPos(-1, 0)) > 0);
    }

    @Test
    public void testGetCorrectedOffset() {
        BoardPosition pos = GameBoardAlt.GridDirection.getOffset(nBPos(0, 0), nBPos(1, 1));
        assertEquals(-1, pos.getColumn());
        assertEquals(-1, pos.getRow());

        pos = GameBoardAlt.GridDirection.getOffset(nBPos(2, 2), nBPos(4, 4));
        assertEquals(-2, pos.getColumn());
        assertEquals(-2, pos.getRow());

        pos = GameBoardAlt.GridDirection.getOffset(nBPos(0, 2), nBPos(0, 4));
        assertEquals(0, pos.getColumn());
        assertEquals(-2, pos.getRow());
    }

    @Test
    public void testDiagDirectionWinChecking() {
        GameBoardAlt gameBoard = new GameBoardAlt(8, 8, 5);
        for (int i = 0; i < 6; i++) {
            gameBoard.placeMarker(new BoardPosition(i, i, 'X'));
        }
        assertTrue(gameBoard.checkForWinner(new BoardPosition(4, 4, 'X')));
    }

    @Test
    public void testHorDirectionWinChecking() {
        GameBoardAlt gameBoard = new GameBoardAlt(8, 8, 5);
        for (int i = 0; i < 5; i++) {
            gameBoard.placeMarker(new BoardPosition(0, i, 'X'));
        }
        assertTrue(gameBoard.checkForWinner(new BoardPosition(0, 4, 'X')));
    }


    public void randomDrawTest() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8 * 8; i++) {
            builder.append(randomBoardNum()).append(" ").append(randomBoardNum()).append("\n");
        }
        builder.append('n');
        System.out.println(builder.toString());
        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        System.setIn(in);
        try {
            GameScreen.main(new String[0]);
        } catch (InputMismatchException e) {

        }
    }

    private static int randomBoardNum() {
        return (int) (Math.random() * 8.0);
    }


    private static BoardPosition nBPos(int x, int y) {
        return new BoardPosition(x, y, ' ');
    }

}