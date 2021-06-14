package com.pittacode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class InFixCalculator {

    private final static int MINIMUM_INPUT = Integer.MIN_VALUE;
    private final static int MAXIMUM_INPUT = Integer.MAX_VALUE;

    // OPERATORS -- modifies stack
    private final static String ADD = "+";
    private final static String SUB = "-";
    private final static String DIV = "/";
    private final static String MUL = "*";
    private final static String POW = "^";
    private final static Collection<String> OPERATORS = toSet(Arrays.asList(ADD, SUB, DIV, MUL, POW));
    // This is replaced by actual values
    private final static String RND = "r";
    // This is executed before the infix operations and is removed
    private final static String PRINT_LATEST_INPUT = "=";
    private final static Collection<String> RECOGNISABLE_TOKENS = toSet(Arrays.asList(ADD, SUB, DIV, MUL, POW, RND, PRINT_LATEST_INPUT));

    private final RandomGenerator randomGenerator;

    public InFixCalculator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    private static Collection<String> toSet(List<String> list) {
        return new HashSet<>(list);
    }

    public boolean startsWithOperation(String input) {
        return OPERATORS.contains(String.valueOf(input.charAt(0)));
    }

    public List<String> process(String input) {
        List<String> segments = splitInputIntoSegments(input);
        segments = constructNegativeNumbers(segments);
        segments = replaceRandoms(segments);
        segments = executePrintLatestInput(segments);

        Deque<String> trailingOperations = getTrailingOperations(segments);
        segments = segments.subList(0, segments.size() - trailingOperations.size());

        System.out.println(segments);
        segments = executePowers(segments);
        System.out.println(segments);
        segments = executeMultiDiv(segments);
        System.out.println(segments);
        segments = executeAddSub(segments);
        return Collections.emptyList();
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
                } else {
                    reducedSegments.add(segments.get(i));
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

    private List<String> executePrintLatestInput(List<String> segments) {
        List<String> reducedSegments = new ArrayList<>();
        for (String segment : segments) {
            if (PRINT_LATEST_INPUT.equals(segment)) {
                System.out.println(findLatestInput(reducedSegments));
            } else {
                reducedSegments.add(segment);
            }
        }
        return reducedSegments;
    }

    private String findLatestInput(List<String> reducedSegments) {
        for (int i = reducedSegments.size() - 1; i >= 0; i--) {
            if (isNumber(reducedSegments.get(i))) {
                return reducedSegments.get(i);
            }
        }
        throw new IllegalStateException("Should not get here");
    }

    private Deque<String> getTrailingOperations(List<String> segments) {
        Deque<String> trailingOperations = new ArrayDeque<>();
        int i = segments.size() - 1;
        while (i >= 0 && OPERATORS.contains(segments.get(i))) {
            trailingOperations.push(segments.get(i));
            i--;
        }
        return trailingOperations;
    }

    private List<String> executePowers(List<String> segments) {
        int powerOperationIndex = segments.indexOf(POW);
        while (powerOperationIndex != -1) {
            String operand1 = segments.get(powerOperationIndex - 1);
            String operand2 = segments.get(powerOperationIndex + 1);
            Integer result = applyLimits(Math.pow(Double.parseDouble(operand1), Double.parseDouble(operand2)));

            segments.add(powerOperationIndex - 1, result.toString());
            segments.remove(powerOperationIndex);
            segments.remove(powerOperationIndex);
            segments.remove(powerOperationIndex);

            powerOperationIndex = segments.indexOf(POW);
        }

        return segments;
    }

    private List<String> executeMultiDiv(List<String> segments) {
        while (segments.contains(DIV) || segments.contains(MUL)) {
            for (int i = 0; i < segments.size(); i++) {
                String current = segments.get(i);
                if (DIV.equals(current) || MUL.equals(current)) {
                    Long operand1 = Long.parseLong(segments.get(i - 1));
                    Long operand2 = Long.parseLong(segments.get(i + 1));
                    segments.remove(i - 1);
                    segments.remove(i - 1);
                    segments.remove(i - 1);

                    Integer result = DIV.equals(current)
                            ? applyLimits(operand1 / operand2)
                            : applyLimits(operand1 * operand2);
                    segments.add(i - 1, result.toString());
                    break;
                }
            }
        }
        return segments;
    }

    private List<String> executeAddSub(List<String> segments) {
        while (segments.contains(ADD) || segments.contains(SUB)) {
            for (int i = 0; i < segments.size(); i++) {
                String current = segments.get(i);
                if (ADD.equals(current) || SUB.equals(current)) {
                    Long operand1 = Long.parseLong(segments.get(i - 1));
                    Long operand2 = Long.parseLong(segments.get(i + 1));
                    segments.remove(i - 1);
                    segments.remove(i - 1);
                    segments.remove(i - 1);

                    Integer result = ADD.equals(current)
                            ? applyLimits(operand1 + operand2)
                            : applyLimits(operand1 - operand2);
                    segments.add(i - 1, result.toString());
                    break;
                }
            }
        }
        return segments;
    }

    private Integer applyLimits(Double value) {
        return applyLimits(value.longValue());
    }

    private Integer applyLimits(Long value) {
        if (value > MAXIMUM_INPUT) {
            return MAXIMUM_INPUT;
        }
        if (value < MINIMUM_INPUT) {
            return MINIMUM_INPUT;
        }
        return value.intValue();
    }
}
