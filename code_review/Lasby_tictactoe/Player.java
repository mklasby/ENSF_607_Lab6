package tictactoe;

/**
 * Parent class to define functionality for subclass types of Player.  
 * 
 * @author Mike Lasby
 * @since Oct 19, 2020
 * @version 2.0
 */

import java.io.*;

abstract public class Player {
    protected String name;
    protected Board board;
    protected Player opponent;
    protected char mark;

    public Player(String name, char letter) {
        this.name = name;
        this.mark = letter;
    }

    protected void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    protected void setOpponent(Player player) {
        this.opponent = player;
    }

    protected String getName() {
        return this.name;
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
            opponent.play();
        }
    }

    /**
     * Method to be implemented in each player subclass based on their respective
     * strategies.
     */
    abstract protected void makeMove();

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
}
