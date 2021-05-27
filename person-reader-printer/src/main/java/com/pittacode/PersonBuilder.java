package com.pittacode;

import java.io.IOException;

public class PersonBuilder {

    private final IntegerReader ageReader;
    private final IntegerReader yearReader;
    private final IntegerReader heightReader;
    private final StringReader nameReader;

    public PersonBuilder() {
        this.ageReader = new IntegerReader("age", 0, 150);
        this.yearReader = new IntegerReader("year of birth");
        this.heightReader = new IntegerReader("height", 30, 300);
        this.nameReader = new StringReader("name");
    }

    public Person build() throws IOException {
        String name = nameReader.read();
        int yearOfBirth = yearReader.read();
        int age = ageReader.read();
        int height = heightReader.read();

        Person person = new Person(name, yearOfBirth, age, height);

        return person;
    }
}
