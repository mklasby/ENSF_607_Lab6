package Exercise3;

import java.io.IOException;

public class BlockingPlayer extends RandomPlayer {

    protected BlockingPlayer(String name, char mark, RandomGenerator random) {
        super(name, mark, random);
    }

    @Override
    protected void makeAMove() throws IOException {
        boolean block, madeABlock = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block = testForBlocking(i, j);
                if (block && board.isValidMove(i, j)) {
                    board.addMark(i, j, mark);
                    madeABlock = true;
                }
            }
        }
        if (!madeABlock) {
            super.makeAMove();
        }
    }

    protected boolean testForBlocking(int row, int col) {
        int markCount = 0;
        char oppMark = opponent.mark;

        //check vertical
        for (int i = 0; i < 3; i++) {
            if (board.getMark(row, i) == oppMark){
                markCount++;
            }
            if (markCount == 2) {
                return true;
            }
        }
        markCount = 0; //reset count
        //check horizontal
        for (int i = 0; i < 3; i++) {
            if (board.getMark(i,col) == oppMark) {
                markCount++;
            }
            if (markCount == 2) {
                return true;
            }
        }

        markCount = 0;
        //check diagonal1
        if (row == col) {
            for (int i = 0; i < 3; i++) {
                if (board.getMark(i, i) == oppMark) {
                    markCount++;
                }
                if (markCount == 2) {
                    return true;
                }
            }
        }

        markCount = 0;
        //check diagonal2
        if ((row == 1 && col == 1) || (row == 2 && col == 0) || (row == 0 && col == 2)) {
            for (int i = 0; i < 3; i++) {
                if (board.getMark(i, 3 - 1 - i) == oppMark) {
                    markCount++;
                }
                if (markCount == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
