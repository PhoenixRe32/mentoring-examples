package com.pittacode.obstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static java.lang.String.format;

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

        return playerName.substring(0, Math.min(3, playerName.length())).toUpperCase();
    }

    public Dimensions askForGameDimensions() {
        int[] numbers = askForTwoNumbers(buildDialog("What are the board dimensions> "), 1);
        return new Dimensions(numbers[0], numbers[1]);
    }

    public Tile askForMove(String player) {
        int[] numbers = askForTwoNumbers(buildDialog(format("Make your move, %s> ", player)), 0);
        return new Tile(numbers[0], numbers[1]);
    }

    private ConsoleTokenisedInputSupplier buildDialog(String prompt) {
        return () -> {
            outputStream.print(prompt);
            String input = inputReader.readLine();
            return input.trim().split(" +");
        };
    }

    private int[] askForTwoNumbers(ConsoleTokenisedInputSupplier dialog, int minimumValue) {
        int[] result = new int[2];
        do try {
            String[] numbers = dialog.get();
            result[0] = Integer.parseInt(numbers[0]);
            result[1] = Integer.parseInt(numbers[1]);
        } catch (Exception e) {
            outputStream.println("Something went wrong.");
            outputStream.printf("Please input two numbers greater than %d, separated by a space. E.g. 9 8%n", minimumValue);
        } while (!Arrays.stream(result).allMatch(i -> i >= minimumValue));

        return result;
    }

    public void printBoard(int roundNumber, String roundBoard) {
        outputStream.printf("%nROUND %d Outcome:%n", roundNumber);
        outputStream.println(roundBoard);
        outputStream.println();
    }

    public void printWinner(String roundPlayer) {
        outputStream.printf("CONGRATULATIONS %s!!!! You are the big winner%n", roundPlayer);
        outputStream.println();
        outputStream.println("Hope you enjoyed this experience brought to you by Alexis and Andreas of PittaCode®");
    }

    public void print(String message) {
        outputStream.print(message);
    }
}
