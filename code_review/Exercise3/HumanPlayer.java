package Exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

    protected HumanPlayer(String name, char mark) {
        super(name, mark);
    }

    @Override
    protected void makeAMove() throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int row, col;

        //Outer loop to verify that the move chosen by the player is in an open space
        while (true) {
            //Loop to validate that the user enters an integer of 0, 1, or 2 for the row.
            //Catches exceptions if they enter anything other than an integer
            while (true) {
                try {
                    System.out.print(name + ", what row should your next " + mark + " be placed in? ");
                    row = Integer.parseInt(stdin.readLine());

                    if (row < 0 || row > 2) {
                        System.out.println("Invalid row, please enter 0,1, or 2.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter a valid integer");
                }
            }

            //Loop to validate that the user enters an integer of 0, 1, or 2 for the column.
            //Catches exceptions if they enter anything other than an integer
            while (true) {
                try {
                    System.out.print(name + ", what column should your next " + mark + " be placed in? ");
                    col = Integer.parseInt(stdin.readLine());

                    if (col < 0 || col > 2) {
                        System.out.println("Invalid column, please enter 0,1, or 2");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter a valid integer");
                }
            }

            //Check if the move is valid, if it is not a valid move, output a message and prompt
            //the user for a valid input
            if (board.isValidMove(row, col)) {
                break;
            } else {
                System.out.println("Invalid move, space is already taken! Please try again");
            }
        }
        //if the code has reached this point then the move is valid and the mark will be placed on the board
        board.addMark(row, col, mark);
    }
}
