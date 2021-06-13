package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;

public class CalculatorConsole {

    private static final String EXIT = "exit";
    private static final String PROMPT = "> ";
    private static final String INTRO =
            "Yet Another SRPN Replica :-)"
                    + System.lineSeparator()
                    + "----------------------------"
                    + System.lineSeparator()
                    + System.lineSeparator()
                    + "Type `" + EXIT + "` or `Ctrl-d` to terminate the application"
                    + System.lineSeparator()
                    + System.lineSeparator();

    private final BufferedReader reader;
    private final PostFixCalculator postFixCalculator;

    public CalculatorConsole(BufferedReader reader, PostFixCalculator postFixCalculator) {
        this.reader = reader;
        this.postFixCalculator = postFixCalculator;
    }

    public void start() throws IOException {
        System.out.println(INTRO);
        ;

        boolean isStillRunning = true;
        while (isStillRunning) {
            isStillRunning = dialog();
        }

        System.out.println("BYE!");
    }

    private boolean dialog() throws IOException {
        String input = getInput();
        if (isExitCommand(input)) {
            return false;
        }

        postFixCalculator.process(input);

        return true;
    }

    private String getInput() throws IOException {
        System.out.print(PROMPT);
        return reader.readLine();
    }

    private boolean isExitCommand(String input) {
        return input == null || EXIT.equalsIgnoreCase(input.trim());
    }
}
