package com.pittacode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class InFixCalculator {

    // OPERATORS -- modifies stack
    private final static String ADD = "+";
    private final static String SUB = "-";
    private final static String DIV = "/";
    private final static String MUL = "*";
    private final static String POW = "^";
    private final static Collection<String> OPERATORS = toSet(Arrays.asList(ADD, SUB, DIV, MUL, POW));

    // OPERAND -- modifies stack
    private final static String RND = "r";

    // ACTIONS -- read only
    private final static String PRINT_LATEST_INPUT = "=";

    private final static Collection<String> RECOGNISABLE_TOKENS = toSet(Arrays.asList(ADD, SUB, DIV, MUL, POW, RND, PRINT_LATEST_INPUT));

    private static Collection<String> toSet(List<String> list) {
        return new HashSet<>(list);
    }

    public void process(String input) {
        List<String> segments = splitInputIntoSegments(input);
        System.out.println(segments);
    }

    private List<String> splitInputIntoSegments(String input) {
        List<String> segments = new ArrayList<>();
        int start = 0;
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            String currentCharacter = String.valueOf(charArray[i]);
            if (RECOGNISABLE_TOKENS.contains(currentCharacter)) {
                String substringSoFar = input.substring(start, i);
                if (!substringSoFar.isEmpty()) {
                    segments.add(substringSoFar);
                }
                segments.add(currentCharacter);
                start = i + 1;
            }
        }
        return segments;
    }
}
