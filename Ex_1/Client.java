package Ex_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Simple client to prompt user to enter Strings to be check for palindrome
 *
 * @author Davis Allan and Mike Lasby
 * @since Nov. 1, 2020
 * @version 1.0
 */
public class Client {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	/**
	 * Instantiates a new Client.
	 *
	 * @param serverName the server name
	 * @param portNumber the port number
	 */
	public Client(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
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
				System.out.println("please enter a word: ");
				line = stdIn.readLine();
				if (!line.equals("QUIT")) {
					System.out.println(line);
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);
				} else {
					running = false;
				}

			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
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