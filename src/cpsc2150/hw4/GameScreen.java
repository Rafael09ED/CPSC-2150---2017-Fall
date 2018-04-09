package cpsc2150.hw4;

import java.util.Scanner;

/**
 * GameScreen.java
 *
 * @author Rafael
 * @version 1.0 9/10/2017
 */
public class GameScreen {
    /**
     * @invariant [GameBoardFast game must accurately represent the game state with moves marked by the player's character] and
     * [characterTurn is the char representing the current turn's player] and
     * [otherCharacter is the char representing the waiting player] and
     * [isGameInPlay = true while you can still attempt to make moves] and
     * [isDraw = true iff isGameInPlay = false & the game ended in a draw] and
     * [moves must equal the total number of valid moves made]
     */
    private final IGameBoard game;
    private final int maxNumMoves;

    private char
            characterTurn = 'X',
            otherCharacter = 'O';
    private boolean
            isGameInPlay = true,
            isDraw = false;
    private int moves = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!\n" +
                "To win you must place your marker down in a row either vertically, horizontally or diagonally.\n");
        GameScreen currentGame;
        Scanner scanner = new Scanner(System.in);
        boolean wantsToPlay = true;
        do {
            currentGame = makeGameScreen(scanner);
            System.out.println("Enter the row then the column of where you would like to place your mark.");
            while (currentGame.isInPlay()) {
                System.out.println(currentGame.getGameBoard());
                System.out.println(currentGame.getTurn() + "'s Turn. Where do you want to go? ");
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
     * Asks the user for input and creates a gamescreen
     *
     * @param scanner the scanner connected to the terminal
     * @return a GameScreen based on user input
     * @requires scanner != null
     * @ensures [returns a valid GameBoard]
     */
    private static GameScreen makeGameScreen(Scanner scanner) {
        int cols, rows, winL;
        do {
            System.out.println("Enter the number of columns: ");
            cols = scanner.nextInt();
            if (cols <= 0 || cols > IGameBoard.MAX_SIZE)
                System.out.println("Invalid parameter");
            else break;
        } while (true);
        do {
            System.out.println("Enter the number of rows: ");
            rows = scanner.nextInt();
            if (rows <= 0 || rows > IGameBoard.MAX_SIZE)
                System.out.println("Invalid parameter");
            else break;
        } while (true);
        do {
            System.out.println("Enter the win length: ");
            winL = scanner.nextInt();
            if (winL <= 0 || winL > rows || winL > cols)
                System.out.println("Invalid parameter");
            else break;
        } while (true);
        scanner.nextLine();
        do {
            System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
            switch (scanner.nextLine().trim().toLowerCase()) {
                case "f":
                    return new GameScreen(new GameBoardFast(cols, rows, winL), cols, rows);
                case "m":
                    return new GameScreen(new GameBoardMem(cols, rows, winL), cols, rows);
                default:
                    System.out.println("Invalid parameter");
            }
        } while (true);
    }

    /**
     * Creates a game screen that keeps track of the logic of a running game
     *
     * @param gameBoard the game board to use
     * @param colLength the col length
     * @param rowLength the row length
     */
    public GameScreen(IGameBoard gameBoard, int colLength, int rowLength) {
        this.game = gameBoard;
        this.maxNumMoves = colLength * rowLength;
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
        BoardPosition move = new BoardPosition(column, row, characterTurn);
        if (!game.checkSpace(move)) {
            return false;
        }
        game.placeMarker(move);
        moves++;
        if (game.checkForWinner(move))
            isGameInPlay = false;
        else {
            if (moves == maxNumMoves) {
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
