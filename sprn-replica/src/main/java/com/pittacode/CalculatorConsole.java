package com.pittacode;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class CalculatorConsole {

    private static final String EXIT = "exit";
    private static final String PROMPT = "> ";
    private static final String INTRO =
            "Yet Another SRPN Replica :-)"
                    + System.lineSeparator()
                    + "----------------------------"
                    + System.lineSeparator()
                    + System.lineSeparator()
                    + "Type `" + EXIT + "` to terminate the application"
                    + System.lineSeparator()
                    + System.lineSeparator();

    private final Scanner scanner;

    public CalculatorConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println(INTRO);;

        boolean isStillRunning = true;
        while(isStillRunning) {
            isStillRunning = dialog();
        }

        System.out.println("BYE!");
    }

    private boolean dialog() {
        String input = getInput();
        if (isExitCommand(input)) {
            return false;
        }
        return true;
    }

    private String getInput() {
        System.out.print(PROMPT);
        return scanner.nextLine();
    }

    private boolean isExitCommand(String input) {
        return EXIT.equalsIgnoreCase(input.trim());
    }
}
