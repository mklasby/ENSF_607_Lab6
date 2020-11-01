package tictactoe;

/**
 * A BlockingPlayer is a RandomPlayer that fist looks at the board for a move
 * that would block its opponent from winning the next move. If no such spot is
 * found, a random move is made.
 * 
 * @author: Mike Lasby
 * @since: Oct 19, 2020
 * @version 1.0
 */

public class BlockingPlayer extends RandomPlayer {

    public BlockingPlayer(String name, char mark) {
        super(name, mark);
    }

    /**
     * Makes a move to block the opponent from winning, otherwise makes a random
     * move.
     */
    protected void makeMove() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (testForBlocking(row, col)) {
                    board.addMark(row, col, this.mark);
                    reportMove(row, col);
                    return;
                }
            }
        }
        super.makeMove(); // make random move
        return;
    }

    protected boolean testForBlocking(int row, int col) {

        char mark = board.getMark(row, col);
        if (mark != SPACE_CHAR) {
            return false;
        }

        // check row
        int counter = 0;
        for (int c = 0; c < 3; c++) {
            mark = board.getMark(row, c);
            if (mark != this.mark && mark != SPACE_CHAR) {
                counter++;
            }
        }
        if (counter == 2) {
            return true;
        }

        // check column
        counter = 0;
        for (int r = 0; r < 3; r++) {
            mark = board.getMark(r, col);
            if (mark != this.mark && mark != SPACE_CHAR) {
                counter++;
            }
        }
        if (counter == 2) {
            return true;
        }

        // diagonal check
        counter = 0;
        if (row == col) {
            for (int i = 0; i < 3; i++) {
                mark = board.getMark(i, i);
                if (mark != this.mark && mark != SPACE_CHAR) {
                    counter++;
                }
            }
            if (counter == 2) {
                return true;
            }
        }

        counter = 0;
        if (Math.abs(row - col) == 2 || (row == 1 && col == 1)) {
            int j = 2;
            for (int i = 0; i < 3; i++) {
                mark = board.getMark(i, j);
                if (mark != this.mark && mark != SPACE_CHAR) {
                    counter++;
                }
                j--;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}
