package com.example;

import java.util.Scanner;

/**
 * TicTacToeConsole - A simple console-based Tic Tac Toe game.
 * Demonstrates core programming concepts such as:
 * - 2D array usage
 * - control flow (loops & conditions)
 * - user input with Scanner
 * - separation of logic from UI (text-based interface)
 */
public class TicTacToeConsole {

    private char[][] board; // 3x3 game board
    private char currentPlayer; // 'X' or 'O'
    private boolean gameOver; // true when game ends
    private Scanner scanner; // for reading user input

    /**
     * Constructor initializes the board and player.
     */
    public TicTacToeConsole() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        scanner = new Scanner(System.in);

        // Initialize empty board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Starts the console-based game loop.
     */
    public void play() {
        System.out.println("Welcome to Tic Tac Toe (Console Version)!");
        printBoard();

        while (!gameOver) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column: 1-3): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            // Validate move
            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Make move
            board[row][col] = currentPlayer;
            printBoard();

            // Check for win or draw
            if (checkWin()) {
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isDraw()) {
                System.out.println("🤝 It's a draw!");
                gameOver = true;
            } else {
                // Switch players
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        System.out.println("Game Over!");
    }

    /**
     * Prints the current board state in a grid format.
     */
    private void printBoard() {
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

    /**
     * Checks all rows, columns, and diagonals for a win.
     */
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    /**
     * Returns true if all cells are filled and there’s no winner.
     */
    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    /**
     * Static main method — allows standalone console execution.
     */
    public static void main(String[] args) {
        TicTacToeConsole game = new TicTacToeConsole();
        game.play();
    }
}

/* Concepts Demonstrated:
Encapsulation: Game state (board, current player, gameOver) is private to the class.
Control Structures: Nested loops for board traversal, if/else for validation and win checks.
User Input Handling: Uses Scanner for console-based input with validation logic.
Method Decomposition: Each function (checkWin, isDraw, printBoard) has a clear, single responsibility.
Reusability: The main logic (rules, win conditions) could be reused for GUI by separating model and view.
*/