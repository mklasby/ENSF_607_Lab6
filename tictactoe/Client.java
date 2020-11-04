package tictactoe;

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
    private ObjectOutputStream messageOut;
    private ObjectInputStream messageIn;
    private BufferedReader stdIn;
    private Player myPlayer;

    public Client(String serverName, int portNumber) {
        try {
            gameSocket = new Socket(serverName, portNumber);
            messageIn = new ObjectInputStream(gameSocket.getInputStream());
            messageOut = new ObjectOutputStream(gameSocket.getOutputStream());
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
        Message response = null;
        boolean running = true;
        Message mark = null;
        try {
            // get my mark
            mark = (Message) messageIn.readObject();
            // get name prompt
            // while (response == null) {
            response = (Message) messageIn.readObject();
            // }
            System.out.print(response.getMessage());
            // read name
            line = stdIn.readLine();
            // send name
            messageOut.writeObject(line);
            // generate myPlayer
            this.myPlayer = new Player(line, (mark.getMessage().charAt(0)));
            // receive myOpponent data
            this.myPlayer.setOpponent((Player) messageIn.readObject());
            // get start message
            // while (response == null) {
            response = (Message) messageIn.readObject();
            // }
            if (mark.getMessage().charAt(0) == 'O') {
                // only display if opponent is playing first
                myPlayer.getBoard().display();
            }

        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }

        while (running) {
            try {
                response = (Message) messageIn.readObject();
                if (response.isText()) {
                    System.out.println(response.getMessage());
                } else {
                    myPlayer.setBoard(response.getBoard());
                    myPlayer.play();
                    messageOut.writeObject(myPlayer.getBoard());
                }

            } catch (IOException | ClassNotFoundException e) {
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

    public static void main(String[] args) throws IOException {
        Client aClient = new Client("localhost", 8099);
        aClient.communicate();
    }
}