package com.pittacode.obstruction;

import java.util.Arrays;

public class Round {

    private final String[][] board;

    public Round(int xLength, int yLength) {
        board = new String[xLength][yLength];
        for (int x = 0; x < xLength; x++) {
            Arrays.fill(board[x], "");
        }
    }

    public boolean play(int x, int y, String player) {
        if (areCoordinatesInvalid(x, y)) {
            return false;
        }

        if (board[x][y].isEmpty()) {
            board[x][y] = player;
            return true;
        }

        return false;
    }

    private boolean areCoordinatesInvalid(int x, int y) {
        return x < 0 || y < 0 || x >= board.length || y >= board[0].length;
    }

    public String[][] getBoard() {
        return board.clone();
    }
}
