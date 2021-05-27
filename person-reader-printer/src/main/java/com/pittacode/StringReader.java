package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StringReader {

    private final Scanner scanner;
    private final BufferedReader reader;

    public StringReader() {
        this.scanner = new Scanner(System.in);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String scan() {
        System.out.println("Enter string: ");
        return scanner.nextLine();
    }

    public String read() throws IOException {
        System.out.println("Enter string: ");
        return reader.readLine();
    }
}
