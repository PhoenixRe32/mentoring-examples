package com.pittacode.obstruction;

public class Round {

    private final String[][] board;

    public Round(int xLength, int yLength) {
        board = new String[xLength][yLength];
    }

    public boolean play(int x, int y, String player) {
        if (areCoordinatesInvalid(x, y)) {
            return false;
        }

        if (board[x][y] == null) {
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
