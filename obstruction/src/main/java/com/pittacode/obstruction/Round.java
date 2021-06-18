package com.pittacode.obstruction;

public class Round {

    private final String[][] board;

    public Round(int xLength, int yLength) {
        board = new String[xLength][yLength];
    }

    public boolean play(int x, int y, String player) {
        if (board[x][y] == null) {
            board[x][y] = player;
            return true;
        }

        return false;
    }

    public String[][] getBoard() {
        return board.clone();
    }
}
