package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

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

    private final static Pattern SPACES = Pattern.compile(" +");

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

        String[] inputs = splitInputAtSpaces(input);

        for (String individualInput : inputs) {
            postFixCalculator.process(individualInput);
        }

        return true;
    }

    private String getInput() throws IOException {
        System.out.print(PROMPT);
        String line = reader.readLine();
        return line == null ? null : line.trim();
    }

    private boolean isExitCommand(String input) {
        return input == null || EXIT.equalsIgnoreCase(input);
    }

    private String[] splitInputAtSpaces(String input) {
        return SPACES.split(input);
    }
}
