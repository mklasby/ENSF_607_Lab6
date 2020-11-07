package Ex_4;

import java.io.*;
import java.net.Socket;

/**
 * Simple client to prompt user to enter Strings to be check for palindrome
 *
 * @author Davis Allan and Mike Lasby
 * @version 1.0
 * @since Nov. 1, 2020
 */
public class Client {
    private Socket gameSocket;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private BufferedReader stdIn;

    /**
     * Instantiates a new Client.
     *
     * @param serverName the server name
     * @param portNumber the port number
     */
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
            } catch (NullPointerException e) {
                System.out.println("Connection closed, thanks for playing!");
                break;
            }
        }
        try {
            stdIn.close();
            messageOut.close();
            messageIn.close();
            gameSocket.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Client aClient = new Client("localhost", 8099);
        aClient.communicate();
    }
}