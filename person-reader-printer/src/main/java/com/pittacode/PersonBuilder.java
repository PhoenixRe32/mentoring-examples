package com.pittacode;

import java.io.IOException;

public class PersonBuilder {

    private final IntegerReader ageReader = new IntegerReader("age", 0, 150);
    private final IntegerReader yearReader = new IntegerReader("year of birth");
    private final IntegerReader heightReader = new IntegerReader("height", 30, 300);
    private final StringReader nameReader = new StringReader("name");

    public Person build() throws IOException {
        String name = nameReader.read();
        int yearOfBirth = yearReader.read();
        int age = ageReader.read();
        int height = heightReader.read();

        Person person = new Person(name, yearOfBirth, age, height);

        return person;
    }
}
