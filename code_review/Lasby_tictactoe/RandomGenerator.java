package tictactoe;

/**
 * Class to get a random number. 
 * 
 * @author Mike Lasby
 * @version 1.0
 * @since Oct. 19, 2020
 */

import java.util.Random;

class RandomGenerator {

	/**
	 * creates a random number ranging between lo and hi,
	 * 
	 * @param lo
	 * @param hi
	 * @return
	 */
	int discrete(int lo, int hi) {
		if (lo >= hi) {
			System.out.println("Error discrete, lo >= hi");
			System.exit(0);
		}

		Random r = new Random();
		int d = r.nextInt(hi - lo + 1) + lo;
		return d;
	}

}
