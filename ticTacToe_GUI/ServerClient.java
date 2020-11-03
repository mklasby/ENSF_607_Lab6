package ticTacToe_GUI;

import java.io.*;
import java.net.Socket;

public class ServerClient {

    private PrintWriter messageOut;
    private BufferedReader messageIn;
    private Socket player;

    public ServerClient(Socket player) throws IOException {
        setPlayer(player);
        setMessageIn(new BufferedReader(new InputStreamReader(player.getInputStream())));
        setMessageOut(new PrintWriter(new OutputStreamWriter(player.getOutputStream()), true));
    }

    public String getMessage() throws IOException {
        StringBuffer response = null;
        while (response == null) {
            response = new StringBuffer(messageIn.readLine());
        }
        return response.toString();
    }

    public void sendMessage(String message) throws IOException {
        messageOut.println(message);
    }

    public void closeConnections() throws IOException {
        messageOut.close();
        messageIn.close();
        player.close();
    }

    public Socket getPlayer() {
        return player;
    }

    public void setPlayer(Socket player) {
        this.player = player;
    }

    public PrintWriter getMessageOut() {
        return messageOut;
    }

    public void setMessageOut(PrintWriter messageOut) {
        this.messageOut = messageOut;
    }

    public BufferedReader getMessageIn() {
        return messageIn;
    }

    public void setMessageIn(BufferedReader messageIn) {
        this.messageIn = messageIn;
    }
}
