package com.pittacode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final IntegerReader integerReader = new IntegerReader();
        final StringReader stringReader = new StringReader();

        String name = stringReader.read();
        int yearOfBirth = integerReader.read();
        int age = integerReader.read();
        int height = integerReader.read();

        Person person = new Person(name, yearOfBirth, age, height);

        System.out.println(person.toString());
    }
}
