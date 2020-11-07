package Ex_5;

import java.io.IOException;

/**
 * Represents a player in a tic-tac-toe game and provides methods for running
 * making moves.
 * 
 * @author Davis Allan & Mike Lasby
 * @since Nov. 7, 2020
 * @version 2.0
 */
public class Player {

    private String name;
    private Board board;
    private Player opponent;
    private char mark;
    private ServerClient myClient;

    /**
     * Constructs a player object with the specified values for name and mark.
     *
     * @param name     the name of the Player
     * @param mark     the mark that the player will play the game with. Either X or
     *                 O
     * @param myClient the my client
     */
    public Player(String name, char mark, ServerClient myClient) {
        this.name = name;
        this.mark = mark;
        this.myClient = myClient;
    }

    /**
     * Setter method for the Board member variable
     *
     * @param board the board that the game will be played on
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Setter method for the Player's opponent
     *
     * @param opponent the Player that is playing against this Player
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Controls the flow of gameplay and checks if the either player has won, or if
     * the game has ended in a Tie. Method will also announce the winner if a
     * winning condition is reached
     *
     * @throws IOException if an I/O error occurs
     */
    public void play() throws IOException {
        while (!board.isFull() && !board.oWins() && !board.xWins()) {
            announcement("BOARD");
            announcement(board.sendState());
            opponent.myClient.sendMessage("Waiting for opponent to make a move...");
            makeAMove();
            announcement("Good move, here is the board...");
            // checks if the player that placed the last mark has a winning condition, if so
            // announce the winner and break out of the loop
            if (board.checkWinner(mark) == 1) {
                announcement("BOARD");
                announcement(board.sendState());
                announcement("THE GAME IS OVER: " + name + " has won!");
                break;
            }
            // checks if the board is full and there are no winning conditions.
            // if true, output a message that it is a tie game and end the game
            else if (board.isFull() && !board.xWins() && !board.oWins()) {
                announcement("BOARD");
                announcement(board.sendState());
                announcement("Tie game!! Please play again!");
                break;
            }
            opponent.play();
        }
    }

    /**
     * Prompts the user for the row and column for where they wish to place their
     * mark on the board. Ensures user input is valid and will re-prompt in the
     * event the Player requests and invalid move
     *
     * @throws IOException if an I/O error occurs
     */
    public void makeAMove() throws IOException {
        int row, col;
        myClient.sendMessage(name + ", what space should your next " + mark + " be placed in? ");
        // Outer loop to verify that the move chosen by the player is in an open space
        while (true) {
            // Loop to validate that the user enters an integer of 0, 1, or 2 for the row.
            // Catches exceptions if they enter anything other than an integer
            row = Integer.parseInt(myClient.getMessage());
            col = Integer.parseInt(myClient.getMessage());
            if (board.isValidMove(row, col)) {
                break;
            } else {
                myClient.sendMessage("Invalid move, space is already taken! Please try again");
            }
        }
        board.addMark(row, col, mark);
    }

    /**
     * Announcement.
     *
     * @param message the message
     */
    public void announcement(String message) {
        try {
            myClient.sendMessage(message);
            opponent.myClient.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
