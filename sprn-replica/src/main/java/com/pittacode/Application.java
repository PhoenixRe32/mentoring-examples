package com.pittacode;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        // Dependencies
        Scanner terminalScanner = new Scanner(System.in);
        PostFixCalculator postFixCalculator = new PostFixCalculator();

        // Build actual application
        CalculatorConsole calculatorConsole = new CalculatorConsole(terminalScanner, postFixCalculator);

        // Start it
        calculatorConsole.start();
    }
}
