package tictactoe;

/**
 * Class to store and display Board state during game play.
 * 
 * @author: Mike Lasby
 * @since Oct. 19, 2020
 * @version: 2.0
 */

public class Board implements Constants {
	private char theBoard[][]; // 2D array depiction of board
	private int markCount;

	public Board() {
		markCount = 0;
		theBoard = new char[3][]; // init theBoard to three columns with empty rows
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3]; // add empty spaces at each location
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Gets mark at row, col
	 * 
	 * @param row: int - row to query
	 * @param col: int - column to query
	 * @return: char - mark at (row, col)
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Method to determine if board is full
	 * 
	 * @return True if board is full
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Method to determine if player X has won.
	 * 
	 * @return true if playerX has won
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Method to determine if player O has won.
	 * 
	 * @return true if playerO has won
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Method to display board on CLI
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	 * Method to add mark at (row, col)
	 * 
	 * @param row:  int - row to add mark to
	 * @param col:  int - column to add mark to
	 * @param mark: char - mark to be added, X or O
	 * @return: boolean - true if mark is successfully place, false if mark already
	 *          exists or if row/column is out of bounds.
	 */
	public boolean addMark(int row, int col, char mark) {
		if (col > 2 || col < 0) {
			System.out.printf("ERROR! Column number %d is out of bounds! Please try again with 0, 1 or 2.\n", col);
			return false;
		} else if (row > 2 || row < 0) {
			System.out.printf("ERROR! Row number %d is out of bounds! Please try again with 0, 1, or 2.\n", row);
			return false;
		} else if (theBoard[row][col] != SPACE_CHAR) {
			System.out.printf("ERROR! Cell at (%d,%d) is already occupied! Please select a new cell.\n", row, col);
			return false;
		}
		theBoard[row][col] = mark;
		markCount++;
		return true;
	}

	/**
	 * Method to clear board back to an empty game
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Method to check if a winner exist on the board in its current state.
	 * 
	 * @param mark: char - mark to test if a winner (X or O)
	 * @return 0 if mark has won and 1 if mark has not yet won.
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
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
	 * Helper method to print out column headers in CLI.
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/**
	 * Helper method to display hypens below column headers in CLI.
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

	/**
	 * Helper method to add spaces to board display
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
