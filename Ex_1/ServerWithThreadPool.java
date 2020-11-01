package Ex_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServerWithThreadPool {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	private ExecutorService pool;

	public ServerWithThreadPool() {
		try {
			serverSocket = new ServerSocket(8099);
			pool = Executors.newFixedThreadPool(5);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		ServerWithThreadPool myServer = new ServerWithThreadPool();
		myServer.runServer();
	}

}
