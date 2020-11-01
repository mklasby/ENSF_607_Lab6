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
                String rev = sb.reverse().toString();
                if (rev.equals(line)) {
                    socketOut.printf("%s\n%sIs a palindrome!\n", line);
                } else {
                    socketOut.printf("%s\n%sIs not a palindrome!\n", line);
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