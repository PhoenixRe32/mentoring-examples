package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IntegerReader {

    private final Scanner scanner;
    private final BufferedReader reader;
    private final String label;
    private final int min;
    private final int max;

    public IntegerReader(String label) {
        this(label, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerReader(String label, int min, int max) {
        this.label = label;
        this.min = min;
        this.max = max;
        this.scanner = new Scanner(System.in);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int scan() {
        System.out.printf("Enter %s: ", label);
        return validate(scanner.nextInt());
    }

    public int read() throws IOException {
        System.out.printf("Enter %s: ", label);
        final String line = reader.readLine();
        return validate(Integer.parseInt(line));
    }

    private int validate(int i) {
        if (min > i || i > max) {
            throw new IllegalArgumentException(String.format("%s input should be between %d and %d", label, min, max));
        }
        return i;
    }
}
