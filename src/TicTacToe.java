import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton buttons[] = new JButton[9];
    boolean xTurn = true;   
    JLabel status;
    JButton resetBtn;

    public TicTacToe() {

        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].addActionListener(this);
            board.add(buttons[i]);
        }

        status = new JLabel("X's Turn", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 20));

        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> resetGame());

        JPanel bottom = new JPanel();
        bottom.add(resetBtn);

        add(status, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return;
        }

        if (xTurn) {
            clicked.setText("X");
            status.setText("O's Turn");
        } else {
            clicked.setText("O");
            status.setText("X's Turn");
        }

        xTurn = !xTurn;

        checkGame();
    }

    public void checkGame() {

        String[][] winPatterns = {
                {btn(0), btn(1), btn(2)},
                {btn(3), btn(4), btn(5)},
                {btn(6), btn(7), btn(8)},
                {btn(0), btn(3), btn(6)},
                {btn(1), btn(4), btn(7)},
                {btn(2), btn(5), btn(8)},
                {btn(0), btn(4), btn(8)},
                {btn(2), btn(4), btn(6)}
        };

        for (String[] line : winPatterns) {
            if (line[0].equals("X") && line[1].equals("X") && line[2].equals("X")) {
                gameOver("X Wins!");
                return;
            }
            if (line[0].equals("O") && line[1].equals("O") && line[2].equals("O")) {
                gameOver("O Wins!");
                return;
            }
        }

        boolean full = true;
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                full = false;
                break;
            }
        }

        if (full) {
            gameOver("It's a Draw!");
        }
    }

    private String btn(int i) {
        return buttons[i].getText();
    }

    public void gameOver(String msg) {
        status.setText(msg);
        for (JButton b : buttons) {
            b.setEnabled(false);
        }
    }

    public void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        xTurn = true;
        status.setText("X's Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
