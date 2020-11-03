
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGUI extends JFrame {
    private JTextArea firstNumber = new JTextArea(100, 100);
    private JLabel addLabel = new JLabel("+");
    private JTextField secondNumber = new JTextField(10);
    private JButton calcButton = new JButton("Calculate");
    private JTextField solution = new JTextField(10);

    // private JTextField status = new JTextField(40);

    public TicTacToeGUI() {
        JPanel calcPanel = new JPanel();
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcPanel.add(firstNumber);
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
