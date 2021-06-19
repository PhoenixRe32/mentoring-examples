package com.pittacode.obstruction;

import java.util.Arrays;

import static java.lang.String.format;

public class Round {

    private static final String EMPTY_TILE = "✹";

    private boolean isMovePlayed;
    private final String roundPlayer;
    private final String[][] board;

    public Round(Dimensions dimensions, String roundPlayer) {
        this.isMovePlayed = false;
        this.roundPlayer = roundPlayer;
        this.board = new String[dimensions.x][dimensions.y];
        for (int x = 0; x < dimensions.x; x++) {
            Arrays.fill(board[x], "✹");
        }
    }

    public Round(Round previousRound, String roundPlayer) {
        this.isMovePlayed = false;
        this.roundPlayer = roundPlayer;
        this.board = previousRound.getBoard();
    }

    public MoveOutcome play(Tile tile) {
        if (isMovePlayed) {
            return MoveOutcome.aFailedMove(MoveOutcome.MoveError.DOUBLE_PLAY);
        }

        if (areCoordinatesInvalidFor(tile)) {
            return MoveOutcome.aFailedMove(MoveOutcome.MoveError.OUT_OF_BOUNDS);
        }

        if (board[tile.x][tile.y].equals(EMPTY_TILE)) {
            board[tile.x][tile.y] = roundPlayer;
            markDeadSpaceAround(tile);
            isMovePlayed = true;
            return MoveOutcome.aSuccessfulMove();
        }

        return MoveOutcome.aFailedMove(MoveOutcome.MoveError.OCCUPIED);
    }

    private boolean areCoordinatesInvalidFor(Tile tile) {
        return isTileBeyondTheXBoundaries(tile) || isTileBeyondTheYBoundaries(tile);
    }

    private boolean isTileBeyondTheXBoundaries(Tile tile) {
        return tile.x < 0 || tile.x >= board.length;
    }

    private boolean isTileBeyondTheYBoundaries(Tile tile) {
        return tile.y < 0 || tile.y >= board[0].length;
    }

    private void markDeadSpaceAround(Tile tile) {
        int x = tile.x;
        int y = tile.y;
        int xStart = Math.max(x - 1, 0);
        int xEnd = Math.min(x + 1, board.length - 1);
        int yStart = Math.max(y - 1, 0);
        int yEnd = Math.min(y + 1, board[0].length - 1);

        for (int xPos = xStart; xPos <= xEnd; xPos++) {
            for (int yPos = yStart; yPos <= yEnd; yPos++) {
                if (!(xPos == x && yPos == y)) {
                    board[xPos][yPos] = "☥";
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
                if (tile.equals(EMPTY_TILE)) {
                    return false;
                }
            }
        }
        return true;
    }
}
