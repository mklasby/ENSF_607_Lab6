package Ex_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private PrintWriter socketOut;
    private Socket dateSocket;
    private BufferedReader stdIn;
    private BufferedReader socketIn;

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
