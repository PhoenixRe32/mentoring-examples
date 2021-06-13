package com.pittacode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class PostFixCalculator {

    private final static int MINIMUM_INPUT = Integer.MIN_VALUE;
    private final static int MAXIMUM_INPUT = Integer.MAX_VALUE;

    // OPERATORS -- modifies stack
    private final static String ADD = "+";
    private final static String SUB = "-";
    private final static String DIV = "/";
    private final static String MUL = "*";
    private final static String POW = "^";

    // OPERAND -- modifies stack
    private final static String RND = "r";

    // ACTIONS -- read only
    private final static String PRINT_STACK = "d";
    private final static String PRINT_LAST_INPUT = "=";

    private static final int MAX_STACK_SIZE = 23;
    private static final List<Integer> RANDOMS = Arrays.asList(1804289383, 846930886, 1681692777, 1714636915,
                                                               1957747793, 424238335, 719885386, 1649760492,
                                                               596516649, 1189641421, 1025202362, 1350490027,
                                                               783368690, 1102520059, 2044897763, 1967513926,
                                                               1365180540, 1540383426, 304089172, 1303455736,
                                                               35005211, 521595368);

    private static Collection<String> toSet(Collection<String> collection) {
        return new HashSet<>(collection);
    }

    private final Deque<Integer> stack;
    private int randomIndex;

    public PostFixCalculator() {
        this.stack = new ArrayDeque<>();
        this.randomIndex = 0;
    }

    // Wrap push operation to implement stack limitation logic
    public void push(Integer operand) {
        if (operand == null) {
            // do nothing, we don't add null values
            // we also use it as a way to deal with some errors
            // also, can't add a null to an ArrayDeque
        } else if (stack.size() == MAX_STACK_SIZE) {
            printOverflowError();
        } else {
            stack.push(operand);
        }
    }

    public void process(String input) {
        switch (input) {
            case PRINT_LAST_INPUT:
                printLastInput();
                break;
            case PRINT_STACK:
                printStack();
                break;
            case RND:
                push(randomNumber());
                break;
            case ADD:
                push(add());
                break;
            case SUB:
                push(subtract());
                break;
            case DIV:
                break;
            case MUL:
                break;
            case POW:
                break;
            default:
                processUnrecognisedInput(input);
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
        if (stack.isEmpty()) {
            System.out.println(MINIMUM_INPUT);
        } else {
            stack.descendingIterator().forEachRemaining(System.out::println);
        }
    }

    private Integer randomNumber() {
        Integer nextRandom = RANDOMS.get(randomIndex);
        randomIndex = (randomIndex + 1) % RANDOMS.size();
        return nextRandom;
    }

    private Integer add() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return applyLimits(operand1 + operand2);
        } catch (Exception e) {
            printUnderflowError();
            return operand2 == null ? null : operand2.intValue();
        }
    }

    private Integer subtract() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return applyLimits(operand1 - operand2);
        } catch (Exception e) {
            printUnderflowError();
            return operand2 == null ? null : operand2.intValue();
        }
    }

    private void processUnrecognisedInput(String input) {
        if (isNumber(input)) {
            push(Integer.parseInt(input));
        } else {
            System.err.printf("Unrecognised operator or operand \"%s\".%n", input);
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

    private Integer applyLimits(Long value) {
        if (value > MAXIMUM_INPUT) {
            return MAXIMUM_INPUT;
        }
        if (value < MINIMUM_INPUT) {
            return MINIMUM_INPUT;
        }
        return value.intValue();
    }

    private void printOverflowError() {
        System.err.println("Stack overflow");
    }

    private void printUnderflowError() {
        System.err.println("Stack underflow");
    }
}
