package Ex_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple server multi listening for clients on port 8099
 *
 * @author Davis Allan & Mike Lasby
 * @since Nov. 7, 2020
 * @version 2.0
 */
public class Server {

    private Socket xPlayer;
    private Socket oPlayer;
    private ServerSocket serverSocket;

    private ExecutorService pool;

    /**
     * Instantiates a new Server.
     */
    public Server() {
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
                System.out.println("Server is running...");
                xPlayer = serverSocket.accept();
                System.out.println("1 player has connected...");
                oPlayer = serverSocket.accept();
                System.out.println("2 players have connected, beginning a new game...");
                Game game = new Game(xPlayer, oPlayer);
                pool.execute(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        try {
            xPlayer.close();
            oPlayer.close();
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

        Server myServer = new Server();
        myServer.runServer();
    }

}
