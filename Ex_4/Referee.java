package Ex_4;

import java.io.IOException;

/**
 * Represents a referee which has methods responsible for setting up the
 * opponents and beginning the first turn of the game.
 * 
 * @author Davis Allan & Mike Lasby
 * @since Nov. 7, 2020
 * @version 2.0
 */
public class Referee {

    private Player xPlayer;
    private Player oPlayer;
    private Board board;

    /**
     * Setter method for the Board member variable
     *
     * @param board the board that the game will be played on
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Sets the Player who will be using X's during the game
     *
     * @param xPlayer Player that will be using X's as their mark
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    /**
     * Sets the Player who will be using O's during the game
     *
     * @param oPlayer Player that will be using O's as their mark
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    /**
     * Sets the two Players as each other's opponents and then begins the first turn
     * of the game
     *
     * @throws IOException if an I/O error occurs
     */
    public void runTheGame() throws IOException {
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        board.display();
        xPlayer.play();
    }
}
