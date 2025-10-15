package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TicTacToeGUI represents a simple 3x3 Tic Tac Toe game using Swing.
 * It demonstrates event-driven programming through ActionListener.
 */
public class TicTacToeGUI extends JFrame implements ActionListener {

    // 2D array of buttons representing the 3x3 grid
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X'; // X always starts first
    private boolean gameOver = false; // tracks if the game has ended

    /**
     * Constructor initializes the window and grid layout.
     */
    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes only this window, not the whole app
        setLayout(new GridLayout(3, 3)); // sets layout to 3x3 grid

        Font font = new Font("Arial", Font.BOLD, 60); // big bold font for X and O

        // Create and add 9 buttons to the frame
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this); // listen for clicks
                add(buttons[i][j]);
            }
        }

        setVisible(true); // make the window visible
    }

    /**
     * Handles button click events.
     * This is the main method where player moves are processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return; // if game is over, ignore clicks

        JButton btn = (JButton) e.getSource(); // identify which button was clicked
        if (!btn.getText().equals("")) return; // ignore if button already has X or O

        btn.setText(String.valueOf(currentPlayer)); // mark current player's move

        // Check for win or draw after each move
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            gameOver = true;
            askRestart(); // ask if the user wants to restart
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            gameOver = true;
            askRestart(); // ask if the user wants to restart
        } else {
            // Switch turns between X and O
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    /**
     * Displays a popup asking the player if they want to restart or exit.
     */
    private void askRestart() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetBoard(); // restart the same game
        } else {
            dispose(); // close current game window
            // Return to launcher menu
            com.example.games.GameLauncher.showMenu();
        }
    }

    /**
     * Clears the board for a new game.
     */
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // reset all buttons
            }
        }
        currentPlayer = 'X';
        gameOver = false;
    }

    /**
     * Checks all winning conditions for rows, columns, and diagonals.
     */
    private boolean checkWin() {
        // check rows and columns
        for (int i = 0; i < 3; i++) {
            if (equal(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                equal(buttons[0][i], buttons[1][i], buttons[2][i])) {
                return true;
            }
        }
        // check diagonals
        return equal(buttons[0][0], buttons[1][1], buttons[2][2]) ||
               equal(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    /**
     * Checks if all cells are filled without a winner.
     */
    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals("")) return false; // still empty cells
        return true;
    }

    /**
     * Helper function to compare three buttons for equality.
     */
    private boolean equal(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") &&
               b1.getText().equals(b2.getText()) &&
               b2.getText().equals(b3.getText());
    }
}

/* Concepts Demonstrated:
Encapsulation: Private variables and helper methods ensure that the internal state of the class is tightly controlled and hidden from external access. This promotes modularity and protects data integrity.
Inheritance: Using 'extends JFrame' allows your class to inherit window behavior from Java Swing’s 'JFrame', giving it built-in capabilities for GUI rendering and interaction.
Polymorphism: By implementing 'ActionListener', your class can dynamically respond to user actions. This interface-based design enables flexible event handling across different components.
Event Handling: The method 'actionPerformed(ActionEvent e)' is the core of event-driven programming in Swing. It captures and processes user interactions like button clicks or menu selections.
Threading (EDT): Calling 'SwingUtilities.invokeLater()' ensures that UI updates happen on the Event Dispatch Thread, maintaining thread safety and preventing unpredictable behavior in the GUI.
Layout Management: Using 'GridLayout(3,3)' automatically arranges components in a 3×3 grid, simplifying the positioning and alignment of elements without manual calculations.
Dialog Interaction: 'JOptionPane.showMessageDialog()' provides instant feedback to users through pop-up dialogs, ideal for alerts, confirmations, or error messages.
Reusability: A modular 'GameLauncher' class makes it easy to extend your application with new games or features, promoting clean architecture and scalability.
*/