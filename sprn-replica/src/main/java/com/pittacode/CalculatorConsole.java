package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    private final static String PRINT_STACK = "d";
    private final static String PRINT_LAST_INPUT = "=";

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
        String userInput = getInput();

        if (isExitCommand(userInput)) {
            return false;
        }

        List<String> actualInputs = processInput(userInput);
        for (String actualInput : actualInputs) {
            postFixCalculator.process(actualInput);
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

    private List<String> processInput(String input) {
        return splitInputAtSpaces(input).stream()
                                        .flatMap(subInput -> splitInputAtPrintStackAction(subInput).stream())
                                        .flatMap(subInput -> splitStartingPrintLastElementAction(subInput).stream())
                                        .collect(Collectors.toList());

    }

    private List<String> splitInputAtSpaces(String input) {
        return Arrays.asList(SPACES.split(input));
    }

    private List<String> splitInputAtPrintStackAction(String input) {
        List<String> subInputs = new ArrayList<>();
        String remainingInput = input;
        int indexOfPrintStack = remainingInput.indexOf(PRINT_STACK);
        while (printStackActionIsPresent(indexOfPrintStack)) {
            String subInputBeforePrintStackAction = remainingInput.substring(0, indexOfPrintStack);
            if (!subInputBeforePrintStackAction.isEmpty()) {
                subInputs.add(subInputBeforePrintStackAction);
            }
            subInputs.add(PRINT_STACK);

            remainingInput = remainingInput.substring(indexOfPrintStack + 1);
            indexOfPrintStack = remainingInput.indexOf(PRINT_STACK);
        }
        if (!remainingInput.isEmpty()) {
            subInputs.add(remainingInput);
        }
        return subInputs;
    }

    private List<String> splitStartingPrintLastElementAction(String input) {
        List<String> subInputs = new ArrayList<>();
        String remainingInput = input;
        while (remainingInput.startsWith(PRINT_LAST_INPUT)) {
            subInputs.add(PRINT_LAST_INPUT);
            remainingInput = remainingInput.substring(1);
        }
        if (!remainingInput.isEmpty()) {
            subInputs.add(remainingInput);
        }

        return subInputs;
    }

    private boolean printStackActionIsPresent(int indexOfPrintStack) {
        return indexOfPrintStack != -1;
    }
}
