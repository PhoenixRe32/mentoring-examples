package com.pittacode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) throws IOException {
        // Dependencies
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        PostFixCalculator postFixCalculator = new PostFixCalculator();
        InFixCalculator inFixCalculator = new InFixCalculator();

        // Build actual application
        CalculatorConsole calculatorConsole = new CalculatorConsole(terminalReader, postFixCalculator, inFixCalculator);

        // Start it
        calculatorConsole.start();
    }
}
