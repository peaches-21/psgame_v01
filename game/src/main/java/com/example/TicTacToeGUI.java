package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private static JButton[][] buttons = new JButton[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameOver = false;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));

        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) return;

        btn.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            gameOver = true;
            askRestart();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            gameOver = true;
            askRestart();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private void askRestart() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            dispose();
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
        currentPlayer = 'X';
        gameOver = false;
    }

    private static boolean equal(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && 
        b1.getText().equals(b2.getText()) && 
        b2.getText().equals(b3.getText());
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3 ; i++) {
            if (equal( buttons[i][0], buttons[i][1], buttons[i][2] ) || equal( buttons[0][i], buttons[1][i], buttons[2][i] ) ) {
                return true;
            }
        }
        return equal( buttons[0][0], buttons[1][1], buttons[2][2] ) || equal( buttons[0][2], buttons[1][1], buttons[2][0] );
    }

    private static boolean isDraw() {
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if (buttons[i][j].getText().equals("")) return false;
            }
        }    
        return true;
    }

}