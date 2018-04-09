package cpsc2150.hw2;

import java.util.Scanner;

/**
 * GameScreen.java
 *
 * @author Rafael
 * @version 1.0 9/10/2017
 */
public class GameScreen {
    /**
     * @invariant [GameBoard game must accurately represent the game state with moves marked by the player's character] and
     * [characterTurn is the char representing the current turn's player] and
     * [otherCharacter is the char representing the waiting player] and
     * [isGameInPlay = true while you can still attempt to make moves] and
     * [isDraw = true iff isGameInPlay = false & the game ended in a draw] and
     * [moves must equal the total number of valid moves made]
     */
    private final GameBoard game = new GameBoard();
    private char
            characterTurn = 'X',
            otherCharacter = 'O';
    private boolean
            isGameInPlay = true,
            isDraw = false;
    private int moves = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to 8x8 Tic-Tac-Toe!\n" +
                "To win you must get 5 in a row either vertically, horizontally or diagonally.\n" +
                "Enter the row then the column of where you would like to place your mark.");
        GameScreen currentGame;
        Scanner scanner = new Scanner(System.in);
        boolean wantsToPlay = true;
        do {
            currentGame = new GameScreen();

            while (currentGame.isInPlay()) {
                System.out.println(currentGame.getGameBoard());
                System.out.println(currentGame.getTurn() + "'s Turn. Where do you want to go?");
                if (!currentGame.move(scanner.nextInt(), scanner.nextInt()))
                    System.out.println("Invalid Position! Try again!");
                System.out.println();
                scanner.nextLine();
            }

            System.out.println(currentGame.getGameBoard());
            if (currentGame.isDraw)
                System.out.println("Draw!");
            else
                System.out.println(currentGame.getTurn() + " wins! Congratulations!");
            System.out.println("Would you like to play again? y/n");
            String response = scanner.nextLine().trim();
            if (!(response.contains("y") || response.contains("Y")))
                wantsToPlay = false;
        } while (wantsToPlay);
        scanner.close();
        System.out.println("Good Bye! Thanks for playing.");
    }

    /**
     * Processes the current player's move attempt
     *
     * @param row    the row to mark
     * @param column the column to mark
     * @return true iff the move was valid and registered in the game state
     * @ensures [game state will be updated with move iff the move can be made]
     */
    private boolean move(int row, int column) {
        BoardPosition move = new BoardPosition(row, column, characterTurn);
        if (!game.checkSpace(move)) {
            return false;
        }
        game.placeMarker(move);
        moves++;
        if (game.checkForWinner(move))
            isGameInPlay = false;
        else {
            if (moves == Math.pow(GameBoard.BOARD_SIZE, 2)) {
                isDraw = true;
                isGameInPlay = false;
            }
            changeTurns();
        }
        return true;
    }

    /**
     * Gets current player's turn
     *
     * @return the char of the current turn's player
     */
    public char getTurn() {
        return characterTurn;
    }

    /**
     * Gets if the game is in play
     *
     * @return true iff the game is in play
     */
    public boolean isInPlay() {
        return isGameInPlay;
    }

    /**
     * Gets the friendly representation of the current game's board
     *
     * @return the displayable string representing the game board
     */
    public String getGameBoard() {
        return game.toString();
    }

    /**
     * Changes the turn from the active player to the waiting player
     *
     * @ensures characterTurn = #otherCharacter and
     * otherCharacter = #characterTurn
     */
    private void changeTurns() {
        char temp = characterTurn;
        characterTurn = otherCharacter;
        otherCharacter = temp;
    }

}
