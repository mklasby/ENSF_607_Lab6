package Exercise3;

import java.io.IOException;

public class RandomPlayer extends Player{
    protected RandomGenerator random;

    protected RandomPlayer(String name, char mark, RandomGenerator randomGenerator) {
        super(name, mark);
        setRandom(randomGenerator);
    }

    public RandomGenerator getRandom() {
        return random;
    }

    public void setRandom(RandomGenerator rGen) {
        this.random = rGen;
    }

    @Override
    protected void makeAMove() throws IOException {
        int row, col;
        do {
            row = getRandom().discrete(0, 2);
            col = getRandom().discrete(0, 2);

        } while (!board.isValidMove(row, col));

        board.addMark(row, col, mark);
    }
}
