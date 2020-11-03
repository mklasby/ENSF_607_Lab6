package ticTacToe_DA;

import java.io.*;
import java.net.Socket;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

/**
 * Provides methods to create a game of tic-tac-toe to be played by two players.
 * Responsible for setting up the game and appointing the referee to then run the game
 *
 * @author Davis Allan
 * @version 1.0
 * @since Sept 30 2020
 */
public class Game implements Constants, Runnable {

	private ServerClient xClient;
	private ServerClient oClient;

	/**
	 * Board object representing the 3x3 tic-tac-toe grid
	 */
	private Board theBoard;

	/**
	 * The referee for the game
	 */
	private Referee theRef;

	/**
	 * Constructs a new Game object and constructs a new Board for the game to be played on
	 */
    public Game(Socket xPlayer, Socket oPlayer) {
		try {
			this.xClient = new ServerClient(xPlayer);
			this.oClient = new ServerClient(oPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		theBoard  = new Board();
	}

	/**
	 * Sets the referee and begins the game
	 * @param r Referee for the game to be played
	 * @throws IOException if an input error occurs
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }

    public void announcement(String message) {
    	try {
			oClient.sendMessage(message);
			xClient.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Referee theRef = new Referee();
		Player xPlayer, oPlayer;

		try {
			xClient.sendMessage("Message: Welcome to the game! You are the 'X' player, please enter your name: ");
			xClient.sendMessage("INPUT");
			oClient.sendMessage("Message: Welcome to the game! You are the 'O' player, please enter your name: ");
			oClient.sendMessage("INPUT");

			String name1 = xClient.getMessage();
			xPlayer = new Player(name1, LETTER_X);
			xPlayer.setBoard(this.theBoard);

			String name2 = oClient.getMessage();
			oPlayer = new Player(name2, LETTER_O);
			oPlayer.setBoard(this.theBoard);

			theRef.setBoard(this.theBoard);
			theRef.setxPlayer(xPlayer);
			theRef.setoPlayer(oPlayer);
			announcement("Referee has started the game...");

			this.appointReferee(theRef);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
