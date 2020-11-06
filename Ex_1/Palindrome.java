package Ex_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to check if a string passed over socketIn is a palindrome
 *
 * @author Davis Allan and Mike Lasby
 * @version 1.0
 * @since 2020 -11-01
 */
public class Palindrome implements Runnable {
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    /**
     * Instantiates a new Palindrome.
     *
     * @param socketOut the socket out
     * @param socketIn  the socket in
     */
    public Palindrome(PrintWriter socketOut, BufferedReader socketIn) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
    }

    /**
     * Checks if strings passed over socketIn are palindromes. Writes response to
     * socketOut.
     */
    public void isPalin() {
        String line;
        while (true) {
            try {
                line = socketIn.readLine();
                if (line == null) {
                    break;
                }
                StringBuffer sb = new StringBuffer(line);
                String rev = sb.reverse().toString();
                if (rev.equals(line)) {
                    socketOut.printf("%s Is a palindrome!\n", line);
                } else {
                    socketOut.printf("%s Is not a palindrome!\n", line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        isPalin();
    }
}