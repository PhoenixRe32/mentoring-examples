package com.pittacode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final IntegerReader ageReader = new IntegerReader("age", 0, 150);
        final IntegerReader yearReader = new IntegerReader("year of birth");
        final IntegerReader heightReader = new IntegerReader("height", 30, 300);
        final StringReader nameReader = new StringReader("name");

        String name = nameReader.read();
        int yearOfBirth = yearReader.read();
        int age = ageReader.read();
        int height = heightReader.read();

        Person person = new Person(name, yearOfBirth, age, height);

        System.out.println(person.toString());
    }
}
