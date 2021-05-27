package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StringReader {

    private final Scanner scanner;
    private final BufferedReader reader;
    private final String label;

    public StringReader(String label) {
        this.label = label;
        this.scanner = new Scanner(System.in);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String scan() {
        System.out.printf("Enter %s: ", label);
        return scanner.nextLine();
    }

    public String read() throws IOException {
        System.out.printf("Enter %s: ", label);
        return reader.readLine();
    }
}
