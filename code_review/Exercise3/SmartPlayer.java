package Exercise3;

import java.io.IOException;

public class SmartPlayer extends BlockingPlayer {

    protected SmartPlayer(String name, char mark, RandomGenerator random) {
        super(name, mark, random);
    }

    @Override
    protected void makeAMove() throws IOException {
        boolean block, madeAMove = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block = testForWinning(i, j);
                if (block && board.isValidMove(i, j)) {
                    board.addMark(i, j, mark);
                    madeAMove = true;
                }
            }
        }
        if (!madeAMove) {
            super.makeAMove();
        }
    }

    protected boolean testForWinning(int row, int col) {
        int markCount = 0;
        //check vertical
        for (int i = 0; i < 3; i++) {
            if (board.getMark(row, i) == mark){
                markCount++;
            }
            if (markCount == 2) {
                return true;
            }
        }
        markCount = 0; //reset count
        //check horizontal
        for (int i = 0; i < 3; i++) {
            if (board.getMark(i,col) == mark) {
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
                if (board.getMark(i, i) == mark) {
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
                if (board.getMark(i, 3 - 1 - i) == mark) {
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
