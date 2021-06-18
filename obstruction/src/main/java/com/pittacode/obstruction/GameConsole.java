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

    public Tile roundQuestion() throws IOException {
        outputStream.print("Make your move, Player> ");
        String input = inputReader.readLine();

        return convertIntoTile(input);
    }

    private Tile convertIntoTile(String input) {
        String[] coordinates = input.trim().split(" +");
        return new Tile(Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]));
    }
}
