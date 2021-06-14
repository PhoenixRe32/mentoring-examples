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
    private final static Collection<String> OPERATORS = toSet(Arrays.asList(ADD, SUB, DIV, MUL, POW));

    // OPERAND -- modifies stack
    private final static String RND = "r";

    // ACTIONS -- read only
    private final static String PRINT_STACK = "d";
    private final static String PRINT_LAST_INPUT = "=";
    private final static Collection<String> ACTIONS = toSet(Arrays.asList(PRINT_LAST_INPUT, PRINT_STACK));

    private static Collection<String> toSet(List<String> list) {
        return new HashSet<>(list);
    }

    private static final int MAX_STACK_SIZE = 23;
    private static final List<Integer> RANDOMS = Arrays.asList(1804289383, 846930886, 1681692777, 1714636915,
                                                               1957747793, 424238335, 719885386, 1649760492,
                                                               596516649, 1189641421, 1025202362, 1350490027,
                                                               783368690, 1102520059, 2044897763, 1967513926,
                                                               1365180540, 1540383426, 304089172, 1303455736,
                                                               35005211, 521595368);

    private final Deque<Integer> stack;
    private int randomIndex;

    public PostFixCalculator() {
        this.stack = new ArrayDeque<>();
        this.randomIndex = 0;
    }

    // Wrap push operation to implement stack limitation logic
    public void push(Long operand) {
        if (operand == null) {
            // do nothing, we don't add null values
            // we also use it as a way to deal with some errors
            // also, can't add a null to an ArrayDeque
        } else if (stack.size() == MAX_STACK_SIZE) {
            printOverflowError();
        } else {
            stack.push(applyLimits(operand));
        }
    }

    public boolean canProcess(String input) {
        return isOperator(input) || isAction(input) || isNumericalInput(input);
    }

    private boolean isOperator(String input) {
        return OPERATORS.contains(input);
    }

    private boolean isAction(String input) {
        return ACTIONS.contains(input);
    }

    private boolean isNumericalInput(String input) {
        return RND.equals(input) || (!input.startsWith(ADD) && isNumber(input));
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
                push(divide());
                break;
            case MUL:
                push(multiply());
                break;
            case POW:
                push(power());
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

    private Long randomNumber() {
        Integer nextRandom = RANDOMS.get(randomIndex);
        randomIndex = (randomIndex + 1) % RANDOMS.size();
        return (long) nextRandom;
    }

    private Long add() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return operand1 + operand2;
        } catch (Exception e) {
            printUnderflowError();
            return operand2;
        }
    }

    private Long subtract() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return operand1 - operand2;
        } catch (Exception e) {
            printUnderflowError();
            return operand2;
        }
    }

    private Long divide() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return operand1 / operand2;
        } catch (Exception e) {
            printUnderflowError();
            return operand2;
        }
    }

    private Long multiply() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return operand1 * operand2;
        } catch (Exception e) {
            printUnderflowError();
            return operand2;
        }
    }

    private Long power() {
        Long operand2 = null;
        try {
            operand2 = (long) stack.pop();
            Long operand1 = (long) stack.pop();
            return Double.valueOf(Math.pow(operand1, operand2)).longValue();
        } catch (Exception e) {
            printUnderflowError();
            return operand2;
        }
    }

    private void processUnrecognisedInput(String input) {
        if (isNumber(input)) {
            push(Long.parseLong(input));
        } else {
            System.err.printf("Unrecognised operator or operand \"%s\".%n", input);
        }
    }

    private boolean isNumber(String input) {
        try {
            Long.parseLong(input);
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
