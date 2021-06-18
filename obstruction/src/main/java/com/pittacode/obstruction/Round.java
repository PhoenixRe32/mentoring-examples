package com.pittacode.obstruction;

import java.util.Arrays;

import static java.lang.String.format;

public class Round {

    private boolean isMovePlayed;
    private final String roundPlayer;
    private final String[][] board;

    public Round(int xLength, int yLength, String roundPlayer) {
        this.isMovePlayed = false;
        this.roundPlayer = roundPlayer;
        this.board = new String[xLength][yLength];
        for (int x = 0; x < xLength; x++) {
            Arrays.fill(board[x], "");
        }
    }

    public Round(Round previousRound, String roundPlayer) {
        this.isMovePlayed = false;
        this.roundPlayer = roundPlayer;
        this.board = previousRound.getBoard();
    }

    public boolean play(int x, int y) {
        if (isMovePlayed) {
            return false;
        }

        if (areCoordinatesInvalid(x, y)) {
            return false;
        }

        if (board[x][y].isEmpty()) {
            board[x][y] = roundPlayer;
            markDeadSpace(x, y);
            isMovePlayed = true;
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

    public String generateBoardVisualisation() {
        int yLength = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int y = yLength - 1; y >= 0; y--) {
            for (String[] strings : board) {
                sb.append(format("%4s", strings[y]));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String[][] getBoard() {
        String[][] boardCopy = new String[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            String[] boardColumn = board[i];
            boardCopy[i] = Arrays.copyOf(boardColumn, board[0].length);
        }

        return boardCopy;
    }

    public String getRoundPlayer() {
        return roundPlayer;
    }

    public boolean isInEndState() {
        for (String[] boardColumn : board) {
            for (String tile : boardColumn) {
                if (tile.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
