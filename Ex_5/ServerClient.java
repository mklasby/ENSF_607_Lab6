package Ex_5;

import java.io.*;
import java.net.Socket;

/**
 * The type Server client.
 * 
 * @author Davis Allan & Mike Lasby
 * @since Nov. 7, 2020
 * @version 2.0
 */
public class ServerClient {

    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private Socket player;

    /**
     * Instantiates a new Server client.
     *
     * @param player the player
     * @throws IOException the io exception
     */
    public ServerClient(Socket player) throws IOException {
        setPlayer(player);
        setMessageIn(new BufferedReader(new InputStreamReader(player.getInputStream())));
        setMessageOut(new PrintWriter(new OutputStreamWriter(player.getOutputStream()), true));
    }

    /**
     * Gets message.
     *
     * @return the message
     * @throws IOException the io exception
     */
    public String getMessage() throws IOException {
        StringBuffer response = null;
        while (response == null) {
            response = new StringBuffer(messageIn.readLine());
        }
        return response.toString();
    }

    /**
     * Send message.
     *
     * @param message the message
     * @throws IOException the io exception
     */
    public void sendMessage(String message) throws IOException {
        messageOut.println(message);
    }

    /**
     * Close connections.
     *
     * @throws IOException the io exception
     */
    public void closeConnections() throws IOException {
        messageOut.close();
        messageIn.close();
        player.close();
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Socket getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Socket player) {
        this.player = player;
    }

    /**
     * Gets message out.
     *
     * @return the message out
     */
    public PrintWriter getMessageOut() {
        return messageOut;
    }

    /**
     * Sets message out.
     *
     * @param messageOut the message out
     */
    public void setMessageOut(PrintWriter messageOut) {
        this.messageOut = messageOut;
    }

    /**
     * Gets message in.
     *
     * @return the message in
     */
    public BufferedReader getMessageIn() {
        return messageIn;
    }

    /**
     * Sets message in.
     *
     * @param messageIn the message in
     */
    public void setMessageIn(BufferedReader messageIn) {
        this.messageIn = messageIn;
    }
}
