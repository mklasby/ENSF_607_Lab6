package tictactoe;

/**
* Main function of tic tac toe game.
*
* @author Mike Lasby
* @since Oct. 19, 2020
* @version 1.0
*/
import java.io.*;
import java.net.Socket;

public class Game implements Constants, Runnable {

    private Board theBoard;
    private Referee theRef;
    private ServerClient xClient;
    private ServerClient oClient;

    /**
     * creates a board for the game
     */
    public Game(Socket xPlayer, Socket oPlayer) {
        try {
            this.xClient = new ServerClient(xPlayer);
            this.oClient = new ServerClient(oPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        theBoard = new Board();
    }

    /**
     * calls the referee method runTheGame
     *
     * @param r refers to the appointed referee for the game
     * @throws IOException
     */
    public void runGame() throws IOException {
        announcement(new Message("Let's begin playing!\n\nHere's the Board:\n"));
        xClient.sendMessage(new Message(this.theBoard));
    }

    @Override
    public void run() {
        System.out.print("in game.run");
        Referee theRef = new Referee();
        Player xPlayer, oPlayer;

        try {
            xClient.sendMessage(new Message(Character.toString(LETTER_X)));
            xClient.sendMessage(
                    new Message("Message: Welcome to the game! You are the 'X' player, please enter your name: \n"));
            Message name1 = (Message) xClient.getMessage();

            oClient.sendMessage(new Message(Character.toString(LETTER_O)));
            oClient.sendMessage(
                    new Message("Message: Welcome to the game! You are the 'O' player, please enter your name: \n"));
            Message name2 = (Message) oClient.getMessage();

            xPlayer = new Player(name1.getMessage(), LETTER_X);
            oPlayer = new Player(name2.getMessage(), LETTER_O);
            xClient.sendPlayer(oPlayer);
            oClient.sendPlayer(xPlayer);

            announcement(new Message("Referee has started the game..."));
            this.runGame();

            xClient.closeConnections();
            oClient.closeConnections();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void announcement(Message message) {
        try {
            xClient.sendMessage(message);
            oClient.sendMessage(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
