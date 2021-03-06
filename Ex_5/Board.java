package Ex_5;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

import java.io.Serializable;

/**
 * Represents a tic-tac-toe board and provides methods for playing the game.
 *
 * @author Davis Allan & Mike Lasby
 * @since Nov. 7, 2020
 * @version 2.0
 */
public class Board implements Constants, Serializable {

	private char theBoard[][];
	private int markCount;

	/**
	 * Constructs the Board object and initializes a blank 3x3 grid for starting a
	 * new game
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		// loop to create a 3x3 array filled with spaces to represent a blank board
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Returns the character contained in a specified location on the grid
	 *
	 * @param row the row of the board that the cell is contained in
	 * @param col the column of the board that the cell is contained in
	 * @return character of either X or O contained in the specified cell
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Checks if the board is full
	 *
	 * @return true if the board is full, false otherwise
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Determines if the player playing X's has won the game
	 *
	 * @return true if they are the winner, false otherwise
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Checks if the player playing O's has won the game
	 *
	 * @return true if they are the winner, false otherwise
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Draws the 3x3 grid in its current state with labels for each row and column
	 * to the terminal
	 *
	 * @return the string
	 */
	public String display() {
		StringBuilder result = new StringBuilder();

		result = displayColumnHeaders(result);
		result = addHyphens(result);
		// drawing the rows
		for (int row = 0; row < 3; row++) {
			result = addSpaces(result);
			result.append("    row ").append(row).append(' ');
			// drawing the columns
			for (int col = 0; col < 3; col++)
				result.append("|  ").append(getMark(row, col)).append("  ");
			result.append("|\n");
			result = addSpaces(result);
			result = addHyphens(result);
		}
		return result.toString();
	}

	/**
	 * Send state string.
	 *
	 * @return the string
	 */
	public String sendState() {
		StringBuilder result = new StringBuilder();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				result.append(theBoard[row][col]);
				result.append("%");
			}
		}
		return result.toString();
	}

	/**
	 * Adds the character mark to the user specified location on the board
	 *
	 * @param row  the row where the mark is to be inserted
	 * @param col  the column where the mark is to be inserted
	 * @param mark the character to be added to the board
	 */
	public void addMark(int row, int col, char mark) {

		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Determines if the location on the board is already occupied by another player
	 *
	 * @param row the row to be checked
	 * @param col the column to be checked
	 * @return true if there is no mark present in that cell, false otherwise
	 */
	public boolean isValidMove(int row, int col) {
		if (theBoard[row][col] != SPACE_CHAR) {
			return false;
		}
		return true;
	}

	/**
	 * Clears the board of all previous marks to restart the game and resets the
	 * mark counter
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks to see if a player has won the game by placing three matching
	 * characters to draw a horizontal, vertical, or diagonal line
	 *
	 * @param mark the mark to be checked for a winning condition
	 * @return 1 if the mark being checked has a winning condition, 0 if there is no
	 *         winning condition
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		// checking for a horizontal win
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		// checking for a vertical win
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		// checking if there is a diagonal win from top left to bottom right of the grid
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		// checking if there is a diagonal win from the top right to the bottom left of
		// the grid
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Prints the three column headers of the game board
	 *
	 * @param sb the sb
	 * @return the string builder
	 */
	StringBuilder displayColumnHeaders(StringBuilder sb) {
		sb.append("          ");
		for (int j = 0; j < 3; j++)
			sb.append("|col ").append(j);
		sb.append("\n");
		return sb;
	}

	/**
	 * Prints a horizontal row of hyphens representing the boundaries in the game
	 * board
	 *
	 * @param sb the sb
	 * @return the string builder
	 */
	StringBuilder addHyphens(StringBuilder sb) {
		sb.append("          ");
		for (int j = 0; j < 3; j++)
			sb.append("+-----");
		sb.append("+\n");
		return sb;
	}

	/**
	 * Prints the vertical lines representing the boundaries in the game board
	 *
	 * @param sb the sb
	 * @return the string builder
	 */
	StringBuilder addSpaces(StringBuilder sb) {
		sb.append("          ");
		for (int j = 0; j < 3; j++)
			sb.append("|     ");
		sb.append("|\n");
		return sb;
	}
}
