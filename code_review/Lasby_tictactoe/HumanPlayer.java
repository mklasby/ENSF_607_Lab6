package tictactoe;

/**
 * Human player for tic-tac-toe game. 
 * 
 * @author Mike Lasby
 * @version 1.0
 * @since Oct. 19, 2020
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char mark) {
        super(name, mark);
    }

    /**
     * Method to check if play will continue (ie. no winner or full board). If play
     * continues, prompt this Player instance to select a cell to play in before
     * passing control back to opponent.
     * 
     */
    protected void play() {
        if (board.xWins()) {
            String winner = this.mark == 'X' ? this.name : opponent.getName();
            System.out.printf("Game over! %s wins!\n", winner);
            System.exit(0);
        } else if (board.oWins()) {
            String winner = this.mark == 'O' ? this.name : opponent.getName();
            System.out.printf("Game over! %s wins!\n", winner);
            System.exit(0);
        } else if (board.isFull()) {
            System.out.printf("Game over! It's a tie! No more free spaces remain.\n");
            System.exit(0);
        } else {
            System.out.printf("%s's move!\n", name);
            makeMove();
            opponent.play();
        }
    }

    /**
     * Method to prompt Player for good row/col input to make a move. Will re-prompt
     * until good input is received.
     */
    protected void makeMove() {
        boolean badMove = true;
        int row = -1;
        int col = -1;

        while (badMove) {
            System.out.print("Please enter the row number: \n");
            row = getInteger();
            System.out.print("Please enter the column number:\n");
            col = getInteger();
            if (board.addMark(row, col, this.mark)) {
                badMove = false;
            }
        }
        System.out.printf("Good move, I've added an %c to (%d,%d). Here's the board:\n\n", mark, row, col);
        board.display();
        System.out.print("\n\n");

    }

    /**
     * Helper method to make sure that row/col inputs are integers. Will re-prompt
     * if any other type is inputted.
     * 
     * 
     * @return int - good integer input
     */
    private int getInteger() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int value = -1;
        boolean badInput = true;

        while (badInput) {
            try {
                input = br.readLine();
                value = Integer.parseInt(input);
                badInput = false;
            } catch (Exception e) {
                System.out.print("ERROR! Bad input, please enter row/column numbers as integers.\nPlease try again: ");
                continue;
            }
        }
        return value;
    }
}
