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
    // TODO look into having one source of truth for these
    private final static String PRINT_STACK = "d";
    private final static String PRINT_LATEST_INPUT = "=";
    private final static String RND = "r";

    private final BufferedReader reader;
    private final PostFixCalculator postFixCalculator;
    private final InFixCalculator inFixCalculator;

    public CalculatorConsole(BufferedReader reader, PostFixCalculator postFixCalculator, InFixCalculator inFixCalculator) {
        this.reader = reader;
        this.postFixCalculator = postFixCalculator;
        this.inFixCalculator = inFixCalculator;
    }

    public void start() throws IOException {
        System.out.println(INTRO);

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
            if (postFixCalculator.canProcess(actualInput)) {
                postFixCalculator.process(actualInput);
            } else if (inFixCalculator.startsWithOperation(actualInput)) {
                Integer missingOperand = postFixCalculator.pop();
                String correctedInput = missingOperand == null
                        ? actualInput.substring(1)
                        : String.format("%s%s", missingOperand, actualInput);
                inFixCalculator.process(correctedInput);
            } else {
                inFixCalculator.process(actualInput);
            }
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
                                        .flatMap(subInput -> splitStartingRecognisableToken(subInput, PRINT_LATEST_INPUT).stream())
                                        .flatMap(subInput -> splitStartingRecognisableToken(subInput, RND).stream())
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

    private List<String> splitStartingRecognisableToken(String input, String recognisableToken) {
        List<String> subInputs = new ArrayList<>();
        String remainingInput = input;
        while (remainingInput.startsWith(recognisableToken)) {
            subInputs.add(recognisableToken);
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
