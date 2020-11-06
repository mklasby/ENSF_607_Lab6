package Ex_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Simple server listening for clients on port 8099, sends the current date or time to the client
 *
 * @author Davis Allan & Mike Lasby
 * @since 2020 -11-01
 * @version 1.0
 */
public class DateServer {
	private BufferedReader socketInput;
	private PrintWriter socketOutput;
	private ServerSocket serverSocket;
	private Socket aSocket;

	/**
	 * Construct a Server with Port 9090
	 */
	public DateServer() {
		try {
			serverSocket = new ServerSocket(9090);
			System.out.println("Date Server is now running.");
			aSocket = serverSocket.accept();
			socketInput = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOutput = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (IOException e) {
		}
	}

	/**
	 * Get input from Ex_1.Client.
	 *
	 * @throws IOException the io exception
	 */
	public void getUserInput() throws IOException {
		StringBuffer line = null;
		while (true) {
			line = new StringBuffer(socketInput.readLine());
			if (line != null) {
				if (line.toString().equals("QUIT")) {
					break;
				}
				if (line.toString().equals("DATE")) {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					socketOutput.println( sdf.format(cal.getTime()));
				} else if (line.toString().equals("TIME")) {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					socketOutput.println( sdf.format(cal.getTime()));
				}else {
					socketOutput.println("Wrong input, please try again");
				}
			}
		}
		socketInput.close();
		socketOutput.close();
		serverSocket.close();
	}

	/**
	 * Run the Server.
	 *
	 * @param args the input arguments
	 * @throws IOException the io exception
	 */
	public static void main(String[] args) throws IOException {
		DateServer ds = new DateServer();
		ds.getUserInput();
	}
}