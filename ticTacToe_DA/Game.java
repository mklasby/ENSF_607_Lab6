package ticTacToe_DA;

import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

/**
 * Provides methods to create a game of tic-tac-toe to be played by two players.
 * Responsible for setting up the game and appointing the referee to then run the game
 *
 * @author Davis Allan
 * @version 1.0
 * @since Sept 30 2020
 */
public class Game implements Constants {
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
    public Game( ) {
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


	public static void main(String[] args) throws IOException {
    	Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();

		stdin = new BufferedReader(new InputStreamReader(System.in));
		//prompting user for the first player's name, ensures user enters a valid string
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		//setting up the first player
		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.theBoard);

		//prompting user for the second player's name, ensures user enters a valid string
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		//setting up the second player
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.theBoard);

		//setting up the referee and beginning the game
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
	}
	

}
