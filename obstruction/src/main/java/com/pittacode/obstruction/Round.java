package com.pittacode.obstruction;

import java.util.Arrays;

import static java.lang.String.format;

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
            markDeadSpace(x, y);
            return true;
        }

        return false;
    }

    private boolean areCoordinatesInvalid(int x, int y) {
        return x < 0 || y < 0 || x >= board.length || y >= board[0].length;
    }

    private void markDeadSpace(int x, int y) {
        int xStart = Math.max(x - 1, 0);
        int xEnd = Math.min(x + 1, board.length - 1);
        int yStart = Math.max(y - 1, 0);
        int yEnd = Math.min(y + 1, board[0].length - 1);

        for (int xPos = xStart; xPos <= xEnd; xPos++) {
            for (int yPos = yStart; yPos <= yEnd; yPos++) {
                if (!(xPos == x && yPos == y)) {
                    board[xPos][yPos] = "X";
                }
            }
        }
    }

    public String[][] getBoard() {
        return board.clone();
    }
}
