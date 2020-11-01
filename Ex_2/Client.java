package Ex_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Simple client to prompt user to enter DATE or TIME and get the current
 * date or time from the server
 *
 * @author: Davis Allan and Mike Lasby
 * @since: Nov. 1, 2020
 * @version: 1.0
 */

public class Client {
    private PrintWriter socketOut;
    private Socket dateSocket;
    private BufferedReader stdIn;
    private BufferedReader socketIn;

    /**
     * Creates a Client object and instantiates all of its member variables from the provided arguments
     * @param serverName the name of the server
     * @param portNumber the port for the server
     */
    public Client (String serverName, int portNumber) {
        try {
            dateSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(dateSocket.getInputStream()));
            socketOut = new PrintWriter((dateSocket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user for input, sends input to the server, and prints out the response
     */
    public void communicate() {
        String line = "";
        String response = "";

        while (!line.equals("QUIT")) {
            try {
                System.out.println("Please select an option (DATE/TIME):");
                line = stdIn.readLine();

                socketOut.println(line);
                response = socketIn.readLine();
                if (response == null) {
                    System.out.println("Good Bye!");
                    break;
                }
                System.out.println(response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            stdIn.close();
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client myClient = new Client("localhost", 9090);
        myClient.communicate();
    }
}
