package tictactoe;

/**
 * Computer operated player who picks a move at random.
 * 
 * @author Mike Lasby
 * @since Oct. 19, 2020
 * @version 1.0
 */

public class RandomPlayer extends Player implements Constants {
    RandomGenerator rg = new RandomGenerator();

    public RandomPlayer(String name, char mark) {
        super(name, mark);
    }

    /**
     * Makes a random move.
     */
    protected void makeMove() {
        boolean badMove = true;
        int row = -1;
        int col = -1;

        while (badMove) {
            row = rg.discrete(0, 2);
            col = rg.discrete(0, 2);
            if (board.getMark(row, col) == SPACE_CHAR) {
                board.addMark(row, col, this.mark);
                badMove = false;
            }
        }
        reportMove(row, col);
        return;
    }
}
