package com.example;

import javax.swing.*;

/**
 * GameLauncher acts as the entry point for all games.
 * It displays a popup menu to allow the user to select a game.
 * This example launches a GUI-based Tic Tac Toe game.
 */
public class GameLauncher {

    public static void main(String[] args) {
        // Ensures that the GUI runs on the Event Dispatch Thread (EDT)
        // which is the recommended practice for Swing applications.
        SwingUtilities.invokeLater(GameLauncher::showMenu);
    }

    /**
     * Displays the main menu where users can choose which game to play.
     * The loop is used to keep showing the menu until the user exits.
     */
    public static void showMenu() {
        while (true) {
            String[] options = {"Tic Tac Toe", "Exit"};

            // Displays a popup menu with game options
            int choice = JOptionPane.showOptionDialog(
                null, // parent component (null means center of screen)
                "Choose a Game to Play:",
                "🎮 Game Launcher",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, // no custom icon
                options, // array of button labels
                options[0] // default selection
            );

            // If the user chooses Tic Tac Toe
            if (choice == 0) {
                new TicTacToeGUI(); // launch the Tic Tac Toe game window
                break; // exit the loop to avoid reopening the launcher immediately
            } 
            // If the user chooses Exit or closes the popup
            else if (choice == 1 || choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Thanks for playing!");
                System.exit(0); // terminate the program
            }
        }
    }
}
