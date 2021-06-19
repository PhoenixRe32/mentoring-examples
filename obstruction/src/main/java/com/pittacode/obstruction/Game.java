package com.pittacode.obstruction;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class Game {

    private final GameConsole gameConsole;
    private final Deque<Round> gameHistory;
    private final String[] players;
    private int currentPlayer;

    public Game(GameConsole gameConsole) {
        this.gameConsole = gameConsole;
        this.gameHistory = new ArrayDeque<>();
        this.players = new String[2];
        this.currentPlayer = 0;
    }

    public void start() throws IOException {
        gameConsole.printGameIntro();

        setPlayers();

        Round currentRound = new Round(gameConsole.askForGameDimensions(),
                                       players[currentPlayer]);
        while (!currentRound.isInEndState()) {
            playRound(currentPlayer, currentRound);

            store(currentRound);
            print(currentRound);

            currentRound = prepareNextRound(currentRound);
        }

        Optional.ofNullable(gameHistory.peek())
                .map(Round::getRoundPlayer)
                .ifPresentOrElse(gameConsole::printWinner,
                                 gameConsole::printNoRoundsError);
    }

    private void setPlayers() throws IOException {
        players[0] = gameConsole.askForPlayerName(1);
        players[1] = gameConsole.askForPlayerName(2);
    }

    private void playRound(int currentPlayer, Round currentRound) {
        boolean wasMoveValid = currentRound.play(gameConsole.askForMove(players[currentPlayer]));
        while (!wasMoveValid) {
            gameConsole.printInvalidMoveError();
            wasMoveValid = currentRound.play(gameConsole.askForMove(players[currentPlayer]));
        }
    }

    private void store(Round currentRound) {
        gameHistory.push(currentRound);
    }

    private void print(Round currentRound) {
        gameConsole.printBoard(gameHistory.size(), currentRound.generateBoardVisualisation());
    }

    private Round prepareNextRound(Round currentRound) {
        currentPlayer = (currentPlayer + 1) % 2;
        return new Round(currentRound, players[currentPlayer]);
    }
}