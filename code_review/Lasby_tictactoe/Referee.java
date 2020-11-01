package tictactoe;

/**
 * Class to "referee" the game. Will display board and prompt xPlayer to being
 * playing.
 * 
 * @author Mike Lasby
 * @since Oct. 1, 2020
 * @version 1.0
 */

public class Referee {
    private Player xPlayer;
    private Player oPlayer;
    private Board board;

    /**
     * Method to start a new game. Sets the player opponents, displays a welcome
     * message, displays the board, and then prompts xPlayer to begin.
     */
    public void runTheGame() {
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        System.out.printf("Let's begin playing!\n\nHere's the Board:\n");
        board.display();
        xPlayer.play();
    }

    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    public void setOPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    public void setXPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

}
