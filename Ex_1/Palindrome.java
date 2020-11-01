package Ex_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Palindrome implements Runnable {
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public Palindrome(PrintWriter socketOut, BufferedReader socketIn) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
    }

    public void isPalin() {
        String line = null;
        while (true) {
            try {
                line = socketIn.readLine();
                if (line.equals("QUIT")) {
                    line = "Good Bye!";
                    socketOut.println(line);
                    break;
                }
                StringBuffer sb = new StringBuffer(line);
                if (sb.reverse().equals(sb)) {
                    socketOut.printf("%s\nIs a palindrome!\n", sb);
                } else {
                    socketOut.printf("%s\nIs not a palindrome!\n", sb);
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