package tictactoe;

/**
 * Parent class to define functionality for subclass types of Player.  
 * 
 * @author Mike Lasby
 * @since Oct 19, 2020
 * @version 2.0
 */

import java.io.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 12L;
    protected String name;
    protected Board board;
    protected char mark;
    private Player opponent;

    public Player(String name, char letter) {
        this.name = name;
        this.mark = letter;
        setBoard(new Board());
    }

    protected void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    protected String getName() {
        return this.name;
    }

    protected void setOpponent(Player o) {
        this.opponent = o;
    }

    /**
     * Checks if there is a winner or board is full, if not, prompts player to move.
     * Method will prompt both human and computer operated players to mimic a
     * player-to-player game.
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
        }
    }

    /**
     * Method to be implemented in each player subclass based on their respective
     * strategies.
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
        System.out.print("Waiting for Opponent's move...\n");
    }

    /**
     * Reports a successful move to CLI.
     * 
     * @param row: row where move was made
     * @param col: col where move was made
     */
    protected void reportMove(int row, int col) {
        System.out.printf("Good move, I've added an %c to (%d,%d). Here's the board:\n\n", this.mark, row, col);
        board.display();
        System.out.print("\n\n");
    }

    public Board getBoard() {
        return this.board;
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
