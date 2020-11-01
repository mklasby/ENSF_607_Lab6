package Exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents a player in a tic-tac-toe game and provides methods for running making moves.
 */
abstract public class Player {
    /**
     * The name of the player
     */
    protected String name;

    /**
     * The board object that the game is played on
     */
    protected Board board;

    /**
     * The Players opponent in the game
     */
    protected Player opponent;

    /**
     * The players "mark" for the game, either an X or O
     */
    protected char mark;

    /**
     * Constructs a player object with the specified values for name and mark.
     *
     * @param name the name of the Player
     * @param mark the mark that the player will play the game with. Either X or O
     */
    protected Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Setter method for the Board member variable
     *
     * @param board the board that the game will be played on
     */
    protected void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Setter method for the Player's opponent
     *
     * @param opponent the Player that is playing against this Player
     */
    protected void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    protected void play() throws IOException{
        while (!board.isFull() && !board.oWins() && !board.xWins()) {
            makeAMove();
            board.display();
            //checks if the player that placed the last mark has a winning condition, if so
            //announce the winner and break out of the loop
            if (board.checkWinner(mark) == 1) {
                System.out.println("THE GAME IS OVER: " + name + " has won!");
                System.out.println("Game ended...");
                break;
            }
            //checks if the board is full and there are no winning conditions.
            //if true, output a message that it is a tie game and end the game
            else if (board.isFull() && !board.xWins() && !board.oWins()) {
                System.out.println("Tie game!! Please play again!");
                System.out.println("Game ended...");
            }
            opponent.play();
        }
    }

    protected abstract void makeAMove() throws IOException;

}
