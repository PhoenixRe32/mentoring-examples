package com.pittacode.obstruction;

import java.io.PrintStream;

public class GameConsole {

    private final PrintStream outputStream;

    public GameConsole(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public Tile roundQuestion() {
        outputStream.print("Make your move, Player> ");

        return null;
    }
}
