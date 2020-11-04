package tictactoe;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 100L;
    private String message;
    private Board board;
    private boolean isText = false;

    public Message(String message) {
        this.setMessage(message);
        this.setBoard(null);
        this.isText = true;
    }

    public Message(Board board) {
        this.setMessage(null);
        this.setBoard(board);
        this.isText = false;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean isText) {
        this.isText = isText;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
