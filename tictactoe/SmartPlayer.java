package tictactoe;

/**
 * Computer operated player who selects moves based by prioritizing winning
 * moves and blocking moves. Should no winner or blocking move be present, a
 * random move is made.
 * 
 * @author: Mike Lasby
 * @since: Oct. 19, 2020
 * @version: 1.0
 */

public class SmartPlayer extends BlockingPlayer {

    public SmartPlayer(String name, char mark) {
        super(name, mark);
    }

    /**
     * Make a move by first checking for a winning move, then a blocking move, and
     * finally defaults to a random move
     */
    @Override
    protected void makeMove() {
        // test for winner
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (testForWinner(row, col)) {
                    board.addMark(row, col, this.mark);
                    reportMove(row, col);
                    return;
                }
            }
        }
        super.makeMove(); // tests for blocking and then returns random if none found
        return;
    }

    /**
     * Tests if a move at a given row,col will win.
     * 
     * @param row: row to be checked for winning
     * @param col: col to be checked for winning
     * @return boolean: True if move will win, false otherwise
     */
    protected boolean testForWinner(int row, int col) {
        char mark = board.getMark(row, col);
        if (mark != SPACE_CHAR) {
            return false;
        }

        // check row
        int counter = 0;
        for (int c = 0; c < 3; c++) {
            mark = board.getMark(row, c);
            if (mark == this.mark) {
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
            if (mark == this.mark) {
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
                if (mark == this.mark) {
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
                if (mark == this.mark) {
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
