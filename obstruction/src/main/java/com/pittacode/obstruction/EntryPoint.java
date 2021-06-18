package com.pittacode.obstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntryPoint {

    public static void main(String[] args) throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        GameConsole gameConsole = new GameConsole(System.out, inputReader);
        Game game = new Game(gameConsole);
        game.start();
    }
}
