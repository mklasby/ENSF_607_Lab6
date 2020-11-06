package ticTacToe_GUI;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.*;

import javax.swing.*;

/**
 * The type Tic tac toe gui.
 */
public class TicTacToeGUI extends JFrame implements Constants {
    private List<JButton> buttons = new ArrayList<JButton>();
    private JLabel playerLabel = new JLabel("Player:");
    private JTextField playerField = new JTextField(2);
    private JLabel name = new JLabel("User Name:");
    private JTextField nameField = new JTextField(10);
    private JLabel messageLabel = new JLabel("Message Window: ");
    private JTextArea messageBox = new JTextArea(5, 5);

    /**
     * Instantiates a new Tic tac toe gui.
     */
    public TicTacToeGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mapping index 0,1,2 to row,col (0,0), (0,1), (0,2)...
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600,350));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons.add(new JButton(Character.toString(SPACE_CHAR)));
            buttons.get(i).setPreferredSize(new Dimension(100, 100));
            buttonPanel.add(buttons.get(i));

        }

        mainPanel.add("LINE_START", buttonPanel);

        JPanel ioPanel = new JPanel();
        messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ioPanel.setLayout(new BoxLayout(ioPanel, BoxLayout.Y_AXIS));
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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        TicTacToeGUI gui = new TicTacToeGUI();
    }

    /**
     * Gets buttons.
     *
     * @return the buttons
     */
    public List<JButton> getButtons() {
        return this.buttons;
    }

    /**
     * Gets player field.
     *
     * @return the player field
     */
    public JTextField getPlayerField() {
        return this.playerField;
    }

    /**
     * Gets name field.
     *
     * @return the name field
     */
    public JTextField getNameField() {
        return this.nameField;
    }

    /**
     * Gets message box.
     *
     * @return the message box
     */
    public JTextArea getMessageBox() {
        return this.messageBox;
    }

    /**
     * Display error message.
     *
     * @param err the err
     */
    public void displayErrorMessage(String err) {
        JOptionPane.showMessageDialog(this, err);
    }

    /**
     * Add button listener.
     *
     * @param listener the listener
     */
    public void addButtonListener(ActionListener listener) {
        for (JButton b : buttons) {
            b.addActionListener(listener);
        }
    }

    /**
     * Add name listener.
     *
     * @param listener the listener
     */
    public void addNameListener(ActionListener listener) {
        this.nameField.addActionListener(listener);
    }

    /**
     * Update board.
     *
     * @param response the response
     */
    public void updateBoard(String response) {
        String[] responses = response.split("%");
        for (int i = 0; i < 9; i++) {
            buttons.get(i).setText(responses[i]);
        }
    }
}
