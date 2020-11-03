package ticTacToe_DA;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple server multi listening for clients on port 8099
 * @author Davis Allan & Mike Lasby
 * @since 2020-11-01
 * @version 1.0
 */
public class Server {

    private Socket xPlayer;
    private Socket oPlayer;
    private ServerSocket serverSocket;
    private ObjectInputStream xInput;
    private ObjectOutputStream xOutput;
    private ObjectInputStream oInput;
    private ObjectOutputStream oOutput;
    private BufferedReader xMessageIn;
    private BufferedReader oMessageIn;
    private PrintWriter xMessageOut;
    private PrintWriter oMessageOut;

    private ExecutorService pool;

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


//                xOutput = new ObjectOutputStream(xPlayer.getOutputStream());
//                xInput = new ObjectInputStream(xPlayer.getInputStream());
//
//                oOutput = new ObjectOutputStream(oPlayer.getOutputStream());
//                oInput = new ObjectInputStream(oPlayer.getInputStream());
//
//                xMessageIn = new BufferedReader(new InputStreamReader(xPlayer.getInputStream()));
//                xMessageOut = new PrintWriter(new OutputStreamWriter(xPlayer.getOutputStream()), true);
//
//                oMessageIn = new BufferedReader(new InputStreamReader(oPlayer.getInputStream()));
//                oMessageOut = new PrintWriter(new OutputStreamWriter(oPlayer.getOutputStream()), true);

                Game game = new Game(xPlayer, oPlayer);
                pool.execute(game);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pool.shutdown();
        try {
            xInput.close();
            xOutput.close();
            oInput.close();
            oOutput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        Server myServer = new Server();
        myServer.runServer();
    }

}
