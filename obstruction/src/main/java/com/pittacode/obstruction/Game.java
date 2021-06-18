package com.pittacode.obstruction;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Game {

    private final GameConsole gameConsole;
    private final Deque<Round> gameHistory;

    public Game(GameConsole gameConsole) {
        this.gameConsole = gameConsole;
        this.gameHistory = new ArrayDeque<>();
    }

    public void start() throws IOException {
        gameConsole.printGameIntro();

        List<String> players = new ArrayList<>(2);
        players.add(gameConsole.askForPlayerName(1));
        players.add(gameConsole.askForPlayerName(2));
        int currentPlayer = 0;

        Dimensions dimensions = gameConsole.askForGameDimensions();

        Round currentRound = new Round(dimensions.x, dimensions.y, players.get(currentPlayer));
        while(!currentRound.isInEndState()) {
            boolean wasMovePlayed = false;
            do {
                Tile tile = gameConsole.askForMove(players.get(currentPlayer));
                wasMovePlayed = currentRound.play(tile.x, tile.y);
            }while (!wasMovePlayed);

            gameHistory.push(currentRound);
            gameConsole.printBoard(gameHistory.size(), currentRound.generateBoardVisualisation());

            currentPlayer = (currentPlayer + 1) % 2;
            currentRound = new Round(currentRound, players.get(currentPlayer));
        }

        gameConsole.printWinner(gameHistory.peek().getRoundPlayer());
    }
}
