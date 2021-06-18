package com.pittacode.obstruction;

import static java.lang.String.format;

public class Round {

    private final String[][] board;

    public Round(int xLength, int yLength) {
        board = new String[xLength][yLength];
    }

    public void play(int x, int y, String player) {
        board[x][y] = player;
    }

    public String[][] getBoard() {
        return board.clone();
    }
}
