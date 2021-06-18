package com.pittacode.obstruction;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class Game {

    private final GameConsole gameConsole;
    private final Deque<Round> gameHistory;
    private final String[] players;

    public Game(GameConsole gameConsole) {
        this.gameConsole = gameConsole;
        this.gameHistory = new ArrayDeque<>();
        this.players = new String[2];
    }

    public void start() throws IOException {
        gameConsole.printGameIntro();

        setPlayers();

        int currentPlayer = 0;
        Round currentRound = new Round(gameConsole.askForGameDimensions(),
                                       players[currentPlayer]);
        while (!currentRound.isInEndState()) {
            playRound(currentPlayer, currentRound);

            store(currentRound);
            print(currentRound);

            currentPlayer = changeToNextRoundPlayer(currentPlayer);
            currentRound = createNextRound(currentPlayer, currentRound);
        }

        Optional.ofNullable(gameHistory.peek())
                .ifPresentOrElse(
                        lastRound -> gameConsole.printWinner(lastRound.getRoundPlayer()),
                        () -> gameConsole.print("Something went wrong and no rounds were played")
                );
    }

    private void setPlayers() throws IOException {
        players[0] = gameConsole.askForPlayerName(1);
        players[1] = gameConsole.askForPlayerName(2);
    }

    private void playRound(int currentPlayer, Round currentRound) {
        boolean wasMoveValid = false;
        do {
            wasMoveValid = currentRound.play(gameConsole.askForMove(players[currentPlayer]));
        } while (!wasMoveValid);
    }

    private void store(Round currentRound) {
        gameHistory.push(currentRound);
    }

    private void print(Round currentRound) {
        gameConsole.printBoard(gameHistory.size(), currentRound.generateBoardVisualisation());
    }

    private int changeToNextRoundPlayer(int currentPlayer) {
        return (currentPlayer + 1) % 2;
    }

    private Round createNextRound(int currentPlayer, Round currentRound) {
        return new Round(currentRound, players[currentPlayer]);
    }
}
