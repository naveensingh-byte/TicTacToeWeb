import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel = new JLabel("Player X's Turn");

    public Main() {
        // Configure main window
        setTitle("Tic-Tac-Toe");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status Label at top
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(statusLabel, BorderLayout.NORTH);

        // 3x3 Grid of Buttons
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3, 5, 5));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("")) {
            return; // Ignore if already clicked
        }

        clickedButton.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            statusLabel.setText("🎉 Player " + currentPlayer + " Wins!");
            disableAllButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a Draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s Turn");
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Rows & Columns
            if (checkThree(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkThree(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        // Diagonals
        if (checkThree(buttons[0][0], buttons[1][1], buttons[2][2])) return true;
        if (checkThree(buttons[0][2], buttons[1][1], buttons[2][0])) return true;

        return false;
    }

    private boolean checkThree(JButton b1, JButton b2, JButton b3) {
        String text = b1.getText();
        return !text.equals("") && text.equals(b2.getText()) && text.equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}