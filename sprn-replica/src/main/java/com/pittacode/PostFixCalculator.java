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

    private static final int MAX_STACK_SIZE = 23;

    private static Collection<String> toSet(Collection<String> collection) {
        return new HashSet<>(collection);
    }

    private final Deque<Integer> stack;

    public PostFixCalculator() {
        this.stack = new ArrayDeque<>();
    }

    public void process(String input) {
        if (isAction(input)) {
            executeAction(input);
        } else if (isNumber(input)) {
            pushNumberToStack(Integer.parseInt(input));
        } else {
            System.err.printf("Unrecognised operator or operand \"%s\".%n", input);
        }
    }

    private boolean isAction(String input) {
        return ACTIONS.contains(input);
    }

    private void executeAction(String input) {
        if (PRINT_LAST_INPUT.equalsIgnoreCase(input)) {
            printLastInput();
        } else if (PRINT_STACK.equalsIgnoreCase(input)) {
            printStack();
        } else {
            System.err.println("Shouldn't get here... In-development error");
        }
    }

    private void printLastInput() {
        if (stack.isEmpty()) {
            System.out.println("Stack empty.");
        } else {
            System.out.println(stack.peek());
        }
    }

    private void printStack() {
        stack.descendingIterator().forEachRemaining(System.out::println);
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void pushNumberToStack(int number) {
        if (stack.size() == MAX_STACK_SIZE) {
            System.err.println("Stack overflow");
        } else {
            stack.push(number);
        }
    }


}
