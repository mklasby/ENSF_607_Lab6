package ticTacToe_DA;

import java.io.*;
import java.net.Socket;

/**
 * Simple client to prompt user to enter Strings to be check for palindrome
 *
 * @author Davis Allan and Mike Lasby
 * @since Nov. 1, 2020
 * @version 1.0
 */

public class Client {
    private Socket gameSocket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private BufferedReader stdIn;

    public Client(String serverName, int portNumber) {
        try {
            gameSocket = new Socket(serverName, portNumber);
            messageIn = new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
            messageOut = new PrintWriter(new OutputStreamWriter(gameSocket.getOutputStream()), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Prompts user for input and writes response from Palindrome to stdout.
     */
    public void communicate() {
        String line = "";
        String response = "";
        boolean running = true;
        while (running) {
            try {
                response = messageIn.readLine();
                if (response.equals("INPUT")) {
                    line = stdIn.readLine();
                    messageOut.println(line);
                    continue;
                }
                System.out.println(response);
            } catch (IOException e) {
                System.out.println("Sending error: " + e.getMessage());
            }
        }
        try {
            stdIn.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }

    }

    public static void main(String[] args) throws IOException {
        Client aClient = new Client("localhost", 8099);
        aClient.communicate();
    }
}