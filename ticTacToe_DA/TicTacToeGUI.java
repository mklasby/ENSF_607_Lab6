package ticTacToe_DA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements Constants {
    private JButton cell00 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell01 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell02 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell10 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell11 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell12 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell20 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell21 = new JButton(Character.toString(SPACE_CHAR));
    private JButton cell22 = new JButton(Character.toString(SPACE_CHAR));
    private JLabel playerLabel = new JLabel("Player:");
    private JTextField playerField = new JTextField(10);
    private JLabel name = new JLabel("User Name:");
    private JTextField nameField = new JTextField(100);
    private JLabel messageLabel = new JLabel("Message Window: ");
    private JTextArea messageBox = new JTextArea(100, 100);

    public TicTacToeGUI() {
        JPanel gamePanel = new JPanel();
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.add(firstNumber);
        calcPanel.add(addLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(calcButton);
        calcPanel.add(solution);
        this.add(calcPanel);

    }

    public int getFirstNumber() {
        return Integer.parseInt(firstNumber.getText());
    }

    public int getSecondNumber() {
        return Integer.parseInt(secondNumber.getText());
    }

    public void setSolution(int sol) {
        this.solution.setText(Integer.toString(sol));
    }

    public void addCalcListener(ActionListener listenForCalcButton) {
        calcButton.addActionListener(listenForCalcButton);
    }

    public void displayErrorMessage(String err) {
        JOptionPane.showMessageDialog(this, err);
    }

}
