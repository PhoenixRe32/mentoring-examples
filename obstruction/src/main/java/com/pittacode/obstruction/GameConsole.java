package com.pittacode.obstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class GameConsole {

    private final PrintStream outputStream;
    private final BufferedReader inputReader;

    public GameConsole(PrintStream outputStream, BufferedReader inputReader) {
        this.outputStream = outputStream;
        this.inputReader = inputReader;
    }

    public void printGameIntro() {
        outputStream.println("WELCOME TO A GAME OF OBSTRUCTION");
        outputStream.println("          by PittaCode          ");
        outputStream.println("      ====================      ");
        outputStream.println();
        outputStream.println();
    }

    public String askForPlayerName(int playerNumber) throws IOException {
        outputStream.printf("Enter player's %d name> ", playerNumber);
        String playerName = inputReader.readLine();

        return playerName.substring(0, Math.min(3, playerName.length()));
    }

    public Dimensions askForGameDimensions() throws IOException {
        outputStream.print("What are the game dimensions> ");
        String input = inputReader.readLine();

        return convertIntoDimensions(input);
    }

    private Dimensions convertIntoDimensions(String input) {
        String[] dimensions = input.trim().split(" +");
        return new Dimensions(Integer.parseInt(dimensions[0]),
                              Integer.parseInt(dimensions[1]));
    }

    public Tile askForMove(String player) throws IOException {
        outputStream.printf("Make your move, %s> ", player);
        String input = inputReader.readLine();

        return convertIntoTile(input);
    }

    private Tile convertIntoTile(String input) {
        String[] coordinates = input.trim().split(" +");
        return new Tile(Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]));
    }

    public void printBoard(int roundNumber, String roundBoard) {
        outputStream.printf("ROUND %d Outcome%n", roundNumber);
        outputStream.println("   --------     ");
        outputStream.println(roundBoard);
        outputStream.println();
    }

    public void printWinner(String roundPlayer) {
        outputStream.printf("CONGRATULATIONS %s!!!! You are the big winner%n", roundPlayer);
        outputStream.println();
        outputStream.println("Hope you enjoyed this experience brought to you by Alexis and Andreas of PittaCode®");
    }
}
