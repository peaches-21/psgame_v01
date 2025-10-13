package com.example;

import javax.swing.*;

public class GameLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameLauncher::showMenu);
    }

    private static void showMenu() {
        while (true) {
            String[] options = {"Tic Tac Toe", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Choose a Game to Play:",
                "🎮 Game Launcher",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 0) {
                new TicTacToeGUI(); // open game
                break; // stop reopening launcher immediately
            } else if (choice == 1 || choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Thanks for playing!");
                System.exit(0);
            }
        }
    }
}
