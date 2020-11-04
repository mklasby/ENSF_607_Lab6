package tictactoe;

import java.io.*;
import java.net.Socket;

public class ServerClient {

    private ObjectOutputStream messageOut;
    private ObjectInputStream messageIn;
    private Socket player;

    public ServerClient(Socket player) throws IOException {
        setPlayer(player);
        setMessageOut(new ObjectOutputStream(player.getOutputStream()));
        setMessageIn(new ObjectInputStream(player.getInputStream()));
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        String response = null;
        while (response == null) {
            response = (String) messageIn.readObject();
        }
        return new Message(response);
    }

    public void sendMessage(Message message) throws IOException {
        messageOut.writeObject(message);
    }

    public void sendPlayer(Player player) throws IOException {
        messageOut.writeObject(player);
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

    public ObjectOutputStream getMessageOut() {
        return messageOut;
    }

    public void setMessageOut(ObjectOutputStream messageOut) {
        this.messageOut = messageOut;
    }

    public ObjectInputStream getMessageIn() {
        return messageIn;
    }

    public void setMessageIn(ObjectInputStream messageIn) {
        this.messageIn = messageIn;
    }
}
