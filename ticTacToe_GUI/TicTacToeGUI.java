package ticTacToe_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.*;

import javax.swing.*;

public class TicTacToeGUI extends JFrame implements Constants {
    private List<JButton> buttons = new ArrayList<JButton>();
    private JLabel playerLabel = new JLabel("Player:");
    private JTextField playerField = new JTextField(10);
    private JLabel name = new JLabel("User Name:");
    private JTextField nameField = new JTextField(10);
    private JLabel messageLabel = new JLabel("Message Window: ");
    private JTextArea messageBox = new JTextArea(5, 5);

    public TicTacToeGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mapping index 0,1,2 to row,col (0,0), (0,1), (0,2)...
        JPanel mainPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons.add(new JButton(Character.toString(SPACE_CHAR)));
            buttons.get(i).setPreferredSize(new Dimension(100, 100));
            buttonPanel.add(buttons.get(i));

        }

        mainPanel.add("LINE_START", buttonPanel);

        JPanel ioPanel = new JPanel();
        ioPanel.setLayout(new BoxLayout(ioPanel, BoxLayout.PAGE_AXIS));
        ioPanel.add(messageLabel);
        messageBox.setLineWrap(true);
        ioPanel.add(messageBox);
        ioPanel.add(playerLabel);
        ioPanel.add(playerField);
        ioPanel.add(name);
        ioPanel.add(nameField);

        mainPanel.add("LINE_END", ioPanel);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        TicTacToeGUI gui = new TicTacToeGUI();
    }

    public List<JButton> getButtons() {
        return this.buttons;
    }

    public JTextField getPlayerField() {
        return this.playerField;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextArea getMessageBox() {
        return this.messageBox;
    }

    public void displayErrorMessage(String err) {
        JOptionPane.showMessageDialog(this, err);
    }

    public void addButtonListener(ActionListener listener) {
        for (JButton b : buttons) {
            b.addActionListener(listener);
        }
    }

    public void addNameListener(ActionListener listener) {
        this.nameField.addActionListener(listener);
    }

    public void updateBoard(String response) {
        String[] responses = response.split("%");
        for (int i = 0; i < 8; i++) {
            buttons.get(i).setText(responses[i]);
        }
    }
}
