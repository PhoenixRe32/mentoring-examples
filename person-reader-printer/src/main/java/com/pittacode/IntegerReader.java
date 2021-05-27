package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IntegerReader {

    private final Scanner scanner;
    private final BufferedReader reader;
    private final String label;

    public IntegerReader(String label) {
        this.label = label;
        this.scanner = new Scanner(System.in);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int scan() {
        System.out.printf("Enter %s: ", label);
        return scanner.nextInt();
    }

    public int read() throws IOException {
        System.out.printf("Enter %s: ", label);
        final String line = reader.readLine();
        return Integer.parseInt(line);
    }
}
