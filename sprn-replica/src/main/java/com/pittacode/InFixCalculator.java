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

    private final RandomGenerator randomGenerator;

    public InFixCalculator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    private static Collection<String> toSet(List<String> list) {
        return new HashSet<>(list);
    }

    public void process(String input) {
        List<String> segments = splitInputIntoSegments(input);
        segments = constructNegativeNumbers(segments);
        segments = replaceRandoms(segments);

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
        String substringSoFar = input.substring(start);
        if (!substringSoFar.isEmpty()) {
            segments.add(substringSoFar);
        }
        return segments;
    }

    private List<String> constructNegativeNumbers(List<String> segments) {
        List<String> reducedSegments = new ArrayList<>();
        for (int i = 0; i < segments.size(); i++) {
            if (SUB.equals(segments.get(i))) {
                if (RECOGNISABLE_TOKENS.contains(segments.get(i - 1)) && i + 1 < segments.size() && isNumber(segments.get(i + 1))) {
                    reducedSegments.add(segments.get(i) + segments.get(i + 1));
                    i++;
                }
            } else {
                reducedSegments.add(segments.get(i));
            }
        }
        return reducedSegments;
    }

    private boolean isNumber(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<String> replaceRandoms(List<String> segments) {
        List<String> replacedSegments = new ArrayList<>();
        for (String segment : segments) {
            if (RND.equals(segment)) {
                replacedSegments.add(String.valueOf(randomGenerator.getNext()));
            } else {
                replacedSegments.add(segment);
            }
        }
        return replacedSegments;
    }
}
