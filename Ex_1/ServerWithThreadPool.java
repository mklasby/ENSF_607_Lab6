package Ex_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple server multi listening for clients on port 8099
 * 
 * @author Davis Allan & Mike Lasby
 * @since 2020-11-01
 * @version 1.0
 */
public class ServerWithThreadPool {
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ExecutorService pool;

	/**
	 * Instantiates a new Server with thread pool.
	 */
	public ServerWithThreadPool() {
		try {
			serverSocket = new ServerSocket(8099);
			pool = Executors.newFixedThreadPool(5);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Listens for a client connection, creates new socket reader/writers, and
	 * executes Palindrome.run() on one of 5 executors.
	 */
	public void runServer() {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Connection accepted by the server!");
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter(aSocket.getOutputStream(), true);
				Palindrome pal = new Palindrome(socketOut, socketIn);
				pool.execute(pal);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool.shutdown();
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws IOException the io exception
	 */
	public static void main(String[] args) throws IOException {

		ServerWithThreadPool myServer = new ServerWithThreadPool();
		myServer.runServer();
	}

}
