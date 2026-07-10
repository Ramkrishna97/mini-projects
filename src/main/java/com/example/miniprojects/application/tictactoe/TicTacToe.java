package com.example.miniprojects.application.tictactoe;

import java.io.Serializable;

public class TicTacToe implements Serializable {
    private String[] board; // 9 squares: 0 to 8
    private String currentPlayer;
    private String winner;
    private boolean isDraw;

    public TicTacToe() {
        reset();
    }

    public void reset() {
        board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }
        currentPlayer = "X";
        winner = null;
        isDraw = false;
    }

    public void makeMove(int position) {
        // Ignore if square is taken or game is already over
        if (!board[position].equals("") || winner != null || isDraw) {
            return;
        }

        board[position] = currentPlayer;
        checkGameStatus();

        if (winner == null && !isDraw) {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private void checkGameStatus() {
        // 8 Possible Winning Combinations
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] combo : winCombinations) {
            if (!board[combo[0]].equals("") &&
                    board[combo[0]].equals(board[combo[1]]) &&
                    board[combo[0]].equals(board[combo[2]])) {
                winner = board[combo[0]];
                return;
            }
        }

        // Check for Draw
        boolean spacesLeft = false;
        for (String space : board) {
            if (space.equals("")) {
                spacesLeft = true;
                break;
            }
        }
        if (!spacesLeft) {
            isDraw = true;
        }
    }

    // Getters and Setters
    public String[] getBoard() { return board; }
    public String getCurrentPlayer() { return currentPlayer; }
    public String getWinner() { return winner; }
    public boolean isDraw() { return isDraw; }
}

