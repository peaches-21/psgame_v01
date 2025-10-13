package com.example;

import java.util.Scanner;

public class TicTacToeConsole {

    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    
    private static char currentPlayer = 'X';
    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     boolean gameEnded = false;

    //     System.out.println("Welcome to TicTacToe (Control Version)");
    //     printBoard();

    //     while (!gameEnded) {
    //         System.out.println("Player " + currentPlayer + ", enter your move (row and column: 0, 1, or 2):");
    //         int row = scanner.nextInt();
    //         int col = scanner.nextInt();

    //         if (row < 0 || row > 2 || col < 0 || col > 2) {
    //             System.out.println("Invalid position! Try again.");
    //             continue;
    //         }

    //         if (board[row][col] != ' ') {
    //             System.out.println("Cell already taken! Try again.");
    //             continue;
    //         }

    //         board[row][col] = currentPlayer;
    //         printBoard();

    //         if (hasWon(currentPlayer)) {
    //             System.out.println("Player " + currentPlayer + " won the game!");
    //             gameEnded = true;
    //         } else if (isDraw()) {
    //             System.out.println("Game is draw!!");
    //             gameEnded = true;
    //         } else {
    //             currentPlayer = (currentPlayer == 'X') ? 'O' :'X';
    //         }
    //     }
    //     scanner.close();
    // }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) ;
    }

    private static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}
