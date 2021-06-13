package com.pittacode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;

public class PostFixCalculator {

    // OPERATIONS -- modifies stack
    private final static String ADD = "+";
    private final static String SUB = "-";
    private final static String DIV = "/";
    private final static String MUL = "*";
    private final static String POW = "^";
    private final static Collection<String> OPERATIONS = toSet(Arrays.asList(ADD, SUB, DIV, MUL, POW));

    // INPUT -- modifies stack
    private final static String RND = "r";
    private final static Collection<String> INPUT = toSet(Collections.singletonList(RND));

    // ACTIONS -- read only
    private final static String PRINT_STACK = "d";
    private final static String PRINT_LAST_INPUT = "=";
    private final static Collection<String> ACTIONS = toSet(Arrays.asList(PRINT_LAST_INPUT, PRINT_STACK));

    private static Collection<String> toSet(Collection<String> collection) {
        return new HashSet<>(collection);
    }

    private final Deque<Integer> stack;

    public PostFixCalculator() {
        this.stack = new ArrayDeque<>();
    }

    public void process(String input) {
        if (isNumber(input)) {
            stack.push(Integer.parseInt(input));
        }
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
